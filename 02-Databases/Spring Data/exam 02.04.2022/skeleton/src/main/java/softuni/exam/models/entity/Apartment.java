package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "apartments")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "apartment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ApartmentType apartmentType;

    @Column(nullable = false)
    private double area;

    @ManyToOne
    private Town town;

//    @OneToMany()
//    private Set<Offer> apartmentOffers;
//
//    public Set<Offer> getApartmentOffers() {
//        return apartmentOffers;
//    }
//
//    public void setApartmentOffers(Set<Offer> apartmentOffers) {
//        this.apartmentOffers = apartmentOffers;
//    }

    public Apartment() {
    }

    public long getId() {
        return id;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public double getArea() {
        return area;
    }

    public Town getTown() {
        return town;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setTown(Town town) {
        this.town = town;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Apartment apartment = (Apartment) o;
//        return id == apartment.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
