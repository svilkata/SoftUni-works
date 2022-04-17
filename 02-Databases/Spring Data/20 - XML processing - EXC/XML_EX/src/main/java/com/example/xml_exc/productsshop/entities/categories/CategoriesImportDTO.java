package com.example.xml_exc.productsshop.entities.categories;

import javax.xml.bind.annotation.*;

import java.util.List;

import static javax.xml.bind.annotation.XmlAccessType.*;
@XmlRootElement(name = "categories")
@XmlAccessorType(FIELD)
public class CategoriesImportDTO {

    @XmlElement(name = "category")
    private List<CategoryNameDTO> categories;

    public CategoriesImportDTO() {
    }

    public List<CategoryNameDTO> getCategories() {
        return categories;
    }
}
