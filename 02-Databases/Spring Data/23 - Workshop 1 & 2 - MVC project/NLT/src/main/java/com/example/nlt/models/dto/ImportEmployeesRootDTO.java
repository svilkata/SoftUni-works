package com.example.nlt.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportEmployeesRootDTO {
    @XmlElement(name = "employee")
    List<ImportOneEmployeeDTO> employees;

    public ImportEmployeesRootDTO() {
    }

    public List<ImportOneEmployeeDTO> getEmployees() {
        return employees;
    }
}
