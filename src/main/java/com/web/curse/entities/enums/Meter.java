package com.web.curse.entities.enums;

public enum Meter {
    NONE("none"),
    SINGLE("single"),
    DOUBLE("double");
    private final String value;
    Meter(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
