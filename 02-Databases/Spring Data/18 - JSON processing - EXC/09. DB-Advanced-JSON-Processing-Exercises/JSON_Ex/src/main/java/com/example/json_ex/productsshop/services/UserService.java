package com.example.json_ex.productsshop.services;

import com.example.json_ex.productsshop.entities.users.User;
import com.example.json_ex.productsshop.entities.users.UserWithSoldProductsDTO;

import java.util.List;

public interface UserService {
    List<UserWithSoldProductsDTO> getUsersWithSoldProducts();


    List<User> getUsersWithSoldProductsOrderedByCount();
}
