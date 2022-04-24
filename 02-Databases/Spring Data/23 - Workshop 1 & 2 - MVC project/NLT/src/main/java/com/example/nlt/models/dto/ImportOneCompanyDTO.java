package com.example.nlt.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportOneCompanyDTO {
    @XmlAttribute
    private String name;

    public ImportOneCompanyDTO() {
    }

    public String getName() {
        return name;
    }
}
