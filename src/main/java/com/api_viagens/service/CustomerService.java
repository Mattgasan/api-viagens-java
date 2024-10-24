package com.api_viagens.service;

import com.api_viagens.model.Customer;
import com.api_viagens.repository.CustomerRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> update(Long id, Customer customer) {
        if (customerRepository.existsById(id)) {
            customer.setId(id);
            return Optional.of(customerRepository.save(customer));
        }
        return Optional.empty();
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
