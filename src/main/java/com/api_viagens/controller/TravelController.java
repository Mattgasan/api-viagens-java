package com.api_viagens.controller;

import com.api_viagens.model.Travel;
import com.api_viagens.service.TravelService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travels")
public class TravelController {

    @Autowired
    private TravelService travelService;

    @GetMapping
    public List<Travel> getAllTravels() {
        return travelService.findAll();
    }

    @PostMapping
    public ResponseEntity<Travel> createTravel(@RequestBody Travel travel) {
        // Chama a validação antes de salvar a viagem
        travelService.validateCustomerLimit(travel.getCustomer().getId(), travel.getAmount());
        travelService.validateActiveTravel(travel.getCustomer().getId());
        Travel savedTravel = travelService.save(travel);
        return ResponseEntity.ok(savedTravel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Travel> getTravelById(@PathVariable Long id) {
        return travelService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Travel> updateTravel(@PathVariable Long id, @RequestBody Travel travel) {
        // Chama a validação antes de atualizar a viagem
        travelService.validateCustomerLimit(travel.getCustomer().getId(), travel.getAmount());
        travelService.validateActiveTravel(travel.getCustomer().getId());
        return travelService.update(id, travel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTravel(@PathVariable Long id) {
        travelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
