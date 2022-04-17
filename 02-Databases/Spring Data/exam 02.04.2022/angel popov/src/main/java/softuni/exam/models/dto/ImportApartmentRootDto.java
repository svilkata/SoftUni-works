package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "apartments")
public class ImportApartmentRootDto {

    @XmlElement(name = "apartment")
    private List<ImportApartmentDto> apartmentDtoList;

    public List<ImportApartmentDto> getApartmentDtoList() {
        return apartmentDtoList;
    }
}
