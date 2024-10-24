package com.api_viagens.service;

import com.api_viagens.model.Travel;
import com.api_viagens.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelService {
    @Autowired
    private TravelRepository travelRepository;

    public List<Travel> findAll() {
        return travelRepository.findAll();
    }

    public Travel save(Travel travel) {
        return travelRepository.save(travel);
    }

    public Optional<Travel> findById(Long id) {
        return travelRepository.findById(id);
    }

    public Optional<Travel> update(Long id, Travel travel) {
        if (travelRepository.existsById(id)) {
            travel.setId(id); 
            return Optional.of(travelRepository.save(travel));
        }
        return Optional.empty();
    }

    public void delete(Long id) {
        travelRepository.deleteById(id);
    }
}
