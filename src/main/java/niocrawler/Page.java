package niocrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class Page
{
    private URI                 uri;

    private String              content;

    private boolean             extracted    = false;

    private int                 statusCode;

    private Map<String, String> headers      = new HashMap<String, String>();

    private String              body;

    private static Pattern      regexPattern = Pattern.compile("href=\"(.+?)\"");

    public Page(URI uri, String content)
    {
        this.uri = uri;
        this.content = content;
    }

    public URI getUri()
    {
        return uri;
    }

    public String getBody()
    {
        if (!extracted)
        {
            try
            {
                extract();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
        return body;
    }

    public int getStatusCode()
    {
        getBody();
        return statusCode;
    }

    public String getHeader(String name)
    {
        getBody();
        return headers.get(name);
    }

    public List<URI> getLinks()
    {
        // TODO Find all links with a XML library? (parsing HTML with regexp is
        // usually BAD)
        Matcher matcher = regexPattern.matcher(getBody());

        ArrayList<URI> links = new ArrayList<URI>();
        while (matcher.find())
        {
            String link = matcher.group(1);
            try
            {
                links.add(uri.resolve(link));
            }
            catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            }
        }
        return links;
    }

    private void extract() throws IOException
    {
        BufferedReader br = new BufferedReader(new StringReader(content));

        // Read Http status line
        String line = br.readLine();
        String[] parts = line.split("\\s");
        statusCode = Integer.parseInt(parts[1]);

        // Read until next blank line
        while (!StringUtils.isBlank(line = br.readLine()))
        {
            int colon = line.indexOf(":");
            headers.put(line.substring(0, colon), line.substring(colon + 1).trim());
        }

        // Read until no more content
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null)
        {
            sb.append(line);
        }
        body = sb.toString();

        extracted = true;
    }
}
