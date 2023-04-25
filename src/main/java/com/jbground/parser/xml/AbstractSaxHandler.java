package com.jbground.parser.xml;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public abstract class AbstractSaxHandler extends DefaultHandler {
    protected Set<String> parameter;
    protected List<Map<String, String>> result;
    protected Map<String, String> map;
    protected StringBuffer value = new StringBuffer();

    public AbstractSaxHandler(Set<String> parameter) {
        this.parameter = parameter;
    }
    public List<Map<String, String>> getResult() {
        return result;
    }

    public void startDocument() throws SAXException {
        result = new ArrayList<>();
        map = new HashMap<>();
    }

    public void endDocument() throws SAXException {
        if (!map.isEmpty()) {
            result.add(map);
        }
    }
}
