package com.example.makersharks.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ManufacturingProcesses {
    MOULDING("moulding"),
    PRINTING_3D("3d_printing"),
    CASTING("casting"),
    COATING("coating");

    private String value;

    private ManufacturingProcesses(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
