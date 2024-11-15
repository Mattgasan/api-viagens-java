package com.api_viagens.exception;

import org.springframework.stereotype.Service;

import com.api_viagens.model.Location;
import com.api_viagens.repository.LocationRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EntityNotFound {

    private final LocationRepository locationRepository;

    public EntityNotFound(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location findLocationById(Long id) {
        return locationRepository.findById(id)
            .orElseThrow();
    }

    public Location findLocationByNameOrNickname(String nameOrNickname) {
    return locationRepository.findByName(nameOrNickname)
        .orElseThrow(() -> new EntityNotFoundException("Localização com nome ou apelido " + nameOrNickname + " não encontrada."));
}
}
