package niocrawler;

import java.net.URI;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Crawler
{
    public static void main(String[] args) throws Exception
    {
        // TODO get from args
        TestJob crawler = new TestJob();

        BlockingQueue<URI> linksQueue = new LinkedBlockingQueue<URI>();
        // TODO get starting url from args
        linksQueue.put(new URI("http://en.wikinews.org/wiki/Main_Page"));

        BlockingQueue<Page> pagesQueue = new LinkedBlockingQueue<Page>();
        LinksStorageMemImpl linksStorage = new LinksStorageMemImpl();

        // TODO maintain a pool of handlers
        PageHandler parser = new PageHandler(pagesQueue, linksQueue, crawler, linksStorage);
        Thread thread = new Thread(parser);
        thread.setDaemon(true);
        thread.start();

        HttpFetcherNIOImpl client = new HttpFetcherNIOImpl(linksQueue, pagesQueue);
        client.fetch();
    }
}
