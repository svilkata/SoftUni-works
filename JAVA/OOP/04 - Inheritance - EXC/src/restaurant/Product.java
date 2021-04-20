package restaurant;

import java.math.BigDecimal;

public abstract class Product {
    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal bigDecimal) {
        this.name = name;
        this.price = bigDecimal;
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
}
