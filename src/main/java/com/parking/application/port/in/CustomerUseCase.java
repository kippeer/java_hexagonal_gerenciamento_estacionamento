package com.parking.application.port.in;

import com.parking.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerUseCase {
    /**
     * Registers a new customer in the system.
     *
     * @param customer The customer to register
     * @return The registered customer with its ID
     */
    Customer registerCustomer(Customer customer);

    /**
     * Updates an existing customer's information.
     *
     * @param customer The customer with updated information
     * @return The updated customer
     */
    Customer updateCustomer(Customer customer);

    /**
     * Deletes a customer from the system.
     *
     * @param customerId The ID of the customer to delete
     * @return true if deletion is successful, false otherwise
     */
    boolean deleteCustomer(Long customerId);

    /**
     * Finds a customer by its ID.
     *
     * @param customerId The ID of the customer
     * @return An Optional containing the customer if found
     */
    Optional<Customer> findCustomerById(Long customerId);

    /**
     * Finds a customer by email.
     *
     * @param email The email of the customer
     * @return An Optional containing the customer if found
     */
    Optional<Customer> findCustomerByEmail(String email);

    /**
     * Finds all customers in the system.
     *
     * @return List of all customers
     */
    List<Customer> findAllCustomers();
}