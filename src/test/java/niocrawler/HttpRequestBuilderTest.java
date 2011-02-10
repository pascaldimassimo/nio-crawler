package niocrawler;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import org.junit.Test;

/**
 * TODO make those tests better!
 */
public class HttpRequestBuilderTest
{
    HttpRequestBuilder builder = new HttpRequestBuilder();

    @Test
    public void test_buildGet() throws Exception
    {
        URI uri = new URI("http://localhost:4567/ho");
        String expected = "GET /ho HTTP/1.1\r\nHost: localhost\r\nConnection: close\r\n\r\n";
        assertEquals(expected, builder.buildGet(uri));
    }
    
    @Test
    public void test_buildGet_no_path() throws Exception
    {
        URI uri = new URI("http://localhost:4567/");
        String expected = "GET / HTTP/1.1\r\nHost: localhost\r\nConnection: close\r\n\r\n";
        assertEquals(expected, builder.buildGet(uri));
    }
    
    @Test
    public void test_buildGet_no_path_no_slash() throws Exception
    {
        URI uri = new URI("http://localhost:4567");
        String expected = "GET / HTTP/1.1\r\nHost: localhost\r\nConnection: close\r\n\r\n";
        assertEquals(expected, builder.buildGet(uri));
    }
}
