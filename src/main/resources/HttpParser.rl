package niocrawler;

import java.util.HashMap;
import java.util.Map;

// Original code from Zed A. Shaw

/** machine **/
%%{
  machine httpclient_parser;

  action mark {
  	 mark = p;
  }

  action start_field { 
  	field_start = p; 
  }

  action write_field { 
    field_len = p - field_start;
  }

  action start_value { 
  	mark = p; 
  }

  action write_value {
  	String field = new String(data, field_start, field_len);
  	String value = new String(data, mark, p - mark); 
    http_fields.put(field, value);
  }

  action reason_phrase { 
    reason_phrase = new String(data, mark, p - mark);
  }

  action status_code { 
    try
    {
        status_code = Integer.parseInt(new String(data, mark, p - mark));
    }
    catch (NumberFormatException e)
    {
        throw new HttpParserException(e);
    }
  }

  action http_version {	
    http_version = new String(data, mark, p - mark);
  }

  action chunk_size {
    // chunk_size = new String(data, mark, p - mark);
  }

  action last_chunk {
    // ?
  }

  action done { 
    int body_start = p + 1;
    body = new String(data, body_start, pe - body_start);
    fbreak;
  }

# line endings
  CRLF = ("\r\n" | "\n");

# character types
  CTL = (cntrl | 127);
  tspecials = ("(" | ")" | "<" | ">" | "@" | "," | ";" | ":" | "\\" | "\"" | "/" | "[" | "]" | "?" | "=" | "{" | "}" | " " | "\t");

# elements
  token = (ascii -- (CTL | tspecials));

  Reason_Phrase = (any -- CRLF)* >mark %reason_phrase;
  Status_Code = digit{3} >mark %status_code;
  http_number = (digit+ "." digit+) ;
  HTTP_Version = ("HTTP/" http_number) >mark %http_version ;
  Status_Line = HTTP_Version " " Status_Code " "? Reason_Phrase :> CRLF;

  field_name = token+ >start_field %write_field;
  field_value = any* >start_value %write_value;
  message_header = field_name ":" " "* field_value :> CRLF;

  Response = 	Status_Line (message_header)* (CRLF @done);

  chunk_ext_val = token+;
  chunk_ext_name = token+;
  chunk_extension = (";" chunk_ext_name >start_field %write_field %start_value ("=" chunk_ext_val >start_value)? %write_value )*;
  last_chunk = "0"? chunk_extension :> (CRLF @last_chunk @done);
  chunk_size = xdigit+;
  chunk = chunk_size >mark %chunk_size chunk_extension space* :> (CRLF @done);
  Chunked_Header = (chunk | last_chunk);

  main := Response | Chunked_Header;
}%%

public class HttpParser
{
	%% write data;
	
	public HttpParserData parse(byte[] data) 
	{
		int cs;
		int p = 0;
		int pe = data.length;
		int mark = 0;
		int field_start = 0;
		int field_len = 0;
		
		int status_code = -1;
    	String http_version = null;
    	String reason_phrase = null;
    	Map<String, String> http_fields = new HashMap<String, String>();
    	String body = null;
	
		%% write init;
		%% write exec;
		
		check(p <= pe, "buffer overflow after parsing execute");
        check(mark < data.length, "mark is after buffer end");
        check(field_len <= data.length, "field has length longer than whole buffer");
        check(field_start < data.length, "field starts after buffer end");
		
		return new HttpParserData(status_code, http_version, reason_phrase, http_fields, body);
	}
	
	private void check(boolean condition, String message)
    {
        if (!condition)
        {
            throw new HttpParserException(message);
        }
    }
}
