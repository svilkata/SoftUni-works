package com.example.nlt.models.dto;

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportOneEmployeeDTO {
    @XmlElement(name = "first-name")
    private String firstName;

    @XmlElement(name = "last-name")
    private String lastName;

    @XmlElement
    @Min(18)
    private int age;

    @XmlElement
    private ImportOneProjectDTO project;

    public ImportOneEmployeeDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public ImportOneProjectDTO getProject() {
        return project;
    }
}
