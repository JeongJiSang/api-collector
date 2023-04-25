package com.jbground.url;

import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class URLBuilder {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Format format;
    private String spec;
    private final Set<String> response = new HashSet<>();
    private final List<Parameter> parameters = new ArrayList<>();
    private final Set<String> keys = new HashSet<>();

    public final static String XML = "xml";
    public final static String JSON = "json";
    public final static String ATTR = "attr";


    public URLBuilder() {}

    public URLBuilder(String spec) {
        setURL(spec);
    }

    public URLBuilder(URL url) {
        setURL(url);
    }

    public void setURL(URL url) {
        setURL(url.toString());
    }

    public void setURL(String url) {
        try {
            validation(url);

            StringTokenizer token = new StringTokenizer(url, "?");

            this.spec = token.nextToken();

            if (token.hasMoreTokens()) {
                StringTokenizer param = new StringTokenizer(token.nextToken(), "&");
                while (param.hasMoreTokens()) {
                    Parameter parameter = new Parameter(param.nextToken());
                    parameters.add(parameter);
                }
            }

        } catch (MalformedURLException e) {
            logger.error("url에 문제가 있습니다.", e);
        }

    }

    public void addServiceKey(String value) {
        Parameter target = findParameterByType(ParameterType.SERVICE);
        if(target != null){
            target.setValue(value);
        }
    }

    public void setServiceKey(String key, String value) {
        Parameter target = findParameterByKey(key);

        if(target == null){
            parameters.add(new Parameter(key, value, ParameterType.SERVICE));
        }else{
            target.setType(ParameterType.SERVICE);
        }
    }

    public void setFormat(String format){
        Format form = Format.findFormat(format);
        if (form == null) {
            this.format = Format.XML;
        } else {
            this.format = form;
        }
    }

    public void setParameterData(ParameterType type, String value) {
        Parameter parameterByType = findParameterByType(type);
        assert parameterByType != null;
        parameterByType.setValue(value);
    }

    public void setResponse(String[] arr) {
        setResponse(Arrays.asList(arr));
    }

    public void setResponse(List<String> list) {
        this.response.addAll(list);
    }

    public URL build() throws Exception {
        StringBuilder queryString = new StringBuilder();
        String pathString = spec;

        for (Parameter p : parameters) {

            if (p.getType() == ParameterType.DEPENDANT && pathString.contains(p.getKey())) {
                pathString = pathString.replace(p.getKey(), p.getValue());

            } else {

                if (queryString.length() > 0)
                    queryString.append("&");

//                queryString.append(URLEncoder.encode(p.getKey(), "UTF-8")).append("=").append(p.getValue());
                queryString.append(URLEncoder.encode(p.getKey(), "UTF-8"));
                queryString.append("=");
                queryString.append(p.getValue());
            }
        }

        //파라미터 정보가 없을경우 url 리턴
        if (queryString.length() < 1)
            return new URL(pathString);
        else
            return new URL(pathString + "?" + queryString);
    }

    public Set<String> getResponseSet() {
        return this.response;
    }

    public Format getFormat() {
        return format;
    }

    private Parameter findParameterByType(ParameterType type) {

        for (Parameter p : parameters) {
            if (p.getType() == type){
                return p;
            }
        }
        return null;
    }

    private Parameter findParameterByKey(String key){
        for (Parameter p : parameters) {
            if (p.getKey().equalsIgnoreCase(key)) {
                return p;
            }
        }
        return null;
    }

    public void validation(String url) throws MalformedURLException {

        if (url == null || "".equals(url)) {
            throw new MalformedURLException("URL is empty or null.");
        }

        UrlValidator urlValidator = UrlValidator.getInstance();

        if (!urlValidator.isValid(url)) {
            throw new MalformedURLException("URL is not valid.");
        }
    }
}
