package com.jbground.url;

import java.util.Arrays;

public enum Format {
    JSON("json")
    , XML("xml")
    , ATTR("attr");


    private final String format;

    Format(String format) {
        this.format = format;
    }

    public static Format findFormat(String format) {
        return Arrays.stream(values())
                .filter(value -> value.format.equalsIgnoreCase(format))
                .findAny()
                .orElse(null);
    }
}
