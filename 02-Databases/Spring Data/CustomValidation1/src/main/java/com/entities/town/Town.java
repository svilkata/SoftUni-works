package com.entities.town;

import com.entities.country.Country;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(targetEntity = Country.class, mappedBy = "towns")
    private Set<Country> countries;

    public Town() {
        this.countries = new HashSet<>();
    }

    public Town(String name) {
        this();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Country> getCountries() {
        return Collections.unmodifiableSet(countries);
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
}
