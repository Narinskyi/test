package com.onarinskyi.environment;

import java.util.Arrays;

public enum OperatingSystem {

    WINDOWS("windows"),
    MACOS("macos");

    private String value;

    OperatingSystem(String value) {
        this.value = value;
    }

    public static OperatingSystem of(String value) {
        return Arrays.stream(OperatingSystem.values())
                .filter(constant -> constant.value.contains(value))
                .findFirst().orElse(WINDOWS);
    }

    @Override
    public String toString() {
        return "OperatingSystem{" +
                "value='" + value + '\'' +
                '}';
    }
}
