package com.example.json_ex.productsshop.entities.categories;

import java.math.BigDecimal;

public class CategoryStats {
    private String category;
    private long productsCount;
    private double averagePrice;
    private BigDecimal totalRevenue;

    public CategoryStats(String category, long productsCount, double averagePrice, BigDecimal totalRevenue) {
        this.category = category;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
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
