package com.onarinskyi.environment;

import java.util.Arrays;

public enum BrowserType {

    FIREFOX("firefox"),
    CHROME("chrome"),
    SAFARI("safari");

    private String value;

    BrowserType(String value) {
        this.value = value;
    }

    public static BrowserType of(String value) {
        return Arrays.stream(BrowserType.values())
                .filter(constant -> constant.value.contains(value))
                .findFirst().orElse(CHROME);
    }

    @Override
    public String toString() {
        return "BrowserType{" +
                "value='" + value + '\'' +
                '}';
    }
}
