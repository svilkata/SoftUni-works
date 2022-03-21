package com.example.demo.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ManyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    private String name;

    private int age;

    @ManyToMany
    private Set<ManyEntity> friends;


    public void setId(int id) {
        this.id = id;
    }

    public ManyEntity() {
        this.friends = new HashSet<>();
    }

    public ManyEntity(String name, int age) {
        this();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<ManyEntity> getFriends() {
        return friends;
    }

    public void setFriends(Set<ManyEntity> friends) {
        this.friends = friends;
    }

    public int getId() {
        return id;
    }

}
