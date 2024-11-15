package com.api_viagens.repository;

import com.api_viagens.model.Travel; 
import org.springframework.data.jpa.repository.JpaRepository;
import com.api_viagens.model.Customer;
import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    List<Travel> findByCustomer(Customer customer);

    boolean existsByCustomerIdAndStatus(Long customerId, String string);
}
