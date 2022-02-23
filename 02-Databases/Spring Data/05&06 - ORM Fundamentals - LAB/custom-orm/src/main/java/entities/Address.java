package entities;

import annotations.Column;
import annotations.Entity;
import annotations.Id;

@Entity(name = "addresses") //в базата данни таблица addresses
public class Address {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "street") //в базата данни колона street
    private String street;

    @Column(name = "street_number") //в базата данни колона street_number
    private int streetNumber;

    @Column(name = "city") //в базата данни колона city
    private String city;

    @Column(name = "postal_code") //в базата данни колона postal_code
    private String postalCode;

    public Address() {
    }

    public Address(int id, String street, int streetNumber, String city, String postalCode) {
        this.id = id;
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
