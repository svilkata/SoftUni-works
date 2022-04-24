package com.example.nlt.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCompaniesRootDTO {

    @XmlElement(name = "company")
    List<ImportOneCompanyDTO> companies;

    public ImportCompaniesRootDTO() {
    }

    public List<ImportOneCompanyDTO> getCompanies() {
        return companies;
    }
}
