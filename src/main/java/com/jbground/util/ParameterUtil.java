package com.jbground.util;

import com.jbground.url.ParameterType;


public class ParameterUtil {

    private static String[] service = {"service","serviceKey","key","ServiceKey","servicekey","keyset"};
    private static String[] page = {"page","pages","pageNo","cPage","cpage","pageNum","PageNo","page_no","currentPage","pageIndex","start","curPage"};
    private static String[] row ={"numOfRows","rowNum","rows","pageSize","NumOfRow","record_count","displayCount","pageUnit","limit","itemPerPage","perPage"};
    private static String[] startRow ={"firstindex","startRowNumApi","startCount"};
    private static String[] endRow ={"recordcountperpage","endRowNumApi"};
    private static String[] startDate  ={"startDate"};
    private static String[] endDate ={"endDate","end_date"};


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
