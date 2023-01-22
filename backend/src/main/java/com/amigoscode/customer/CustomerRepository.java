package com.amigoscode.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository
        extends JpaRepository<Customer, Integer> {

    boolean existsCustomerByEmail(String email);
    boolean existsCustomerById(Integer id);
    Optional<Customer> findCustomerByEmail(String email);
}
