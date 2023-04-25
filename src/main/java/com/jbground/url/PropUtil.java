package com.jbground.url;

import java.io.*;
import java.nio.file.Files;
import java.util.Properties;

public class PropUtil {

    private static String[] service;
    private static String[] page;
    private static String[] row;
    private static String[] startRow;
    private static String[] endRow;
    private static String[] startDate;
    private static String[] endDate;

    private PropUtil() throws IOException {
        InputStream is = Files.newInputStream(new File("application.properties").toPath());
        Properties props = new Properties();

        props.load(is);

        is.close();
        props.getProperty("default.api.page");
        props.getProperty("default.api.row");
        props.getProperty("default.api.service");
        props.getProperty("default.api.start.row");
        props.getProperty("default.api.end.row");
        props.getProperty("default.api.start.date");
        props.getProperty("default.api.end.date");

    }


    public static ParameterType findParameterType(String s){
        if(contains(service, s)){
            return ParameterType.SERVICE;
        } else if (contains(page, s)) {
            return ParameterType.PAGE;
        } else if (contains(row, s)) {
            return ParameterType.ROW;
        } else if (contains(startRow, s)) {
            return ParameterType.START_ROW;
        } else if (contains(endRow, s)) {
            return ParameterType.END_ROW;
        } else if (contains(startDate, s)) {
            return ParameterType.START_DATE;
        } else if (contains(endDate, s)) {
            return ParameterType.END_DATE;
        }else{
            return ParameterType.NONE;
        }
    }

    private static boolean contains(String[] keys, String s){
        for(String key : keys){
            if(key.equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }


}
