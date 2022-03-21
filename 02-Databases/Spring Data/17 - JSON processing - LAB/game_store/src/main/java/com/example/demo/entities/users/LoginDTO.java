package com.example.demo.entities.users;

import com.google.gson.annotations.Expose;

public class LoginDTO {
    //TODO: validate e-mail
    @Expose
    private String email;

    @Expose
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
