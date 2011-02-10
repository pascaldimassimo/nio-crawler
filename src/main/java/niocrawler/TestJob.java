package niocrawler;

import java.net.URI;

public class TestJob implements Job
{
    @Override
    public boolean visit(URI url)
    {
//        System.out.println("VISIT? " + url);
        return url.toString().startsWith("http://www.nstein.com");
    }

    @Override
    public void process(Page page)
    {
//        System.out.println("PROCESS " + page.getUri());
    }
}
