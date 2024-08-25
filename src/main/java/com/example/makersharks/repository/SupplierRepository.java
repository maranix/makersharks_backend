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

        public Page<Supplier> findSuppliersByLocationAndNatureOfBusiness(String location,
                        NatureOfBusiness natureOfBusiness,
                        Pageable pageable);

        public Page<Supplier> findSuppliersByLocationAndManufacturingProcess(String location,
                        ManufacturingProcess manufacturingProcess, Pageable pageable);

        public Page<Supplier> findSuppliersByNatureOfBusinessAndManufacturingProcess(
                        NatureOfBusiness natureOfBusiness,
                        ManufacturingProcess manufacturingProcess, Pageable pageable);

        public Page<Supplier> findSuppliersByLocation(String location, Pageable pageable);

        public Page<Supplier> findSuppliersByNatureOfBusiness(NatureOfBusiness natureOfBusiness, Pageable pageable);

        public Page<Supplier> findSuppliersByManufacturingProcess(ManufacturingProcess manufacturingProcess,
                        Pageable pageable);
}
