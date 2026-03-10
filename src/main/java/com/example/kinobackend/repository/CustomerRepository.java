package com.example.kinobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.kinobackend.model.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByPhone(String phone);
}
