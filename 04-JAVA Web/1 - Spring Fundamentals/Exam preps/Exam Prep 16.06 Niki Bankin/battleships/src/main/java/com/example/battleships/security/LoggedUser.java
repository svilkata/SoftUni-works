package com.example.battleships.security;

import com.example.battleships.models.entities.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {
    private Long id;

    private String fullName;

    public void login(UserEntity user){
        this.id = user.getId();
        this.fullName = user.getFullName();
    }

    public LoggedUser() {
        this.id = null;
        this.fullName = null;
    }

    public void logout(){
        this.id = null;
        this.fullName = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
