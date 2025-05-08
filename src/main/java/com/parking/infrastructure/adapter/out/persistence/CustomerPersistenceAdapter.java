package com.parking.infrastructure.adapter.out.persistence;

import com.parking.application.port.out.CustomerRepository;
import com.parking.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerPersistenceAdapter implements CustomerRepository {
    private final CustomerJpaRepository customerJpaRepository;
    
    @Qualifier("customerPersistenceMapper")
    private final CustomerMapper customerMapper;

    @Override
    public Customer save(Customer customer) {
        CustomerJpaEntity entity = customerMapper.toEntity(customer);
        CustomerJpaEntity savedEntity = customerJpaRepository.save(entity);
        return customerMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerJpaRepository.findById(id)
                .map(customerMapper::toDomain);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerJpaRepository.findByEmail(email)
                .map(customerMapper::toDomain);
    }

    @Override
    public List<Customer> findAll() {
        return customerJpaRepository.findAll().stream()
                .map(customerMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        customerJpaRepository.deleteById(id);
    }
}