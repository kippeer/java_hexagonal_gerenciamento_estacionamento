package com.parking.infrastructure.adapter.out.persistence;

import com.parking.domain.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    
    public Customer toDomain(CustomerJpaEntity entity) {
        return Customer.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .type(entity.getType())
                .build();
    }
    
    public CustomerJpaEntity toEntity(Customer domain) {
        return CustomerJpaEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .email(domain.getEmail())
                .phone(domain.getPhone())
                .address(domain.getAddress())
                .type(domain.getType())
                .build();
    }
}