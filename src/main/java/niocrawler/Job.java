package niocrawler;

import java.net.URI;

public interface Job
{
    boolean visit(URI url);
    
    void process(Page page);
}
