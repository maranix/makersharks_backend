package com.example.makersharks.model.dto;

import com.example.makersharks.enums.ManufacturingProcesses;
import com.example.makersharks.enums.NatureOfBusiness;

import jakarta.validation.constraints.NotEmpty;

public class CreateSupplierDTO {

    @NotEmpty
    private String companyName;

    @NotEmpty
    private String location;

    @NotEmpty
    private NatureOfBusiness natureOfBusiness;

    @NotEmpty
    private ManufacturingProcesses manufacturingProcesses;

    private String website;

    public CreateSupplierDTO() {
    }

    public CreateSupplierDTO(String companyName, String location, NatureOfBusiness natureOfBusiness,
            ManufacturingProcesses manufacturingProcesses, String website) {
        this.companyName = companyName;
        this.location = location;
        this.natureOfBusiness = natureOfBusiness;
        this.manufacturingProcesses = manufacturingProcesses;
        this.website = website;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getLocation() {
        return location;
    }

    public NatureOfBusiness getNatureOfBusiness() {
        return natureOfBusiness;
    }

    public ManufacturingProcesses getManufacturingProcesses() {
        return manufacturingProcesses;
    }

    public String getWebsite() {
        return website;
    }
}
