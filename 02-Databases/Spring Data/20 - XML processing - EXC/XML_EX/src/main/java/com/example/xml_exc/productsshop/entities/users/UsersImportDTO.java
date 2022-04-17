package com.example.xml_exc.productsshop.entities.users;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersImportDTO {

    @XmlElement(name = "user")
    private List<OneUserImportDTO> users;

    public UsersImportDTO() {
    }

    public List<OneUserImportDTO> getUsers() {
        return users;
    }
}
