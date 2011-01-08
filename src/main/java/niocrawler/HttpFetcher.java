package niocrawler;

import java.net.URI;
import java.util.List;

public interface HttpFetcher
{
    void fetch(List<URI> urls);
}
