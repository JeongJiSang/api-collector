package com.jbground.parser.json;

import org.json.simple.parser.ContentHandler;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

public class AbstractJsonHandler implements ContentHandler {

    protected Set<String> parameter;
    protected List<Map<String, String>> result;
    protected Map<String, String> map;
    protected String prev;    //이전태그
    protected String key;
    protected boolean start = false;

    public AbstractJsonHandler(Set<String> parameter) {
        this.parameter = parameter;
    }

    @Override
    public void startJSON() throws ParseException, IOException {
        result = new LinkedList<>();
        start = false;
    }

    @Override
    public boolean startObject() throws ParseException, IOException {
        if (start)
            map = new HashMap<>();
        return true;
    }

    @Override
    public boolean startObjectEntry(String key) throws ParseException, IOException {
        this.key = key;
        return true;
    }

    @Override
    public boolean startArray() throws ParseException, IOException {
        start = true;
        return true;
    }

    @Override
    public boolean primitive(Object value) throws ParseException, IOException {

        if (start && parameter.contains(key)) {
            //시작값 체크
            if(value != null){
                String str = (String) value;
                map.put(key, str.trim());
                prev = key;
            }

        }
        return true;
    }

    @Override
    public boolean endObjectEntry() throws ParseException, IOException {
        return true;
    }

    @Override
    public boolean endObject() throws ParseException, IOException {
        result.add(map);
        return true;
    }
    @Override
    public boolean endArray() throws ParseException, IOException {
        return true;
    }

    @Override
    public void endJSON() throws ParseException, IOException {

    }

    public List<Map<String, String>> getResult() {
        return result;
    }
}
