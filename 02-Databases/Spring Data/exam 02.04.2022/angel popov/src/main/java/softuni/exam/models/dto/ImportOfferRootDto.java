package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "offers")
public class ImportOfferRootDto {

    @XmlElement(name = "offer")
    private List<ImportOfferDto> importOfferDtoList;

    public List<ImportOfferDto> getImportOfferDtoList() {
        return importOfferDtoList;
    }
}
