package com.jbground.parser.xml;


import com.jbground.collector.Parser;

public class AttributeSaxParser extends AbstractSaxParser implements Parser {

    public AttributeSaxParser(AbstractSaxHandler handler) {
        super(handler);
    }

    public AttributeSaxParser(AbstractSaxHandler handler, int index) {
        super(handler, index);
    }

}
