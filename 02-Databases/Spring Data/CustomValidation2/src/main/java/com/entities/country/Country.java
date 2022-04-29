package com.entities.country;

import com.entities.town.Town;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(targetEntity = Town.class, cascade = CascadeType.ALL)
    private Set<Town> towns;

    public Country() {
        this.towns = new HashSet<>();
    }

    public Country(String name) {
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

    public Set<Town> getTowns() {
        return Collections.unmodifiableSet(towns);
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }
}
