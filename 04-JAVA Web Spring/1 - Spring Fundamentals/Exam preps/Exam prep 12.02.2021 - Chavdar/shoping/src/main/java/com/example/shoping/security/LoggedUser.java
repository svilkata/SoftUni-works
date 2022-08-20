package com.example.shoping.security;


import com.example.shoping.models.entities.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {
    private Long id;
    private String userame;

    public void login(UserEntity user){
        this.id = user.getId();
        this.userame = user.getUsername();
    }

    public LoggedUser() {
        this.id = null;
        this.userame = null;
    }

    public void logout(){
        this.id = null;
        this.userame = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserame() {
        return userame;
    }

    public void setUserame(String userame) {
        this.userame = userame;
    }
}
