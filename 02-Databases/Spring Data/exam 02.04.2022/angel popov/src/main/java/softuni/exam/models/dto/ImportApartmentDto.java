package softuni.exam.models.dto;

import softuni.exam.models.entity.ApartmentType;

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "apartment")
public class ImportApartmentDto {

    @XmlElement(name = "apartmentType")
    private ApartmentType apartmentType;

    @XmlElement(name = "area")
    @Min(40)
    private double area;

    @XmlElement(name = "town")
    private String town;

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
