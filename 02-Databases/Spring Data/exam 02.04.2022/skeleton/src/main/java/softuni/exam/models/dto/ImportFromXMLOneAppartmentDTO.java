package softuni.exam.models.dto;

import softuni.exam.models.entity.ApartmentType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportFromXMLOneAppartmentDTO {

    @XmlElement(name = "apartmentType")
    @NotNull
    private ApartmentType apartmentType;

    @XmlElement(name = "area")
    @Positive
    @Min(value = 40)
    private double area;

    @XmlElement(name = "town")
    private String town;

    public ImportFromXMLOneAppartmentDTO() {
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public double getArea() {
        return area;
    }

    public String getTown() {
        return town;
    }
}
