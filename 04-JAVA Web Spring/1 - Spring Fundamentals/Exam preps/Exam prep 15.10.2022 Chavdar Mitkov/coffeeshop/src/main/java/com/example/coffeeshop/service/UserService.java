package com.example.coffeeshop.service;

import com.example.coffeeshop.model.entity.User;
import com.example.coffeeshop.model.service.UserServiceModelDTO;
import com.example.coffeeshop.model.view.UserViewModelDTO;

import java.util.List;

public interface UserService {
    UserServiceModelDTO registerUser(UserServiceModelDTO userServiceModel);

    UserServiceModelDTO findByUserNameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    User findById(Long id);

    List<UserViewModelDTO> findAllUserAndCountOfTheirOrdersByCountDesc();
}
