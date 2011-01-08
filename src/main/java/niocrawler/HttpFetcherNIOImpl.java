package niocrawler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpFetcherNIOImpl implements HttpFetcher
{
    private Selector                selector;

    private ByteBuffer              readBuffer   = ByteBuffer.allocate(8192);

    private Map<URI, ByteBuffer>    writeBuffers = new HashMap<URI, ByteBuffer>();

    private Map<URI, StringBuilder> content      = new HashMap<URI, StringBuilder>();

    private List<URI>               urlsPending;

    public HttpFetcherNIOImpl() throws IOException
    {
        selector = SelectorProvider.provider().openSelector();
    }

    public String getContent()
    {
        return content.toString();
    }

    @Override
    public void fetch(List<URI> urls)
    {
        try
        {
            initiateConnections(urls);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        while (!urlsPending.isEmpty())
        {
            try
            {
                selector.select();

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
            }
        }
    }

    private void initiateConnections(List<URI> urls) throws IOException
    {
        urlsPending = new ArrayList<URI>(urls);
        for (URI url : urls)
        {
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
        System.out.println("connect " + key.attachment());

        SocketChannel socketChannel = (SocketChannel) key.channel();
        socketChannel.finishConnect();

        // We now want to write
        key.interestOps(SelectionKey.OP_WRITE);
    }

    private void write(SelectionKey key) throws IOException
    {
        URI url = (URI) key.attachment();
        System.out.println("write " + url);

        SocketChannel socketChannel = (SocketChannel) key.channel();

        ByteBuffer writeBuffer = writeBuffers.get(url);
        if (writeBuffer == null)
        {
            writeBuffer = ByteBuffer.wrap("GET / HTTP/1.0\r\n\r\n".getBytes());
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
        System.out.println("read " + url);

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
            System.out.println("close " + key.attachment());
            // System.out.println("--------------------------");
            // System.out.println(content.get(url));
            // System.out.println("--------------------------");

            // Reading is complete
            key.channel().close();
            key.cancel();

            urlsPending.remove(url);
        }
    }

    public static void main(String[] args) throws Exception
    {
        // opentext.com
        // yahoo.ca
        // cnn.com
        // gm.com

        URI[] urls = new URI[] { new URI("http://yahoo.ca"), new URI("http://opentext.com"), new URI("http://cnn.com"),
            new URI("http://gm.com"), new URI("http://localhost:4567") };

        HttpFetcherNIOImpl client = new HttpFetcherNIOImpl();
        client.fetch(Arrays.asList(urls));

    }
}
