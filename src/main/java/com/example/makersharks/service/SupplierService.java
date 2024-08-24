package com.example.makersharks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.makersharks.model.dto.CreateSupplierDTO;
import com.example.makersharks.model.entity.Supplier;
import com.example.makersharks.repository.SupplierRepsitory;

@Service
public class SupplierService {
    private SupplierRepsitory supplierRepsitory;

    @Autowired
    public SupplierService(SupplierRepsitory supplierRepsitory) {
        this.supplierRepsitory = supplierRepsitory;
    }

    public Supplier createSupplier(CreateSupplierDTO supplierDTO) {
        Supplier supplier = new Supplier(
                supplierDTO.getCompanyName(),
                supplierDTO.getLocation(),
                supplierDTO.getNatureOfBusiness(),
                supplierDTO.getManufacturingProcesses(),
                supplierDTO.getWebsite());

        return supplierRepsitory.save(supplier);
    }

    public List<Supplier> getAll() {
        return supplierRepsitory.findAll();
    }
}
