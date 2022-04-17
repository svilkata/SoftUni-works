package com.example.json_ex.productsshop.entities.users;

public class UserImportDTO {
    private String firstName;
    private String lastName;
    private int age;

    public UserImportDTO(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
}
