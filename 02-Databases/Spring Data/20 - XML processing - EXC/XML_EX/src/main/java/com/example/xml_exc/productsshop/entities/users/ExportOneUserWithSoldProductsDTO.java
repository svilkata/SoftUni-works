package com.example.xml_exc.productsshop.entities.users;

import com.example.xml_exc.productsshop.entities.products.ExportOneSoldProductDTO;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportOneUserWithSoldProductsDTO {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    List<ExportOneSoldProductDTO> sellingItems;

    public ExportOneUserWithSoldProductsDTO() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSellingItems(List<ExportOneSoldProductDTO> sellingItems) {
        this.sellingItems = sellingItems;
    }
}
