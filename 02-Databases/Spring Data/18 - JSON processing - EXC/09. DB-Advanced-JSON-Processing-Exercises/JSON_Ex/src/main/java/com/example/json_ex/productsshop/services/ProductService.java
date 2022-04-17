package com.example.json_ex.productsshop.services;

import com.example.json_ex.productsshop.entities.categories.CategoryStats;
import com.example.json_ex.productsshop.entities.products.ProductWithoutBuyerDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductWithoutBuyerDTO> getProductsInPriceRangeForSell(
            float from, float to);

    List<CategoryStats> getCategoryStatistics();
}
