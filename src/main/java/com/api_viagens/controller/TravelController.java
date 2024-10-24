package com.api_viagens.controller;

import com.api_viagens.model.Travel;
import com.api_viagens.repository.TravelRepository;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/travels")
public class TravelController {

    private final TravelRepository travelRepository;

    public TravelController(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    @GetMapping("/{id}")
    public Travel getTravelById(@PathVariable Long id) {
        return travelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));
    }

    @PostMapping
    public Travel createTravel(@RequestBody Travel travel) {
        // Adicione validações de viagem aqui (ex.: verificar se cliente tem viagem em andamento).
        return travelRepository.save(travel);
    }

    @PatchMapping("/{id}/dates")
    public Travel updateDates(@PathVariable Long id, @RequestBody Travel updatedTravel) {
        Travel existing = travelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        existing.setStartDateTime(updatedTravel.getStartDateTime());
        existing.setEndDateTime(updatedTravel.getEndDateTime());

        return travelRepository.save(existing);
    }
    public LocalDateTime getStartDateTime() {
    return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

}
