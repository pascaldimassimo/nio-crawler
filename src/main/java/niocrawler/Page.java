package niocrawler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Page
{
    private URI                 uri;

    private byte[]              data;

    private int                 statusCode;

    private Map<String, String> headers;

    private String              body;

    public Page(URI uri, byte[] data)
    {
        this.uri = uri;
        this.data = data;
    }

    public void process()
    {
        HttpParser parser = new HttpParser();
        HttpParserData parserData = parser.parse(data);

        this.body = parserData.getBody();
        this.headers = parserData.getHttpFields();
        this.statusCode = parserData.getStatusCode();
    }

    public URI getUri()
    {
        return uri;
    }

    public String getBody()
    {
        return body;
    }

    public int getStatusCode()
    {
        return statusCode;
    }

    public String getHeader(String name)
    {
        return headers.get(name);
    }

    public List<URI> getLinks()
    {
        Document doc = Jsoup.parse(body);
        Elements elems = doc.select("a[href]");

        ArrayList<URI> links = new ArrayList<URI>();
        for (Element elem : elems)
        {
            try
            {
                String href = elem.attr("href");

                // see
                // http://stackoverflow.com/questions/724043/http-url-address-encoding-in-java
                URI link = new URI(href.replaceAll("\\s", "%20"));
                link = new URI(link.toASCIIString());

                link = uri.resolve(link);

                if (StringUtils.isNotBlank(link.getHost()))
                {
                    links.add(link);
                }
                // else: this is probably not a http link, skip it
            }
            catch (URISyntaxException e)
            {
                e.printStackTrace();
            }
        }
        return links;
    }
}
