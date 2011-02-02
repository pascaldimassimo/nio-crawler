package niocrawler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class HttpFetcherNIOImpl
{
    private Selector                selector;

    private ByteBuffer              readBuffer         = ByteBuffer.allocate(8192);

    private Map<URI, ByteBuffer>    writeBuffers       = new HashMap<URI, ByteBuffer>();

    private Map<URI, StringBuilder> content            = new HashMap<URI, StringBuilder>();

    private BlockingQueue<URI>      linksQueue;

    private BlockingQueue<Page>     pagesQueue;

    private HttpRequestBuilder      httpRequestBuilder = new HttpRequestBuilder();

    public HttpFetcherNIOImpl(BlockingQueue<URI> linksQueue, BlockingQueue<Page> pagesQueue) throws IOException
    {
        this.linksQueue = linksQueue;
        this.pagesQueue = pagesQueue;
        this.selector = SelectorProvider.provider().openSelector();
    }

    public String getContent()
    {
        return content.toString();
    }

    public void fetch()
    {
        while (true)
        {
            try
            {
                initiateNewConnections();

                int nb = selector.select(1000);
                if (nb == 0 && linksQueue.isEmpty())
                {
                    break;
                }

                // Iterate over the set of keys for which events are available
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext())
                {
                    SelectionKey key = iter.next();
                    iter.remove();

                    if (!key.isValid())
                    {
                        continue;
                    }

                    if (key.isConnectable())
                    {
                        connect(key);
                    }
                    else if (key.isWritable())
                    {
                        write(key);
                    }
                    else if (key.isReadable())
                    {
                        read(key);
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                break;
            }
        }
    }

    private void initiateNewConnections() throws IOException
    {
        // Initiate a maximum of 10 new connections
        for (int i = 0; i < 10; i++)
        {
            // Fetch without blocking
            URI url = linksQueue.poll();

            // We will retry at next iteration
            if (url == null)
            {
                break;
            }

            // Create a non-blocking socket channel
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

            // Kick off connection establishment
            int port = url.getPort() > 0 ? url.getPort() : 80;
            socketChannel.connect(new InetSocketAddress(url.getHost(), port));

            SelectionKey key = socketChannel.register(selector, SelectionKey.OP_CONNECT);
            key.attach(url);

            content.put(url, new StringBuilder());
        }
    }

    private void connect(SelectionKey key) throws IOException
    {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        socketChannel.finishConnect();

        // We now want to write
        key.interestOps(SelectionKey.OP_WRITE);
    }

    private void write(SelectionKey key) throws IOException
    {
        URI url = (URI) key.attachment();
        SocketChannel socketChannel = (SocketChannel) key.channel();

        ByteBuffer writeBuffer = writeBuffers.get(url);
        if (writeBuffer == null)
        {
            String getRequest = httpRequestBuilder.buildGet(url);
            writeBuffer = ByteBuffer.wrap(getRequest.getBytes());
            writeBuffers.put(url, writeBuffer);
        }

        socketChannel.write(writeBuffer);
        if (!writeBuffer.hasRemaining())
        {
            // Remove write buffer
            writeBuffers.remove(url);

            // We now want to read
            key.interestOps(SelectionKey.OP_READ);
        }
    }

    private void read(SelectionKey key) throws IOException
    {
        URI url = (URI) key.attachment();
        SocketChannel socketChannel = (SocketChannel) key.channel();

        readBuffer.clear();
        int numRead = socketChannel.read(readBuffer);
        if (numRead > 0)
        {
            String part = new String(readBuffer.array(), 0, numRead);
            content.get(url).append(part);
        }
        else
        {
            // Reading is complete
            key.channel().close();
            key.cancel();

            // Add to page queue
            StringBuilder sb = content.remove(url);
            Page page = new Page(url, sb.toString());
            pagesQueue.add(page);
        }
    }
}
