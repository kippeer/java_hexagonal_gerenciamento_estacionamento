package com.parking.infrastructure.adapter.in.web;

import com.parking.application.port.in.CustomerUseCase;
import com.parking.infrastructure.adapter.in.web.dto.CustomerDto;
import com.parking.infrastructure.adapter.in.web.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerUseCase customerUseCase;
    
    @Qualifier("customerWebMapper")
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<CustomerDto> registerCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(
                customerMapper.toDto(customerUseCase.registerCustomer(customerMapper.toDomain(customerDto))),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, 
                                                     @Valid @RequestBody CustomerDto customerDto) {
        if (!id.equals(customerDto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(
                customerMapper.toDto(customerUseCase.updateCustomer(customerMapper.toDomain(customerDto)))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (customerUseCase.deleteCustomer(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        return customerUseCase.findCustomerById(id)
                .map(customer -> ResponseEntity.ok(customerMapper.toDto(customer)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerDto> getCustomerByEmail(@PathVariable String email) {
        return customerUseCase.findCustomerByEmail(email)
                .map(customer -> ResponseEntity.ok(customerMapper.toDto(customer)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerUseCase.findAllCustomers().stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(customers);
    }
}