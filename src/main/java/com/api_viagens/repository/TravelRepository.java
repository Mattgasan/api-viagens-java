package com.api_viagens.repository;

import com.api_viagens.model.Travel; 
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Long> {
}
