package com.jbground.parser.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CommonSaxHandler extends AbstractSaxHandler {

    private String parent;  //부모태그
    private String first;   //시작태그
    private String prev;    //이전태그
    private boolean start = false;
    public CommonSaxHandler(Set<String> parameter) {
        super(parameter);
    }

    @Override
    public void startDocument() throws SAXException {
        result = new ArrayList<>();
        map = new HashMap<>();
        start = false;
        parent = "";
        first = "";
        prev = "";
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (parameter.contains(qName)) {

            //시작값 체크
            if (!start) {
                start = true;
                first = qName;
                parent = prev;
            }
        }

        prev = qName; //이전태그값 저장
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        value.append(ch, start, length);
    }

    @Override
    public void endElement (String uri, String localName, String qName) {
        if (parameter.contains(qName)) {
            map.put(qName, value.toString().trim());
        } else if (!parameter.contains(qName) && qName.equals(parent)) {
            result.add(map);
            map = new HashMap<>();
        }
        value.delete(0, value.length());  //초기화
    }

}
