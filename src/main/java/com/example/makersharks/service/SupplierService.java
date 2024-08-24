package com.example.makersharks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.makersharks.enums.ManufacturingProcess;
import com.example.makersharks.enums.NatureOfBusiness;
import com.example.makersharks.model.dto.CreateSupplierDTO;
import com.example.makersharks.model.entity.Supplier;
import com.example.makersharks.repository.SupplierRepository;

@Service
public class SupplierService {
    private SupplierRepository supplierRepository;

    public SupplierService() {
    }

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

    public Page<Supplier> findSuppliersByLocationAndNatureAndProcess(String location,
            String natureOfBusiness,
            String manufacturingProcess,
            Pageable pageable) {
        return supplierRepository.findSuppliersByLocationAndNatureOfBusinessAndManufacturingProcess(location,
                NatureOfBusiness.forValue(natureOfBusiness),
                ManufacturingProcess.forValue(manufacturingProcess), pageable);
    }
}
