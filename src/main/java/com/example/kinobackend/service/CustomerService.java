package com.example.kinobackend.service;

import com.example.kinobackend.model.Customer;
import com.example.kinobackend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }
}
