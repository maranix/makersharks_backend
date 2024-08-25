package com.example.makersharks.model.dto;

import com.example.makersharks.enums.*;
import com.example.makersharks.validator.Enum;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Query Suppliers Request Body")
public class QuerySuppliersDTO {

    private String location;

    @Enum(enumClass = NatureOfBusiness.class, nullable = true)
    private String natureOfBusiness;

    @Enum(enumClass = ManufacturingProcess.class, nullable = true)
    private String manufacturingProcess;

    public QuerySuppliersDTO() {
    }

    public QuerySuppliersDTO(String location, String natureOfBusiness,
            String manufacturingProcess) {
        this.location = location;
        this.natureOfBusiness = natureOfBusiness;
        this.manufacturingProcess = manufacturingProcess;
    }

    public String getLocation() {
        return location;
    }

    public String getNatureOfBusiness() {
        return natureOfBusiness;
    }

    public String getManufacturingProcess() {
        return manufacturingProcess;
    }
}