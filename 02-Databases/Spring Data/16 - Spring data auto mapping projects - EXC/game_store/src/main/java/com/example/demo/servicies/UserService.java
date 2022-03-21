package com.example.demo.servicies;


import com.example.demo.entities.users.LoginDTO;
import com.example.demo.entities.users.RegisterDTO;
import com.example.demo.entities.users.User;

import java.util.Optional;

public interface UserService {
    User register(RegisterDTO registerData);

    void logout();

    Optional<User> login(LoginDTO loginData);

    User getLoggedUser();
}
