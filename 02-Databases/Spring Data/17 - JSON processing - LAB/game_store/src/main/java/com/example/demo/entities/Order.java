package com.example.demo.entities;
import com.example.demo.entities.users.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User buyer;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Game> products;

    public Order() {
        this.products = new HashSet<>();
    }

    public Order(User buyer, Set<Game> products) {
        super();
        this.buyer = buyer;
        this.products = products;
    }
}
