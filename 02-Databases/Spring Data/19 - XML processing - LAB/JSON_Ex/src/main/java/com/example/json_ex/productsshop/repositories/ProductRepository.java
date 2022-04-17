package com.example.json_ex.productsshop.repositories;

import com.example.json_ex.productsshop.entities.categories.CategoryStatsDTO;
import com.example.json_ex.productsshop.entities.products.Product;
import com.example.json_ex.productsshop.entities.products.ProductWithoutBuyerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT new com.example.json_ex.productsshop.entities.products.ProductWithoutBuyerDTO(" +
            " p.name, p.price, p.seller.firstName, p.seller.lastName)" +
            " FROM Product AS p" +
            " WHERE p.price >= :rangeStart AND p.price <= :rangeEnd AND p.buyer IS NULL" +
            " ORDER BY p.price ASC")
    List<ProductWithoutBuyerDTO> findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc
            (BigDecimal rangeStart, BigDecimal rangeEnd);

    @Query("SELECT new com.example.json_ex.productsshop.entities.categories.CategoryStatsDTO(" +
            "c.name, COUNT(p), AVG(p.price), SUM(p.price))" +
            " FROM Product p" +
            " JOIN p.categories c" +
            " GROUP BY c")
    List<CategoryStatsDTO> getCategoryStats();
}
