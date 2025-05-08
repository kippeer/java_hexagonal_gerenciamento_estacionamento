package com.parking.application.port.out;

import com.parking.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    Optional<Customer> findByEmail(String email);
    List<Customer> findAll();
    void delete(Long id);
}