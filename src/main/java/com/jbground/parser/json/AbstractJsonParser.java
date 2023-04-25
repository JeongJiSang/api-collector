package com.jbground.parser.json;

import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class AbstractJsonParser {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected int index;
    protected AbstractJsonHandler handler;
    protected List<Map<String, String>> result;
    protected boolean hasNext = true;

    public AbstractJsonParser(AbstractJsonHandler handler) {
        this.handler = handler;
    }
    public AbstractJsonParser(AbstractJsonHandler handler, int index) {
        this.handler = handler;
        this.index = index;
    }


    public List<Map<String, String>> parse(URL url) throws Exception {

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(100000);
        conn.setReadTimeout(100000);
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        conn.setInstanceFollowRedirects(false);

        if(conn.getResponseCode() != 200)
            logger.error("[{}]HttpResponseCode: {}, {}", index, conn.getResponseCode(), url);

        InputStreamReader inputReader = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
        BufferedReader viewRd = new BufferedReader(inputReader);

        StringBuilder viewSb = new StringBuilder();
        String viewLine;

        while ((viewLine = viewRd.readLine()) != null) {
            viewSb.append(viewLine);
        }

        JSONParser jsonParser = new JSONParser();
        jsonParser.parse(viewSb.toString(), handler);

        if(handler.getResult().isEmpty())
            hasNext = false;

        if(logger.isDebugEnabled())
            logger.debug("[{}]{}, count : {}", index, url, handler.getResult().size());

        return handler.getResult();
    }


    public boolean hasNext() {
        return hasNext;
    }

}
