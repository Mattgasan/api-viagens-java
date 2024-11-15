package com.api_viagens.controller;

import com.api_viagens.model.Location;
import com.api_viagens.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.management.relation.RelationNotFoundException;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Location> getAllCustomers() {
        return customerService.findAll();
    }

    @PostMapping
    public Location createCustomer(@RequestBody Location customer) {
        return customerService.save(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getCustomerById(@PathVariable Long id) {
        return customerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
    //     return customerService.update(id, customer)
    //             .map(ResponseEntity::ok)
    //             .orElse(ResponseEntity.notFound().build());
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) throws RelationNotFoundException {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
