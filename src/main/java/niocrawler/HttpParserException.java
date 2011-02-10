package niocrawler;

public class HttpParserException extends RuntimeException
{
    private static final long serialVersionUID = -7563040337362868784L;

    public HttpParserException()
    {
    }

    public HttpParserException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public HttpParserException(String message)
    {
        super(message);
    }

    public HttpParserException(Throwable cause)
    {
        super(cause);
    }

}
