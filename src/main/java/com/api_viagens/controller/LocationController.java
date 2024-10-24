package com.api_viagens.controller;

import org.springframework.web.bind.annotation.*;

import com.api_viagens.model.Location;
import com.api_viagens.repository.LocationRepository;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping("/{id}")
    public javax.tools.DocumentationTool.Location getLocationById(@PathVariable Long id) {
        return locationRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public <S> Location createLocation(@RequestBody Location location) {
        return locationRepository.save((Iterable<S>) location);
    }

    @PutMapping("/{id}")
    public javax.tools.DocumentationTool.Location updateLocation(@PathVariable Long id, @RequestBody Location location) {
        javax.tools.DocumentationTool.Location existing = locationRepository.findById(id).orElseThrow();
        existing.setName(location.getName());
        // Atualize os outros campos...
        return locationRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable Long id) {
        locationRepository.deleteById(id);
    }
}
