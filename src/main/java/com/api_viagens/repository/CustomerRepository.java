package com.api_viagens.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.api_viagens.model.Customer;

public interface CustomerRepository extends ListCrudRepository<Customer, Long> {
    
}

