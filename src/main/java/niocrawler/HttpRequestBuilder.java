package niocrawler;

import java.net.URI;

import org.apache.commons.lang.StringUtils;

public class HttpRequestBuilder
{
    public String buildGet(URI url)
    {
        String path = url.getPath();
        if (StringUtils.isBlank(path))
        {
            path = "/";
        }
        return String.format("GET %s HTTP/1.0\r\n\r\n", path);
    }
}
