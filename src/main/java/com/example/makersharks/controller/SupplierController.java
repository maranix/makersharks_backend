package com.example.makersharks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.makersharks.common.Response;
import com.example.makersharks.common.ResponseErrorInfo;
import com.example.makersharks.enums.ResponseErrorCode;
import com.example.makersharks.model.dto.CreateSupplierDTO;
import com.example.makersharks.model.dto.QuerySuppliersDTO;
import com.example.makersharks.model.entity.Supplier;
import com.example.makersharks.service.SupplierService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public Response<Supplier> createSupplier(@Valid @RequestBody CreateSupplierDTO createSupplierDTO) {
        try {
            Supplier supplier = supplierService.createSupplier(createSupplierDTO);
            return Response.success(HttpStatus.CREATED, supplier);
        } catch (DataIntegrityViolationException e) {
            ResponseErrorInfo errorInfo = ResponseErrorInfo.fromCode(ResponseErrorCode.ALREADY_EXISTS);
            return Response.error(HttpStatus.CONFLICT, errorInfo);
        } catch (DataAccessException e) {
            ResponseErrorInfo errorInfo = ResponseErrorInfo.fromCode(ResponseErrorCode.NOT_FOUND);
            return Response.error(HttpStatus.NOT_FOUND, errorInfo);
        }
    }

    @GetMapping("/list")
    public Response<List<Supplier>> getAllSuppliers() {
        try {
            List<Supplier> suppliers = supplierService.getAll();
            return Response.success(HttpStatus.OK, suppliers);
        } catch (DataAccessException e) {

            ResponseErrorInfo errorInfo = ResponseErrorInfo.fromCode(ResponseErrorCode.NOT_FOUND);
            return Response.error(HttpStatus.NOT_FOUND, errorInfo);
        }
    }

    @PostMapping("/query")
    public Response<Page<Supplier>> querySuppliers(@Valid @RequestBody QuerySuppliersDTO querySuppliersDTO,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit) {
        try {
            Pageable pageable = PageRequest.of(page, limit);

            Page<Supplier> suppliers = supplierService.findSuppliersByLocationAndNatureAndProcess(
                    querySuppliersDTO.getLocation(), querySuppliersDTO.getNatureOfBusiness(),
                    querySuppliersDTO.getManufacturingProcess(), pageable);

            return Response.success(HttpStatus.OK, suppliers);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            ResponseErrorInfo errorInfo = ResponseErrorInfo.fromCode(ResponseErrorCode.NOT_FOUND);
            return Response.error(HttpStatus.NOT_FOUND, errorInfo);
        }
    }
}
