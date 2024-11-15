package com.api_viagens.service;

import com.api_viagens.model.Customer;
import com.api_viagens.model.Travel;
import com.api_viagens.repository.CustomerRepository;
import com.api_viagens.repository.TravelRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TravelService {

    private final TravelRepository travelRepository;
    private final CustomerRepository customerRepository;

    // Construtor com injeção das dependências
    public TravelService(TravelRepository travelRepository, CustomerRepository customerRepository) {
        this.travelRepository = travelRepository;
        this.customerRepository = customerRepository;
    }

    // Método para buscar todas as viagens
    public List<Travel> findAll() {
        return travelRepository.findAll();
    }

    // Método para salvar uma nova viagem
    public Travel save(Travel travel) {
        return travelRepository.save(travel);
    }

    // Método para buscar uma viagem por ID
    public Optional<Travel> findById(Long id) {
        return travelRepository.findById(id);
    }

    // Método para atualizar uma viagem existente
    public Optional<Travel> update(Long id, Travel travel) {
        if (travelRepository.existsById(id)) {
            travel.setId(id);
            return Optional.of(travelRepository.save(travel));
        }
        return Optional.empty();
    }

    // Método para excluir uma viagem por ID
    public void delete(Long id) {
        travelRepository.deleteById(id);
    }

    // Valida o limite de crédito do cliente
    public void validateCustomerLimit(Long customerId, BigDecimal travelAmount) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        
        if (customer.getLimitAmount().compareTo(travelAmount) < 0) {
            throw new IllegalArgumentException("Cliente não possui limite suficiente para a viagem.");
        }
    }

    // Verifica se o cliente já possui uma viagem em andamento
    public void validateActiveTravel(Long customerId) {
        boolean hasActiveTravel = travelRepository.existsByCustomerIdAndStatus(customerId, "ON_GOING");
        
        if (hasActiveTravel) {
            throw new IllegalArgumentException("O cliente já possui uma viagem em andamento.");
        }
    }
}
