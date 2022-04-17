package exam.model.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownsRootImportFromXMLDto {

    @XmlElement(name = "town")
    private List<TownOneImportFromXMLDto> towns;

    public TownsRootImportFromXMLDto() {
    }

    public List<TownOneImportFromXMLDto> getTowns() {
        return towns;
    }

    public void setTowns(List<TownOneImportFromXMLDto> towns) {
        this.towns = towns;
    }
}
