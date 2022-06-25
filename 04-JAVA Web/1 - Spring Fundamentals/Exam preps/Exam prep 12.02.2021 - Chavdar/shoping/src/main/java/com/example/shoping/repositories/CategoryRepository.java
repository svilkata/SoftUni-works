package com.example.shoping.repositories;

import com.example.shoping.models.entities.CategoryEntity;
import com.example.shoping.models.entities.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(CategoryName category);
}
