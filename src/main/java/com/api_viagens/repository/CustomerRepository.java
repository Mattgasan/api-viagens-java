package com.api_viagens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api_viagens.model.Customer;
import java.time.LocalDate;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByName(String name);

    List<Customer> findByBirthDate(LocalDate birthDate);
}
