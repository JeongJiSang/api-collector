package com.jbground.collector;

import com.jbground.parser.json.CommonJsonHandler;
import com.jbground.parser.json.CommonJsonParser;
import com.jbground.parser.xml.AttributeSaxHandler;
import com.jbground.parser.xml.AttributeSaxParser;
import com.jbground.parser.xml.CommonSaxHandler;
import com.jbground.parser.xml.CommonSaxParser;
import com.jbground.url.URLBuilder;

public class APICollectorFactory {

    public static APICollector createCollector(URLBuilder builder){
        APICollector collector = new APICollector();

        Parser parser = null;

        switch (builder.getFormat()) {
            case JSON:
                parser = new CommonJsonParser(new CommonJsonHandler(builder.getResponseSet()));
            case XML:
                parser =  new CommonSaxParser(new CommonSaxHandler(builder.getResponseSet()));
            case ATTR:
                parser = new AttributeSaxParser(new AttributeSaxHandler(builder.getResponseSet()));
            default:
                parser = new CommonSaxParser(new CommonSaxHandler(builder.getResponseSet()));
        }

        collector.setParser(parser);
        collector.setResponse(builder.getResponseSet());

        return collector;
    }

}
