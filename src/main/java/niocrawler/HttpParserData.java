package niocrawler;

import java.util.Collections;
import java.util.Map;

public class HttpParserData
{
    private int                 statusCode;

    private String              httpVersion;

    private String              reasonPhrase;

    private Map<String, String> httpFields;

    private String              body;

    public HttpParserData(int statusCode, String httpVersion, String reasonPhrase, Map<String, String> httpFields, String body)
    {
        this.httpFields = Collections.unmodifiableMap(httpFields);
        this.reasonPhrase = reasonPhrase;
        this.statusCode = statusCode;
        this.httpVersion = httpVersion;
        this.body = body;
    }

    public Map<String, String> getHttpFields()
    {
        return httpFields;
    }

    public String getReasonPhrase()
    {
        return reasonPhrase;
    }

    public int getStatusCode()
    {
        return statusCode;
    }

    public String getHttpVersion()
    {
        return httpVersion;
    }

    public String getBody()
    {
        return body;
    }
}
