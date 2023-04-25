package com.jbground.collector;

import java.net.URL;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class APICollector {

    private Parser parser;


    private Set<String> response;

    public List<Map<String, String>> collect() {


        return new CopyOnWriteArrayList<>();
    }

    public List<Map<String, String>> collect(URL url) {

        List<Map<String, String>> parse = null;
        try {
            parse = parser.parse(url);
        } catch (Exception e) {
            parse = new CopyOnWriteArrayList<>();
        }

        return parse;
    }

    public void setResponse(Set<String> response) {
        this.response = response;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }
}
