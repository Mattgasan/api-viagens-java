package com.api_viagens.controller;

import com.api_viagens.model.Customer;
import com.api_viagens.model.Location;
import com.api_viagens.service.CustomerService;
import com.api_viagens.service.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import javax.management.relation.RelationNotFoundException;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Customer> updateCustomerStatus(@PathVariable Long id,
            @RequestBody Map<String, String> updates) {
        String newStatus = updates.get("status");
        return customerService.updateStatus(id, newStatus)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) throws RelationNotFoundException {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
