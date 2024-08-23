package com.example.makersharks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.makersharks.common.Response;
import com.example.makersharks.model.dto.CreateSupplierDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @PostMapping
    public Response<CreateSupplierDTO> createSupplier(@Valid @RequestBody CreateSupplierDTO createSupplierDTO) {
        return Response.success(HttpStatus.CREATED, createSupplierDTO);
    }
}
