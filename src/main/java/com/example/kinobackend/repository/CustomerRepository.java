package com.example.kinobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.kinobackend.model.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findCustomerByPhoneContaining(String phone);

    String phone(String phone);
}
