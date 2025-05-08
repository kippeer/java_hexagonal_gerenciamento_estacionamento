package com.parking.application.service;

import com.parking.application.port.in.CustomerUseCase;
import com.parking.application.port.out.CustomerRepository;
import com.parking.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerUseCase {
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer registerCustomer(Customer customer) {
        // Check if customer with same email already exists
        customerRepository.findByEmail(customer.getEmail())
                .ifPresent(c -> {
                    throw new IllegalArgumentException("Customer with email already exists: " + customer.getEmail());
                });
        
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public Customer updateCustomer(Customer customer) {
        // Verify customer exists
        customerRepository.findById(customer.getId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + customer.getId()));
        
        // Check if updating to an email that already exists for another customer
        customerRepository.findByEmail(customer.getEmail())
                .ifPresent(c -> {
                    if (!c.getId().equals(customer.getId())) {
                        throw new IllegalArgumentException("Another customer with this email already exists: " + customer.getEmail());
                    }
                });
        
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public boolean deleteCustomer(Long customerId) {
        // Verify customer exists
        customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + customerId));
        
        customerRepository.delete(customerId);
        return true;
    }

    @Override
    public Optional<Customer> findCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }
}