package com.api_viagens.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api_viagens.controller.Travel;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    Travel findByOrderNumber(String orderNumber);
}
