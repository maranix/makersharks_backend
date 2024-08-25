package com.example.makersharks.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.makersharks.enums.ManufacturingProcess;
import com.example.makersharks.enums.NatureOfBusiness;
import com.example.makersharks.model.entity.Supplier;
import com.example.makersharks.repository.SupplierRepository;
import com.github.javafaker.Faker;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseInitializer {
    @Autowired
    private SupplierRepository supplierRepository;

    public DatabaseInitializer(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @PostConstruct
    public void init() {
        /// Do not create more than 100 example entries in the database
        if (supplierRepository.count() > 100)
            return;

        Faker faker = new Faker();

        NatureOfBusiness[] natureValues = NatureOfBusiness.values();
        ManufacturingProcess[] processValues = ManufacturingProcess.values();

        ArrayList<Supplier> suppliers = new ArrayList<>();
        for (var i = 0; i < 100; i++) {
            String nature = natureValues[faker.random().nextInt(0, natureValues.length - 1)].name();
            String process = processValues[faker.random().nextInt(0, processValues.length - 1)].name();

            Supplier supplier = new Supplier(
                    faker.funnyName().name(),
                    faker.address().city(),
                    nature,
                    process,
                    faker.internet().url());

            suppliers.add(supplier);
        }

        try {
            supplierRepository.saveAll(suppliers);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
