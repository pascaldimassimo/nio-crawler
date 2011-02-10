package niocrawler;

import java.net.URI;

import org.apache.commons.lang.StringUtils;

public class HttpRequestBuilder
{
    private static String GET_REQUEST_HEADER = "GET %s HTTP/1.1\r\n";

    private static String FIELD_ENCODING     = "%s: %s\r\n";

    public String buildGet(URI url)
    {
        return encodeHeader(url) + 
               encodeField("Host", url.getHost()) +                
               encodeField("Connection", "close") +
               "\r\n";
    }

    private String encodeHeader(URI url)
    {
        String path = url.getPath();
        if (StringUtils.isBlank(path))
        {
            path = "/";
        }
        return String.format(GET_REQUEST_HEADER, path);
    }

    private String encodeField(String field, String value)
    {
        return String.format(FIELD_ENCODING, field, value);
    }
}
