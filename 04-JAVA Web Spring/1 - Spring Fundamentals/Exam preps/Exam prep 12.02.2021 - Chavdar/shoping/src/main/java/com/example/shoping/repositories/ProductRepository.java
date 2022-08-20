package com.example.shoping.repositories;

import com.example.shoping.models.entities.ProductEntity;
import com.example.shoping.models.entities.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByName(String name);

    @Query("SELECT SUM(p.price) FROM ProductEntity p")
    BigDecimal findTotalProductsPriceSum();


    List<ProductEntity> findAllByCategory_Name(CategoryName categoryName);
}
