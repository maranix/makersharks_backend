package com.example.makersharks.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ManufacturingProcesses {
    MOULDING("moulding"),
    PRINTING_3D("3d_printing"),
    CASTING("casting"),
    COATING("coating");

    private final String value;

    ManufacturingProcesses(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ManufacturingProcesses forValue(String value) {
        for (ManufacturingProcesses process : ManufacturingProcesses.values()) {
            if (process.getValue().equalsIgnoreCase(value)) {
                return process;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
