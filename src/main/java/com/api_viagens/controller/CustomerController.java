package com.api_viagens.controller;

import org.springframework.web.bind.annotation.*;

import com.api_viagens.model.Customer;
import com.api_viagens.repository.CustomerRepository;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/{id}")
    public com.api_viagens.repository.Customer getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    @SuppressWarnings("unchecked")
    @PostMapping
    public <S> Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.saveAll((Iterable<S>) customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }

    @PatchMapping("/{id}/status")
    public com.api_viagens.repository.Customer updateStatus(@PathVariable Long id, @RequestParam Customer.Status status) {
        com.api_viagens.repository.Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setStatus(status);
        return customerRepository.save(customer);
    }
}
