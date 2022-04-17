package com.example.json_ex.productsshop.entities.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "category")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class XMLCategoryStatsDTO {
    @XmlElement(name = "name")
    private String category;

    private long productsCount;

    private double averagePrice;

    private BigDecimal totalRevenue;

    public XMLCategoryStatsDTO() {
    }

    public XMLCategoryStatsDTO(CategoryStatsDTO other) {
        this.category = other.getCategory();
        this.productsCount = other.getProductsCount();
        this.averagePrice = other.getAveragePrice();
        this.totalRevenue = other.getTotalRevenue();
    }

    @Override
    public String toString() {
        return "XMLCategoryStatsDTO{" +
                "category='" + category + '\'' +
                ", productsCount=" + productsCount +
                ", averagePrice=" + averagePrice +
                ", totalRevenue=" + totalRevenue +
                '}';
    }
}

