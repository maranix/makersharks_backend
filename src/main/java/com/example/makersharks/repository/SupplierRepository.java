package com.example.makersharks.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.makersharks.enums.ManufacturingProcess;
import com.example.makersharks.enums.NatureOfBusiness;
import com.example.makersharks.model.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    public Page<Supplier> findSuppliersByLocationAndNatureOfBusinessAndManufacturingProcess(String location,
            NatureOfBusiness natureOfBusiness,
            ManufacturingProcess manufacturingProcess, Pageable pageable);
}
