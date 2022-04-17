package com.example.xml_exc.productsshop.entities.categories;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;
@XmlRootElement(name = "category")
@XmlAccessorType(FIELD)
public class CategoryNameDTO {
    @XmlElement
    private String name;

    public CategoryNameDTO() {
    }

    public String getName() {
        return name;
    }
}
