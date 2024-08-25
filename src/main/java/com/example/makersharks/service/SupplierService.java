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

    public Page<Supplier> findSuppliersByLocationAndNatureOfBusinessAndManufacturingProcess(String location,
            String natureOfBusiness,
            String manufacturingProcess,
            Pageable pageable) {
        return supplierRepository.findSuppliersByLocationAndNatureOfBusinessAndManufacturingProcess(location,
                NatureOfBusiness.forValue(natureOfBusiness),
                ManufacturingProcess.forValue(manufacturingProcess), pageable);
    }

    public Page<Supplier> findSuppliersByLocationAndNatureOfBusiness(String location,
            String natureOfBusiness,
            Pageable pageable) {
        return supplierRepository.findSuppliersByLocationAndNatureOfBusiness(location,
                NatureOfBusiness.forValue(natureOfBusiness), pageable);
    }

    public Page<Supplier> findSuppliersByLocationAndManufacturingProcess(String location,
            String manufacturingProcess, Pageable pageable) {
        return supplierRepository.findSuppliersByLocationAndManufacturingProcess(location,
                ManufacturingProcess.forValue(manufacturingProcess),
                pageable);
    }

    public Page<Supplier> findSuppliersByNatureOfBusinessAndManufacturingProcess(
            String natureOfBusiness,
            String manufacturingProcess, Pageable pageable) {
        return supplierRepository.findSuppliersByNatureOfBusinessAndManufacturingProcess(
                NatureOfBusiness.forValue(natureOfBusiness),
                ManufacturingProcess.forValue(manufacturingProcess), pageable);
    }

    public Page<Supplier> findSuppliersByLocation(String location, Pageable pageable) {
        return supplierRepository.findSuppliersByLocation(location, pageable);
    }

    public Page<Supplier> findSuppliersByNatureOfBusiness(String natureOfBusiness, Pageable pageable) {
        return supplierRepository.findSuppliersByNatureOfBusiness(NatureOfBusiness.forValue(natureOfBusiness),
                pageable);
    }

    public Page<Supplier> findSuppliersByManufacturingProcess(String manufacturingProcess,
            Pageable pageable) {
        return supplierRepository
                .findSuppliersByManufacturingProcess(ManufacturingProcess.forValue(manufacturingProcess), pageable);
    }
}
