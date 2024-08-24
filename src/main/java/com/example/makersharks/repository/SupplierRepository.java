package com.example.makersharks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.makersharks.model.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
