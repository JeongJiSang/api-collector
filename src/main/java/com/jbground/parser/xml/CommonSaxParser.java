package com.jbground.parser.xml;


import com.jbground.collector.Parser;

public class CommonSaxParser extends AbstractSaxParser implements Parser {

    public CommonSaxParser(AbstractSaxHandler handler) {
        super(handler);
    }

    public CommonSaxParser(AbstractSaxHandler handler, int index) {
        super(handler, index);
    }

}
