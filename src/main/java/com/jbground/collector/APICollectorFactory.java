package com.jbground.collector;

import com.jbground.parser.json.CommonJsonHandler;
import com.jbground.parser.json.CommonJsonParser;
import com.jbground.parser.xml.AttributeSaxHandler;
import com.jbground.parser.xml.AttributeSaxParser;
import com.jbground.parser.xml.CommonSaxHandler;
import com.jbground.parser.xml.CommonSaxParser;
import com.jbground.url.URLBuilder;

import java.util.Set;

public class APICollectorFactory {

    private Set<String> response;
    private int index;

    public static APICollector createCollector(URLBuilder builder){
        APICollector collector = new APICollector();

        Parser parser = null;

        switch (builder.getFormat()) {
            case JSON:
                parser = new CommonJsonParser(new CommonJsonHandler(response), index);
            case XML:
                parser =  new CommonSaxParser(new CommonSaxHandler(response), index);
            case ATTR:
                parser = new AttributeSaxParser(new AttributeSaxHandler(response), index);
            default:
                parser = new CommonSaxParser(new CommonSaxHandler(response), index);
        }


        collector.setParser(parser);
        collector.setResponse(builder.getResponseSet());

        return collector;
    }

}
