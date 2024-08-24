package com.example.makersharks.model.dto;

import com.example.makersharks.enums.ManufacturingProcess;
import com.example.makersharks.enums.NatureOfBusiness;
import com.example.makersharks.validator.Enum;

import jakarta.validation.constraints.NotEmpty;

public class CreateSupplierDTO {

    @NotEmpty
    private String companyName;

    @NotEmpty
    private String location;

    @NotEmpty
    @Enum(enumClass = NatureOfBusiness.class, message = "Invalid nature of business")
    private String natureOfBusiness;

    @NotEmpty
    @Enum(enumClass = ManufacturingProcess.class, message = "Invalid manufacturing processes")
    private String manufacturingProcess;

    private String website;

    public CreateSupplierDTO() {
    }

    public CreateSupplierDTO(String companyName, String location, String natureOfBusiness,
            String manufacturingProcess, String website) {
        this.companyName = companyName;
        this.location = location;
        this.natureOfBusiness = natureOfBusiness;
        this.manufacturingProcess = manufacturingProcess;
        this.website = website;
    }

    public String getCompanyName() {
        return companyName;
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

    public String getWebsite() {
        return website;
    }
}
