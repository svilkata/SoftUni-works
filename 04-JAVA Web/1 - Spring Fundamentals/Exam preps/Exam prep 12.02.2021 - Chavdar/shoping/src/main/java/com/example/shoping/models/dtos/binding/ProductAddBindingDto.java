package com.example.shoping.models.dtos.binding;

import com.example.shoping.models.entities.enums.CategoryName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductAddBindingDto {
    @NotBlank(message = "Cannot be empty")
    @Size(min = 3, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;

    @NotBlank(message = "Please, enter a description")
    @Size(min = 5, message = "Description should be min 5 characters")
    private String description;

    @NotNull
//    @Positive(message = "Price must be positive")
    @DecimalMin(value = "0", message = "Price must be positive")
    private BigDecimal price;

    @NotNull(message = "Please, enter a datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The datetime can not be in the past")
    private LocalDateTime neededBefore;

    @NotNull(message = "You must select the category")
    private CategoryName category;

    public ProductAddBindingDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public void setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
    }

    public CategoryName getCategory() {
        return category;
    }

    public void setCategory(CategoryName category) {
        this.category = category;
    }
}
