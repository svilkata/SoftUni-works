package com.example.coffeeshop.model.view;

import com.example.coffeeshop.model.entity.Category;
import com.example.coffeeshop.model.entity.User;
import com.example.coffeeshop.model.enums.CategoryNameEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderViewModelDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Category category;

    public OrderViewModelDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
