package com.api_viagens.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByName(String name);
    Customer findByBirthDate(LocalDate birthDate);
    <S> com.api_viagens.model.Customer saveAll(Iterable<S> customer);
}
