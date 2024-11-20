package com.api_viagens.service;

import com.api_viagens.model.Customer;
import com.api_viagens.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.management.relation.RelationNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    // Injeção via construtor
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Método para buscar todos os clientes
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    // Método para buscar um cliente por ID
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    // Método para salvar um novo cliente
    public Customer save(Customer customer) {
        // Validações básicas
        validateCustomer(customer);
        return customerRepository.save(customer);
    }

    // Método para atualizar um cliente
    public Customer update(Long id, Customer updatedCustomer) throws RelationNotFoundException {
        // Verifica se o cliente existe antes de atualizar
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RelationNotFoundException("Cliente com ID " + id + " não encontrado."));

        // Atualiza os campos do cliente
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setLimitAmount(updatedCustomer.getLimitAmount());
        existingCustomer.setStatus(updatedCustomer.getStatus());

        // Validações
        validateCustomer(existingCustomer);

        return customerRepository.save(existingCustomer);
    }

    // Método para excluir um cliente
    public void delete(Long id) throws RelationNotFoundException {
        // Verifica se o cliente existe antes de excluir
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RelationNotFoundException("Cliente com ID " + id + " não encontrado."));

        customerRepository.delete(existingCustomer);
    }

    // Método para buscar clientes pelo nome
    public List<Customer> findByName(String name) {
        return customerRepository.findByName(name);
    }

    // Método para atualizar o status de um cliente
    public Customer updateStatus(Long id, String newStatus) throws RelationNotFoundException {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RelationNotFoundException("Cliente com ID " + id + " não encontrado."));

        existingCustomer.setStatus(newStatus);
        return customerRepository.save(existingCustomer);
    }

    // Validações adicionais para garantir que os campos essenciais estão
    // preenchidos
    private void validateCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
        }
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O email do cliente é obrigatório.");
        }
        if (customer.getLimitAmount() == null || customer.getLimitAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O limite do cliente deve ser um valor positivo.");
        }
    }
}
