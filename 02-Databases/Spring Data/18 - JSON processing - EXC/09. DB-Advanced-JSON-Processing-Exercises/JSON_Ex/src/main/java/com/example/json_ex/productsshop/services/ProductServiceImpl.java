package com.example.json_ex.productsshop.services;

import com.example.json_ex.productsshop.entities.categories.CategoryStats;
import com.example.json_ex.productsshop.entities.products.ProductWithoutBuyerDTO;
import com.example.json_ex.productsshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductWithoutBuyerDTO> getProductsInPriceRangeForSell(float from, float to) {
        BigDecimal rangeStart = BigDecimal.valueOf(from);
        BigDecimal rangeEnd = BigDecimal.valueOf(to);

        return this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(rangeStart, rangeEnd);
    }

    @Override
    public List<CategoryStats> getCategoryStatistics() {
        return this.productRepository.getCategoryStats();
    }
}
