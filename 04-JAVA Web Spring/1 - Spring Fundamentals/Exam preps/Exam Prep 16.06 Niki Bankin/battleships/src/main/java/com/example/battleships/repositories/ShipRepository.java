package com.example.battleships.repositories;

import com.example.battleships.models.dto.binding.CreateShipDto;
import com.example.battleships.models.entities.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long> {
    Optional<ShipEntity> findByName(String name);
}
