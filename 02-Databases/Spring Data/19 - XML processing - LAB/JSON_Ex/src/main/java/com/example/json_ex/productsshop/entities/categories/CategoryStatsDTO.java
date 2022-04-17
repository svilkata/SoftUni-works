package com.example.json_ex.productsshop.entities.categories;

import java.math.BigDecimal;

public class CategoryStatsDTO {
    private String category;
    private long productsCount;
    private double averagePrice;
    private BigDecimal totalRevenue;

    public CategoryStatsDTO(String category, long productsCount, double averagePrice, BigDecimal totalRevenue) {
        this.category = category;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public CategoryStatsDTO() {
    }

    public String getCategory() {
        return category;
    }

    public long getProductsCount() {
        return productsCount;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }
}
