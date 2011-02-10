
// line 1 "../../resources/HttpParser.rl"
package niocrawler;

import java.util.HashMap;
import java.util.Map;

/** machine **/

// line 96 "../../resources/HttpParser.rl"


public class HttpParser
{
	
// line 17 "HttpParser.java"
private static byte[] init__httpclient_parser_actions_0()
{
	return new byte [] {
	    0,    1,    0,    1,    1,    1,    2,    1,    3,    1,    4,    1,
	    5,    1,    7,    1,    8,    1,   10,    2,    0,    5,    2,    2,
	    3,    2,    3,    4,    2,    4,   10,    2,    6,    0,    2,    8,
	   10,    2,    9,   10,    2,   10,    9,    3,    2,    3,    4,    3,
	    4,    9,   10,    3,    4,   10,    9,    3,    6,    0,    5,    3,
	    8,   10,    9,    4,    2,    3,    4,   10,    5,    2,    3,    4,
	    9,   10,    5,    2,    3,    4,   10,    9
	};
}

private static final byte _httpclient_parser_actions[] = init__httpclient_parser_actions_0();


private static short[] init__httpclient_parser_key_offsets_0()
{
	return new short [] {
	    0,    0,   11,   12,   24,   29,   30,   31,   43,   58,   80,   95,
	  116,  131,  153,  168,  189,  204,  223,  238,  256,  257,  258,  259,
	  260,  262,  265,  267,  270,  272,  274,  276,  279,  281,  298,  314,
	  317,  319,  320,  322
	};
}

private static final short _httpclient_parser_key_offsets[] = init__httpclient_parser_key_offsets_0();


private static char[] init__httpclient_parser_trans_keys_0()
{
	return new char [] {
	   10,   13,   48,   59,   72,   49,   57,   65,   70,   97,  102,   10,
	   10,   13,   32,   59,    9,   12,   48,   57,   65,   70,   97,  102,
	   10,   13,   32,    9,   12,   10,   10,   10,   13,   32,   59,    9,
	   12,   48,   57,   65,   70,   97,  102,   33,  124,  126,   35,   39,
	   42,   43,   45,   46,   48,   57,   65,   90,   94,  122,   10,   13,
	   32,   33,   59,   61,  124,  126,    9,   12,   35,   39,   42,   43,
	   45,   46,   48,   57,   65,   90,   94,  122,   33,  124,  126,   35,
	   39,   42,   43,   45,   46,   48,   57,   65,   90,   94,  122,   10,
	   13,   32,   33,   59,  124,  126,    9,   12,   35,   39,   42,   43,
	   45,   46,   48,   57,   65,   90,   94,  122,   33,  124,  126,   35,
	   39,   42,   43,   45,   46,   48,   57,   65,   90,   94,  122,   10,
	   13,   32,   33,   59,   61,  124,  126,    9,   12,   35,   39,   42,
	   43,   45,   46,   48,   57,   65,   90,   94,  122,   33,  124,  126,
	   35,   39,   42,   43,   45,   46,   48,   57,   65,   90,   94,  122,
	   10,   13,   32,   33,   59,  124,  126,    9,   12,   35,   39,   42,
	   43,   45,   46,   48,   57,   65,   90,   94,  122,   33,  124,  126,
	   35,   39,   42,   43,   45,   46,   48,   57,   65,   90,   94,  122,
	   10,   13,   33,   59,   61,  124,  126,   35,   39,   42,   43,   45,
	   46,   48,   57,   65,   90,   94,  122,   33,  124,  126,   35,   39,
	   42,   43,   45,   46,   48,   57,   65,   90,   94,  122,   10,   13,
	   33,   59,  124,  126,   35,   39,   42,   43,   45,   46,   48,   57,
	   65,   90,   94,  122,   84,   84,   80,   47,   48,   57,   46,   48,
	   57,   48,   57,   32,   48,   57,   48,   57,   48,   57,   48,   57,
	   10,   13,   32,   10,   13,   10,   13,   33,  124,  126,   35,   39,
	   42,   43,   45,   46,   48,   57,   65,   90,   94,  122,   33,   58,
	  124,  126,   35,   39,   42,   43,   45,   46,   48,   57,   65,   90,
	   94,  122,   10,   13,   32,   10,   13,   10,   10,   13,    0
	};
}

private static final char _httpclient_parser_trans_keys[] = init__httpclient_parser_trans_keys_0();


private static byte[] init__httpclient_parser_single_lengths_0()
{
	return new byte [] {
	    0,    5,    1,    4,    3,    1,    1,    4,    3,    8,    3,    7,
	    3,    8,    3,    7,    3,    7,    3,    6,    1,    1,    1,    1,
	    0,    1,    0,    1,    0,    0,    0,    3,    2,    5,    4,    3,
	    2,    1,    2,    0
	};
}

private static final byte _httpclient_parser_single_lengths[] = init__httpclient_parser_single_lengths_0();


private static byte[] init__httpclient_parser_range_lengths_0()
{
	return new byte [] {
	    0,    3,    0,    4,    1,    0,    0,    4,    6,    7,    6,    7,
	    6,    7,    6,    7,    6,    6,    6,    6,    0,    0,    0,    0,
	    1,    1,    1,    1,    1,    1,    1,    0,    0,    6,    6,    0,
	    0,    0,    0,    0
	};
}

private static final byte _httpclient_parser_range_lengths[] = init__httpclient_parser_range_lengths_0();


private static short[] init__httpclient_parser_index_offsets_0()
{
	return new short [] {
	    0,    0,    9,   11,   20,   25,   27,   29,   38,   48,   64,   74,
	   89,   99,  115,  125,  140,  150,  164,  174,  187,  189,  191,  193,
	  195,  197,  200,  202,  205,  207,  209,  211,  215,  218,  230,  241,
	  245,  248,  250,  253
	};
}

private static final short _httpclient_parser_index_offsets[] = init__httpclient_parser_index_offsets_0();


private static byte[] init__httpclient_parser_indicies_0()
{
	return new byte [] {
	    0,    2,    3,    5,    6,    4,    4,    4,    1,    0,    1,    8,
	    9,    7,   11,    7,   10,   10,   10,    1,   13,   14,   12,   12,
	    1,   13,    1,   15,    1,   16,   17,    7,   18,    7,   10,   10,
	   10,    1,   19,   19,   19,   19,   19,   19,   19,   19,   19,    1,
	   21,   22,   20,   23,   24,   25,   23,   23,   20,   23,   23,   23,
	   23,   23,   23,    1,   26,   26,   26,   26,   26,   26,   26,   26,
	   26,    1,   28,   29,   27,   30,   31,   30,   30,   27,   30,   30,
	   30,   30,   30,   30,    1,   32,   32,   32,   32,   32,   32,   32,
	   32,   32,    1,   33,   34,   20,   35,   36,   37,   35,   35,   20,
	   35,   35,   35,   35,   35,   35,    1,   38,   38,   38,   38,   38,
	   38,   38,   38,   38,    1,   39,   40,   27,   41,   42,   41,   41,
	   27,   41,   41,   41,   41,   41,   41,    1,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,    1,   44,   45,   46,   47,   48,   46,
	   46,   46,   46,   46,   46,   46,   46,    1,   49,   49,   49,   49,
	   49,   49,   49,   49,   49,    1,   50,   51,   52,   53,   52,   52,
	   52,   52,   52,   52,   52,   52,    1,   54,    1,   55,    1,   56,
	    1,   57,    1,   58,    1,   59,   58,    1,   60,    1,   61,   60,
	    1,   62,    1,   63,    1,   64,    1,   66,   67,   68,   65,   70,
	   71,   69,   13,   14,   72,   72,   72,   72,   72,   72,   72,   72,
	   72,    1,   73,   74,   73,   73,   73,   73,   73,   73,   73,   73,
	    1,   76,   77,   78,   75,   80,   81,   79,   82,    1,   84,   85,
	   83,    1,    0
	};
}

private static final byte _httpclient_parser_indicies[] = init__httpclient_parser_indicies_0();


private static byte[] init__httpclient_parser_trans_targs_0()
{
	return new byte [] {
	   39,    0,    2,    3,    7,   16,   20,    4,   39,    6,    7,   12,
	    4,   39,    5,   39,   39,    5,    8,    9,    4,   39,    5,    9,
	    8,   10,   11,    4,   39,    5,   11,    8,   13,   39,    6,   13,
	   12,   14,   15,   39,    6,   15,   12,   17,   39,    2,   17,   16,
	   18,   19,   39,    2,   19,   16,   21,   22,   23,   24,   25,   26,
	   27,   28,   29,   30,   31,   32,   33,   37,   38,   32,   33,   37,
	   34,   34,   35,   36,   33,   37,   35,   36,   33,   37,   33,   32,
	   33,   37
	};
}

private static final byte _httpclient_parser_trans_targs[] = init__httpclient_parser_trans_targs_0();


private static byte[] init__httpclient_parser_trans_actions_0()
{
	return new byte [] {
	   37,    0,    0,    1,    1,    0,    1,   15,   59,   15,    0,   15,
	    0,   17,    0,   40,   34,   15,   15,    3,   43,   63,   43,    0,
	   43,   22,    7,    9,   28,    9,    0,    9,    3,   74,   43,    0,
	   43,   22,    7,   51,    9,    0,    9,    3,   68,   43,    0,   43,
	   22,    7,   47,    9,    0,    9,    0,    0,    0,    0,    0,    0,
	    0,   13,    1,    0,    0,   31,   55,   55,   31,    0,   11,   11,
	    3,    0,    5,    7,   25,   25,    7,    0,    9,    9,    0,    1,
	   19,   19
	};
}

private static final byte _httpclient_parser_trans_actions[] = init__httpclient_parser_trans_actions_0();


static final int httpclient_parser_start = 1;
static final int httpclient_parser_first_final = 39;
static final int httpclient_parser_error = 0;

static final int httpclient_parser_en_main = 1;


// line 101 "../../resources/HttpParser.rl"
	
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
	
		
// line 212 "HttpParser.java"
	{
	cs = httpclient_parser_start;
	}

// line 118 "../../resources/HttpParser.rl"
		
// line 219 "HttpParser.java"
	{
	int _klen;
	int _trans = 0;
	int _acts;
	int _nacts;
	int _keys;
	int _goto_targ = 0;

	_goto: while (true) {
	switch ( _goto_targ ) {
	case 0:
	if ( p == pe ) {
		_goto_targ = 4;
		continue _goto;
	}
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
case 1:
	_match: do {
	_keys = _httpclient_parser_key_offsets[cs];
	_trans = _httpclient_parser_index_offsets[cs];
	_klen = _httpclient_parser_single_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + _klen - 1;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + ((_upper-_lower) >> 1);
			if ( data[p] < _httpclient_parser_trans_keys[_mid] )
				_upper = _mid - 1;
			else if ( data[p] > _httpclient_parser_trans_keys[_mid] )
				_lower = _mid + 1;
			else {
				_trans += (_mid - _keys);
				break _match;
			}
		}
		_keys += _klen;
		_trans += _klen;
	}

	_klen = _httpclient_parser_range_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + (_klen<<1) - 2;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + (((_upper-_lower) >> 1) & ~1);
			if ( data[p] < _httpclient_parser_trans_keys[_mid] )
				_upper = _mid - 2;
			else if ( data[p] > _httpclient_parser_trans_keys[_mid+1] )
				_lower = _mid + 2;
			else {
				_trans += ((_mid - _keys)>>1);
				break _match;
			}
		}
		_trans += _klen;
	}
	} while (false);

	_trans = _httpclient_parser_indicies[_trans];
	cs = _httpclient_parser_trans_targs[_trans];

	if ( _httpclient_parser_trans_actions[_trans] != 0 ) {
		_acts = _httpclient_parser_trans_actions[_trans];
		_nacts = (int) _httpclient_parser_actions[_acts++];
		while ( _nacts-- > 0 )
	{
			switch ( _httpclient_parser_actions[_acts++] )
			{
	case 0:
// line 10 "../../resources/HttpParser.rl"
	{
  	 mark = p;
  }
	break;
	case 1:
// line 14 "../../resources/HttpParser.rl"
	{ 
  	field_start = p; 
  }
	break;
	case 2:
// line 18 "../../resources/HttpParser.rl"
	{ 
    field_len = p - field_start;
  }
	break;
	case 3:
// line 22 "../../resources/HttpParser.rl"
	{ 
  	mark = p; 
  }
	break;
	case 4:
// line 26 "../../resources/HttpParser.rl"
	{
  	String field = new String(data, field_start, field_len);
  	String value = new String(data, mark, p - mark); 
    http_fields.put(field, value);
  }
	break;
	case 5:
// line 32 "../../resources/HttpParser.rl"
	{ 
    reason_phrase = new String(data, mark, p - mark);
  }
	break;
	case 6:
// line 36 "../../resources/HttpParser.rl"
	{ 
    try
    {
        status_code = Integer.parseInt(new String(data, mark, p - mark));
    }
    catch (NumberFormatException e)
    {
        throw new HttpParserException(e);
    }
  }
	break;
	case 7:
// line 47 "../../resources/HttpParser.rl"
	{	
    http_version = new String(data, mark, p - mark);
  }
	break;
	case 8:
// line 51 "../../resources/HttpParser.rl"
	{
    // chunk_size = new String(data, mark, p - mark);
  }
	break;
	case 9:
// line 55 "../../resources/HttpParser.rl"
	{
    // ?
  }
	break;
	case 10:
// line 59 "../../resources/HttpParser.rl"
	{ 
    int body_start = p + 1;
    body = new String(data, body_start, pe - body_start);
    { p += 1; _goto_targ = 5; if (true)  continue _goto;}
  }
	break;
// line 376 "HttpParser.java"
			}
		}
	}

case 2:
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
	if ( ++p != pe ) {
		_goto_targ = 1;
		continue _goto;
	}
case 4:
case 5:
	}
	break; }
	}

// line 119 "../../resources/HttpParser.rl"
		
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
