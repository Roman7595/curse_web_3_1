package com.web.curse.entities.enums;

public enum ClientRoles {
    USER("USER"),
    ADMIN("ADMIN"),
    METER("METER");
    private final String value;
    ClientRoles(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
