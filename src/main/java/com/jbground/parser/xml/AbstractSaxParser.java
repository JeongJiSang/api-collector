package com.jbground.parser.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public abstract class AbstractSaxParser {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected int index;
    protected final AbstractSaxHandler handler;
    protected List<Map<String, String>> result;
    protected List<Map<String, String>> next; //다음 값을 보유
    protected boolean hasNext = true;    //true 다음 값 있음, false 다음 값 없음

    public AbstractSaxParser(AbstractSaxHandler handler) {
        this.handler = handler;
    }

    public AbstractSaxParser(AbstractSaxHandler handler, int index) {
        this.handler = handler;
        this.index = index;
    }

    public List<Map<String, String>> parse(URL url) throws Exception {

        HttpURLConnection conn = null;
        InputStreamReader inputReader = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(100000);
            conn.setReadTimeout(100000);
            conn.setRequestProperty("Accept", "application/xml");

            if(conn.getResponseCode() != 200)
                logger.error("[{}]HttpResponseCode: {}, {}", index, conn.getResponseCode(), url);

            inputReader = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
            InputSource inputSource = new InputSource(inputReader);
            inputSource.setEncoding("UTF-8");

            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            saxParser.parse(inputSource, handler);
            
            if(handler.getResult().isEmpty())
                hasNext = false;

            if(logger.isDebugEnabled())
                logger.debug("[{}]{}, count : {}", index, url, handler.getResult().size());

            return handler.getResult();
        }catch (Exception e){
            throw e;
        }finally {
            if(conn != null)
                conn.disconnect();
            if(inputReader != null)
                inputReader.close();
        }
    }

    public boolean hasNext() {
        return hasNext;
    }

}
