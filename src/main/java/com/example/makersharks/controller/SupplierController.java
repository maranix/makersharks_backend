package com.example.makersharks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

// TODO: Handle ResponseEntity<Response> some other way, current solution for producing correct
//       http status codes in responses is very convulated and not really readable at all.
//
//       Don't really have enough time to figure out a solution so let's keep it that way.
//       Can be a good point for discussion in the interview.
@RestController
@RequestMapping("/api/suppliers")
@Tag(name = "Supplier")
public class SupplierController {

  private SupplierService supplierService;

  @Autowired
  public SupplierController(SupplierService supplierService) {
    this.supplierService = supplierService;
  }

  @Operation(description = "Create Supplier")
  @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = """
      {
      "company_name": "some company",
      "website": "",
      "location": "New York",
      "nature_of_business": "small_scale",
      "manufacturing_process": "casting"
      }
      """)))
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "Response on successful operation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = {
          @ExampleObject(value = """
              {
              "status": 201,
              "success": true,
              "data": {
              "id": 7,
              "company_name": "some company",
              "location": "Washington D.C",
              "nature_of_business": "small_scale",
              "manufacturing_process": "casting",
              "website": ""
              }
              }
              """) })),
      @ApiResponse(responseCode = "409", description = "Response if company withlocation already exists", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = {
          @ExampleObject(value = """
              {
                "status": 409,
                "success": false,
                "error": {
                  "code": "E0004",
                  "description": "Already Exists",
                  "message": "The resource already exists or conflicts with other resources."
                }
              }
              """) })),
      @ApiResponse(responseCode = "422", description = "Response if field validation fails. Returns a map of failed field names with reason cause.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = {
          @ExampleObject(value = """
              {
              "status": 422,
              "success": false,
              "error": {
              "code": "E0003",
              "description": "Validation Error",
              "message": "Validation failed for one or more fields.",
              "field_errors": {
              "nature_of_business": "Invalid nature of business",
              "company_name": "must not be empty",
              "location": "must not be empty",
              "manufacturing_process": "must not be empty"
              }
              }
              }
              """) })),
  })

  @PostMapping
  public ResponseEntity<Response<Supplier>> createSupplier(@Valid @RequestBody CreateSupplierDTO createSupplierDTO) {
    try {
      Supplier supplier = supplierService.createSupplier(createSupplierDTO);
      return ResponseEntity.ok(Response.success(HttpStatus.CREATED, supplier));
    } catch (DataIntegrityViolationException e) {
      ResponseErrorInfo errorInfo = ResponseErrorInfo.fromCode(ResponseErrorCode.ALREADY_EXISTS);
      return ResponseEntity.status(HttpStatus.CONFLICT).body(Response.error(HttpStatus.CONFLICT, errorInfo));
    } catch (DataAccessException e) {
      ResponseErrorInfo errorInfo = ResponseErrorInfo.fromCode(ResponseErrorCode.NOT_FOUND);
      return ResponseEntity.status(HttpStatus.CONFLICT).body(Response.error(HttpStatus.NOT_FOUND, errorInfo));
    }
  }

  @Operation(description = "Get all suppliers")
  @ApiResponse(responseCode = "200", description = "Returns a list of all available suppliers", content = {
      @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = {
          @ExampleObject(value = """
              {
                  "status": 200,
                  "success": true,
                  "data": [
                  {
                    "id": 1,
                    "company_name": "some company",
                    "location": "India",
                    "nature_of_business": "small_scale",
                    "manufacturing_process": "casting",
                    "website": ""
                  },
                  {
                    "id": 4,
                    "company_name": "some company",
                    "location": "New York",
                    "nature_of_business": "small_scale",
                    "manufacturing_process": "casting",
                    "website": ""
                  }
                  ]
              }
              """)
      })
  })
  @GetMapping("/list")
  public ResponseEntity<Response<List<Supplier>>> getAllSuppliers() {
    try {
      List<Supplier> suppliers = supplierService.getAll();
      return ResponseEntity.ok(Response.success(HttpStatus.OK, suppliers));
    } catch (DataAccessException e) {
      ResponseErrorInfo errorInfo = ResponseErrorInfo.fromCode(ResponseErrorCode.NOT_FOUND);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.error(HttpStatus.NOT_FOUND, errorInfo));
    }
  }

  @Operation(description = "Query Supplier", parameters = {
      @Parameter(name = "page", in = ParameterIn.QUERY, description = "page number", example = "1"),
      @Parameter(name = "limit", in = ParameterIn.QUERY, description = "Amount of items to fetch", example = "10")

  })

  @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = """
      {
      "location": "New York",
      "nature_of_business": "small_scale",
      "manufacturing_process": "casting"
      }
      """)))
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Fetches and returns a paginated list of Suppliers based on given filters", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = {
          @ExampleObject(value = """
                  {
                    "status": 200,
                    "success": true,
                    "data": {
                      "content": [],
                      "pageable": {
                        "page_number": 0,
                        "page_size": 10,
                        "sort": {
                          "empty": true,
                          "sorted": false,
                          "unsorted": true
                        },
                        "offset": 0,
                        "paged": true,
                        "unpaged": false
                      },
                      "last": true,
                      "total_elements": 0,
                      "total_pages": 0,
                      "size": 10,
                      "number": 0,
                      "sort": {
                        "empty": true,
                        "sorted": false,
                        "unsorted": true
                      },
                      "number_of_elements": 0,
                      "first": true,
                      "empty": true
                    }
                  }
              """)
      })),
      @ApiResponse(responseCode = "422", description = "Response if field validation fails. Returns a map of failed field names with reason cause.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = {
          @ExampleObject(value = """
              {
              "status": 422,
              "success": false,
              "error": {
              "code": "E0003",
              "description": "Validation Error",
              "message": "Validation failed for one or more fields.",
              "field_errors": {
              "nature_of_business": "Invalid nature of business",
              "location": "must not be empty",
              "manufacturing_process": "must not be empty"
              }
              }
              }
              """)
      })),
  })
  @PostMapping("/query")
  public ResponseEntity<Response<Page<Supplier>>> querySuppliers(
      @Valid @RequestBody QuerySuppliersDTO querySuppliersDTO,
      @RequestParam(name = "page", defaultValue = "0") Integer page,
      @RequestParam(name = "limit", defaultValue = "10") Integer limit) {
    try {
      Pageable pageable = PageRequest.of(page, limit);

      Page<Supplier> suppliers = supplierService.findSuppliersByLocationAndNatureAndProcess(
          querySuppliersDTO.getLocation(), querySuppliersDTO.getNatureOfBusiness(),
          querySuppliersDTO.getManufacturingProcess(), pageable);

      return ResponseEntity.ok(Response.success(HttpStatus.OK, suppliers));
    } catch (DataAccessException e) {
      ResponseErrorInfo errorInfo = ResponseErrorInfo.fromCode(ResponseErrorCode.NOT_FOUND);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.error(HttpStatus.NOT_FOUND, errorInfo));
    }
  }
}
