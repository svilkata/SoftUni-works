package com.example.json_ex.productsshop.entities.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories-stats")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class XMLCategoryStatsList {
    /**
     * Usage
     * The @XmlElementWrapper annotation can be used with the following program elements:
     * JavaBean property
     * non static, non transient field
     * The usage is subject to the following constraints:
     * The property must be a collection property
     * This annotation can be used with the following annotations: XmlElement, XmlElements, XmlElementRef, XmlElementRefs, XmlJavaTypeAdapter.
     */
    @XmlElementWrapper(name = "categories")
    private List<XMLCategoryStatsDTO> stats;

    public XMLCategoryStatsList() {
    }

    public XMLCategoryStatsList(List<XMLCategoryStatsDTO> stats) {
        this.stats = stats;
    }
}
