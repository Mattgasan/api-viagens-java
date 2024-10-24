package com.api_viagens.repository;

import javax.tools.DocumentationTool.Location;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByName(String name);
    Location findByCity(String city);
}
