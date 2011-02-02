package niocrawler;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

public class LinksStorageMemImpl implements LinksStorage
{
    private Set<URI> links = new HashSet<URI>();

    @Override
    public boolean add(URI url)
    {
        return links.add(url);
    }

}
