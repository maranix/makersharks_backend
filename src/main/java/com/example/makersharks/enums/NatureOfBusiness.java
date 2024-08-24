package com.example.makersharks.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum NatureOfBusiness {
    SMALL_SCALE("small_scale"),
    MEDIUM_SCALE("medium_scale"),
    LARGE_SCALE("large_scale");

    private final String value;

    NatureOfBusiness(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static NatureOfBusiness forValue(String value) {
        for (NatureOfBusiness nature : NatureOfBusiness.values()) {
            if (nature.getValue().equalsIgnoreCase(value)) {
                return nature;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}