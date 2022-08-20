package com.example.coffeeshop.model.service;

//a class for showing the best practices - we make modelMapper between the layers a few more times :)
// Ако имаме голямо приложение, то на всеки layer може да има нужда от различен клас на User-a :) да, бе да
public class UserServiceModelDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String username;

    public UserServiceModelDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
