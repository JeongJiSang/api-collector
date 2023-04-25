package com.jbground.url;

import com.jbground.util.ParameterUtil;

public class Parameter {

    protected final String key;
    protected String value;
    protected ParameterType type;

    protected Parameter(String value){
        String[] arr = value.split("=");
        this.key = arr[0];

        if(arr.length < 2){
            this.value = "xx";
        }else{
            this.value = arr[1];
        }

        this.type = ParameterUtil.findParameterType(arr[0]);
    }

    protected Parameter(String key, String value, ParameterType type) {
        this.key = key;
        this.value = value;
        this.type = type;
    }

    protected String getKey() {
        return key;
    }

    protected String getValue() {
        return value;
    }

    protected void setValue(String value) {
        this.value = value;
    }

    protected ParameterType getType() {
        return type;
    }

    protected void setType(ParameterType type) {
        this.type = type;
    }
}
