package com.jbground.util;

import java.util.ArrayList;

public class StringUtil {

    public static String[] split(String target, String regex) {
        String[] str = target.split(regex);
        for (int i = 0; i < str.length; i++) {
            str[i] = str[i].trim();
        }
        return str;
    }

    public static boolean contains(String[] keys, String s){
        for(String key : keys){
            if(key.equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }

    public static String getMaxByteString(String str, int maxLen) {

        if(str == null || str.isEmpty()){
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int curLen = 0;
        String curChar;
        for (int i = 0; i < str.length(); i++) {
            curChar = str.substring(i, i + 1);
            curLen += curChar.getBytes().length;
            if (curLen > maxLen) break;
            else sb.append(curChar);
        }
        return sb.toString();
    }

    public static String[] getMaxByteStringArray(String str, int maxLen) {
        return getMaxByteStringArray(str, maxLen, -1);
    }

    public static String[] getMaxByteStringArray(String str, int maxLen, int maxArrays) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> strList = new ArrayList();
        int curLen = 0;
        String curChar;
        for (int i = 0; i < str.length(); i++) {
            curChar = str.substring(i, i + 1);
            curLen += curChar.getBytes().length;
            if (curLen > maxLen) {
                if (maxArrays == -1 || strList.size() <= maxArrays - 2) {
                    strList.add(sb.toString());
                    sb = new StringBuilder();
                    curLen = 0;
                    i--;
                } else break;
            } else sb.append(curChar);
        }
        strList.add(sb.toString());
        String[] strArr = new String[strList.size()];
        for (int i = 0; i < strList.size(); i++) {
            strArr[i] = strList.get(i);
        }
        return strArr;
    }

    public static boolean validateStringType(String value) {
        if(value == null || value.isEmpty())
            return false;

        for (char c : value.toCharArray()) {
            if (c < '0' || '9' < c) { //문자열에 숫자가 아닌 값이 하나라도 있을 경우
                return true;
            }
        }
        return false; //문자열이 전부 숫자일 경우
    }
}
