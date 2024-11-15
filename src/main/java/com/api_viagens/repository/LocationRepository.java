package com.api_viagens.repository;

import com.api_viagens.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByName(String name);
    Optional<Location> findByNickname(String nickname);
    List<Location> findByCity(String city);
}
