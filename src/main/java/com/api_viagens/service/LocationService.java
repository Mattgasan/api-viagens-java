package com.api_viagens.service;

import com.api_viagens.model.Location;
import com.api_viagens.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }

    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }

    public Optional<Location> update(Long id, Location location) {
        if (locationRepository.existsById(id)) {
            location.setId(id);
            return Optional.of(locationRepository.save(location));
        }
        return Optional.empty();
    }

    public void delete(Long id) {
        locationRepository.deleteById(id);
    }
}
