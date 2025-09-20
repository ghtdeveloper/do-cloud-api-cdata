package com.bluecatch.controller;

import com.bluecatch.data.dto.request.CustomerDto;
import com.bluecatch.data.dto.response.CustomerCollectionResponse;
import com.bluecatch.data.dto.response.CustomerMetricsResponse;
import com.bluecatch.data.dto.response.CustomerResponse;
import com.bluecatch.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(value = "api/v1.0/bluecatch/customer", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Customer", description = "data management API")
@SecurityRequirement(name = "apiKey")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(value = "/save")
    @Operation(summary = "Method to save an customer",
            description = "Method to save an customer")
    public ResponseEntity<CustomerResponse> save(@NotNull @Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.customerService.save(customerDto));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Search by customer  id",
            description = "Search by customer id")
    public ResponseEntity<CustomerResponse> findById(@NotNull @PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.customerService.findById(id));
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Return a list of customers",
            description = "Return a list of customers")
    public ResponseEntity<CustomerCollectionResponse> findAll(@Min(value = 0) @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                              @Min(value = 1) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll(page, pageSize));
    }

    @GetMapping(value = "/metrics")
    @Operation(summary = "Return metrics of the customer ages",
            description = "Return metrics of the customer ages")
    public ResponseEntity<CustomerMetricsResponse> findMetrics() {
        return ResponseEntity.status(HttpStatus.OK).body(this.customerService.getDetailAgeData());
    }
}
