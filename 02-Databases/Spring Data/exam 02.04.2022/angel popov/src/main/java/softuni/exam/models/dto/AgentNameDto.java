package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "agent")
public class AgentNameDto {

    @XmlElement(name = "name")
    private String name;

    public String getName() {
        return name;
    }
}
