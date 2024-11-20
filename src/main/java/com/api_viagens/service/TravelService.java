package com.api_viagens.service;

import com.api_viagens.model.Customer;
import com.api_viagens.model.Travel;
import com.api_viagens.model.Travel.TravelStatus;
import com.api_viagens.repository.CustomerRepository;
import com.api_viagens.repository.TravelRepository;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TravelService {

    private final TravelRepository travelRepository;
    private final CustomerRepository customerRepository;

    public TravelService(TravelRepository travelRepository, CustomerRepository customerRepository) {
        this.travelRepository = travelRepository;
        this.customerRepository = customerRepository;
    }

    // Buscar todas as viagens
    public List<Travel> findAll() {
        return travelRepository.findAll();
    }

    // Salvar nova viagem
    public Travel save(Travel travel) {
        validateCustomerLimit(travel.getCustomer().getId(), travel.getCost());
        validateActiveTravel(travel.getCustomer().getId());
        return travelRepository.save(travel);
    }

    // Buscar viagem por ID
    public Optional<Travel> findById(Long id) {
        return travelRepository.findById(id);
    }

    // Atualizar viagem
    public Travel update(Long id, Travel travel) {
        Travel existingTravel = travelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viagem com ID " + id + " não encontrada."));
        existingTravel.setStartDate(travel.getStartDate());
        existingTravel.setEndDate(travel.getEndDate());
        existingTravel.setCost(travel.getCost());
        return travelRepository.save(existingTravel);
    }

    // Excluir viagem
    public void delete(Long id) {
        if (!travelRepository.existsById(id)) {
            throw new EntityNotFoundException("Viagem com ID " + id + " não encontrada.");
        }
        travelRepository.deleteById(id);
    }

    // Validar limite do cliente
    public void validateCustomerLimit(Long customerId, BigDecimal travelCost) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));
        if (customer.getLimitAmount().compareTo(travelCost) < 0) {
            throw new IllegalArgumentException("Limite insuficiente para a viagem.");
        }
    }

    // Validar viagem ativa
    public void validateActiveTravel(Long customerId) {
        boolean hasActiveTravel = travelRepository.existsByCustomerIdAndStatus(customerId, TravelStatus.ON_GOING);
        if (hasActiveTravel) {
            throw new IllegalArgumentException("Cliente já possui viagem ativa.");
        }
    }

    // Adicionar links (HATEOAS)
    public Travel addLinks(Travel travel) {
        travel.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(TravelController.class).getTravelById(travel.getId()))
                .withSelfRel());
        travel.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(TravelController.class).getAllTravels())
                .withRel("allTravels"));
        return travel;
    }
}
