package com.example.makersharks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.makersharks.model.dto.CreateSupplierDTO;
import com.example.makersharks.model.entity.Supplier;
import com.example.makersharks.repository.SupplierRepository;

@Service
public class SupplierService {
    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier createSupplier(CreateSupplierDTO supplierDTO) {
        Supplier supplier = new Supplier(
                supplierDTO.getCompanyName(),
                supplierDTO.getLocation(),
                supplierDTO.getNatureOfBusiness(),
                supplierDTO.getManufacturingProcess(),
                supplierDTO.getWebsite());

        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }
}
