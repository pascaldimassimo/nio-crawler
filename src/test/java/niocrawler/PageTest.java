package niocrawler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URI;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

public class PageTest
{
    private Page page;

    @Before
    public void setup() throws Exception
    {
        File file = new File("src/test/resources/page1.txt");
        String data = FileUtils.readFileToString(file);

        URI url = new URI("http://localhost:4567/ho");
        page = new Page(url, data.getBytes());
        page.process();
    }

    @Test
    public void test_getStatusCode() throws Exception
    {
        int statusCode = page.getStatusCode();
        assertEquals(200, statusCode);
    }

    @Test
    public void test_getHeaders() throws Exception
    {
        assertEquals("text/html;charset=utf-8", page.getHeader("Content-Type"));
        assertEquals("76", page.getHeader("Content-Length"));
        assertEquals("WEBrick/1.3.1 (Ruby/1.9.2/2010-08-18)", page.getHeader("Server"));
        assertEquals("Thu, 27 Jan 2011 22:49:40 GMT", page.getHeader("Date"));
        assertEquals("close", page.getHeader("Connection"));
    }

    @Test
    public void test_getBody() throws Exception
    {
        String body = page.getBody();
        assertTrue(body.startsWith("<html>"));
        assertTrue(body.endsWith("</html>"));
    }

    @Test
    public void test_getLinks() throws Exception
    {
        List<URI> links = page.getLinks();
        assertEquals(2, links.size());
        assertTrue(links.contains(new URI("http://localhost:4567/ha")));
        assertTrue(links.contains(new URI(page.getUri() + "#hey")));
    }

}
