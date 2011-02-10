#Simple Java Crawler using NIO

This Java crawler uses NIO to fetch web pages. Only a single thread is blocked on IO. The worker threads are responsible to parse the HTTP response and the HTML body.

To manage a crawl job, you have to implement the Job class:

    public class MyJob implements Job
    {	
        @Override
        public boolean visit(URI url)
        {
            // Only visit url in myhost.com
            return url.toString().startsWith("http://www.myhost.com");
        }

        @Override
        public void process(Page page)
        {
            // Do whathever you want with the page!
        }
    }


This crawler is inspired by [Anemone](https://github.com/chriskite/anemone), [crawler-4j](http://code.google.com/p/crawler4j/), [em-http-request](https://github.com/igrigorik/em-http-request) and [Nutch](http://nutch.apache.org/). The HTTP parsing is done using a [Ragel](http://www.complang.org/ragel/) generated parser adapted from the parser written by Zed A. Shaw for Mongrel.

This code is a learning experience, it is far from being production ready!
