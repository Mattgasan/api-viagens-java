package com.api_viagens.service;

import com.api_viagens.model.Customer;
import com.api_viagens.model.Location;
import com.api_viagens.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.management.relation.RelationNotFoundException;

@Service
public class CustomerService {

    private final LocationRepository locationRepository;

    // Injeção via construtor
    public CustomerService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    // Método para buscar todos os locais
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    // Método para salvar um novo local
    public Location save(Location customer) {
        // Validações básicas
        validateLocation(customer);
        return locationRepository.save(customer);
    }

    // Método para buscar um local por ID
    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }

    // Método para atualizar um local
    public Location update(Long id, Customer customer) throws RelationNotFoundException {
        // Verifica se o local existe antes de tentar atualizar
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new RelationNotFoundException("Local com ID " + id + " não encontrado."));
        
        // Atualiza os campos do local
        existingLocation.setName(customer.getName());
        existingLocation.setCity(customer.getCity());
        existingLocation.setState(customer.getState());
        existingLocation.setCountry(customer.getCountry());

        // Validações
        validateLocation(existingLocation);

        return locationRepository.save(existingLocation);
    }

    // Método para excluir um local
    public void delete(Long id) throws RelationNotFoundException {
        // Verifica se o local existe antes de excluir
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new RelationNotFoundException("Local com ID " + id + " não encontrado."));
        
        locationRepository.delete(existingLocation);
    }

    // Validações adicionais para garantir que os campos essenciais estão preenchidos
    private void validateLocation(Location existingLocation) {
                        if (existingLocation.getName() == null || existingLocation.getName().isEmpty()) {
                            throw new IllegalArgumentException("O nome do local é obrigatório.");
                        }
                        if (existingLocation.getCity() == null || existingLocation.getCity().isEmpty()) {
                            throw new IllegalArgumentException("A cidade do local é obrigatória.");
                        }
                        if (existingLocation.getState() == null || existingLocation.getState().isEmpty()) {
                            throw new IllegalArgumentException("O estado do local é obrigatório.");
                        }
                        if (existingLocation.getCountry() == null || existingLocation.getCountry().isEmpty()) {
            throw new IllegalArgumentException("O país do local é obrigatório.");
        }
    }
}
