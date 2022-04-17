package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "apartments")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportfromXMLApartmentsRootDTO {

    @XmlElement(name = "apartment")
    private List<ImportFromXMLOneAppartmentDTO> apartments;

    public ImportfromXMLApartmentsRootDTO() {
    }

    public List<ImportFromXMLOneAppartmentDTO> getApartments() {
        return apartments;
    }
}
