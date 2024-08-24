package com.example.makersharks.model.entity;

import com.example.makersharks.enums.ManufacturingProcess;
import com.example.makersharks.enums.NatureOfBusiness;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "suppliers", uniqueConstraints = {
        @UniqueConstraint(name = "location_constraint", columnNames = { "location" })
}, indexes = {
        @Index(name = "index_location_nature_process", columnList = "location, nature_of_business, manufacturing_process")
})
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "nature_of_business", nullable = false)
    private NatureOfBusiness natureOfBusiness;

    @Enumerated(EnumType.STRING)
    @Column(name = "manufacturing_process", nullable = false)
    private ManufacturingProcess manufacturingProcess;

    private String website;

    public Supplier() {
    }

    public Supplier(String companyName, String location, String natureOfBusiness,
            String manufacturingProcesses, String website) {
        this.companyName = companyName;
        this.location = location;
        this.natureOfBusiness = NatureOfBusiness.valueOf(natureOfBusiness.toUpperCase());
        this.manufacturingProcess = ManufacturingProcess.valueOf(manufacturingProcesses.toUpperCase());
        this.website = website;
    }

    public Long getId() {
        return id;
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

    public ManufacturingProcess getManufacturingProcess() {
        return manufacturingProcess;
    }

    public String getWebsite() {
        return website;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setManufacturingProcess(ManufacturingProcess manufacturingProcesses) {
        this.manufacturingProcess = manufacturingProcesses;
    }

    public void setNatureOfBusiness(NatureOfBusiness natureOfBusiness) {
        this.natureOfBusiness = natureOfBusiness;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
