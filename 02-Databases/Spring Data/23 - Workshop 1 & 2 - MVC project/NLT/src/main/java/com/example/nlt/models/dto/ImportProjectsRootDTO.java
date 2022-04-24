package com.example.nlt.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportProjectsRootDTO {

    @XmlElement(name = "project")
    List<ImportOneProjectDTO> projects;

    public ImportProjectsRootDTO() {
    }

    public List<ImportOneProjectDTO> getProjects() {
        return projects;
    }
}
