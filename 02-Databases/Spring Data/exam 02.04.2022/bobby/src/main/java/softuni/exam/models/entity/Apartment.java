package softuni.exam.models.entity;

import softuni.exam.models.entity.enums.ApartmentType;

import javax.persistence.*;

@Entity
@Table(name = "apartment")
public class Apartment extends BaseEntity{

    private ApartmentType apartmentType;
    private double area;
    private Town town;

    public Apartment() {
    }

    @Enumerated(value = EnumType.STRING)
    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @ManyToOne
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
