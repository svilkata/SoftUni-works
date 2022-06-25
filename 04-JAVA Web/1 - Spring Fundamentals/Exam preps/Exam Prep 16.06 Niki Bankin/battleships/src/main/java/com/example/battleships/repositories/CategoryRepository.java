package com.example.battleships.repositories;

import com.example.battleships.models.entities.CategoryEntity;
import com.example.battleships.models.entities.enums.ShipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(ShipType toString);
}
