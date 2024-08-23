package com.example.makersharks.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum NatureOfBusiness {
    SMALL_SCALE("small_scale"),
    MEDIUM_SCALE("medium_scale"),
    LARGE_SCALE("large_scale");

    private String value;

    private NatureOfBusiness(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
