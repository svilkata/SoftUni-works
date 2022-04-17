package com.example.xml_exc.productsshop.entities.products;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

//<product name="TRAMADOL HYDROCHLORIDE" price="516.46" seller="Christine Gomez" />
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductWithAttributesDTO {
    @XmlAttribute
    private String name;

    @XmlAttribute
    private BigDecimal price;

    @XmlAttribute
    private String seller;

    public ProductWithAttributesDTO() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
