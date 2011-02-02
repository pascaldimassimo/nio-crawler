package niocrawler;

import java.net.URI;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class PageHandler implements Runnable
{
    private BlockingQueue<Page> pagesQueue;

    private BlockingQueue<URI>  linksQueue;

    private Job                 job;

    private LinksStorage        linksStorage;

    public PageHandler(BlockingQueue<Page> pagesQueue, BlockingQueue<URI> linksQueue, Job job, LinksStorage linksStorage)
    {
        this.pagesQueue = pagesQueue;
        this.linksQueue = linksQueue;
        this.job = job;
        this.linksStorage = linksStorage;
    }

    // TODO rename to indicate side-effect
    private boolean canVisit(URI url)
    {
        return job.visit(url) && linksStorage.add(url);
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Page page = pagesQueue.take();
                System.out.println("Handling " + page.getUri());
                if (page.getStatusCode() == 200)
                {
                    // Let the user's job process this page
                    job.process(page);

                    String contentType = page.getHeader("Content-Type");
                    if (contentType.startsWith("text/html"))
                    {
                        List<URI> links = page.getLinks();
                        for (URI link : links)
                        {
                            if (canVisit(link))
                            {
                                linksQueue.add(link);
                            }
                        }
                    }
                }
                else if (page.getStatusCode() == 301 || page.getStatusCode() == 302)
                {
                    URI location = new URI(page.getHeader("Location"));
                    if (canVisit(location))
                    {
                        linksQueue.add(location);
                    }
                }
                else
                {
                    // Log that page was not fetched successfully
                    System.out.println(String.format("Skip [%s] because of status code %d", page.getUri(), page.getStatusCode()));
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
}
