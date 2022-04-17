package com.soft_uni.jsonprocessing.repository;

import com.soft_uni.jsonprocessing.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal lower, BigDecimal upper);

    Set<Product> findAllBySellerIdAndBuyerIsNotNull(Long seller_id);

}
