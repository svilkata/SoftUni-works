package com.example.demo.entities.users;

import com.google.gson.annotations.Expose;

public class UserBasicInfoDTO {
    @Expose
    private String email;

    @Expose
    private String fullName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


}
