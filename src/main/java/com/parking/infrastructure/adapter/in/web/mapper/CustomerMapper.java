package com.parking.infrastructure.adapter.in.web.mapper;

import com.parking.domain.model.Customer;
import com.parking.infrastructure.adapter.in.web.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    
    public CustomerDto toDto(Customer domain) {
        return CustomerDto.builder()
                .id(domain.getId())
                .name(domain.getName())
                .email(domain.getEmail())
                .phone(domain.getPhone())
                .address(domain.getAddress())
                .type(domain.getType())
                .build();
    }
    
    public Customer toDomain(CustomerDto dto) {
        return Customer.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .type(dto.getType())
                .build();
    }
}