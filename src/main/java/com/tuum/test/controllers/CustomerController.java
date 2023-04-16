package com.tuum.test.controllers;

import com.tuum.test.models.Customer;
import com.tuum.test.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping()
    public Customer create(@RequestBody Customer customer) {
        customerRepository.createCustomer(customer);
        return customer;
    }
    @GetMapping()
    public List<Customer> getAll() {
        return customerRepository.allCustomers();
    }
}
