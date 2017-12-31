package com.onarinskyi.environment;

import java.util.Arrays;

public enum BrowserType {

    FIREFOX("firefox"),
    CHROME("chrome"),
    MOBILE_EMULATOR_CHROME("mobileEmulatorChrome"),
    TABLET_EMULATOR_CHROME("tabletEmulatorChrome");

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
