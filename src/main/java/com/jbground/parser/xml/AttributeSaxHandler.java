package com.jbground.parser.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.HashMap;
import java.util.Set;

public class AttributeSaxHandler extends AbstractSaxHandler {

    private boolean stopper = false;
    public AttributeSaxHandler(Set<String> parameter) {
        super(parameter);
    }



    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("pageNo")) {
            stopper = true;
        }

        if (qName.equalsIgnoreCase("item")) {
            String key = attributes.getValue("key");
            String value = attributes.getValue("value");
            if (parameter.contains(key)) {
                map.put(key, value.trim());
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
    }

    @Override
    public void endElement (String uri, String localName, String qName) {
        if (stopper && qName.equalsIgnoreCase("data")) {
            result.add(map);
            map = new HashMap<>();
        }
    }

}
