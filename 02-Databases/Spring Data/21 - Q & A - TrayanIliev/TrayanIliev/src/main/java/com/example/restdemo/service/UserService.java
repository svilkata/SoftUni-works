package com.example.restdemo.service;

import com.example.restdemo.model.User;

import java.util.Collection;

public interface UserService {
    Collection<User> getUsers();
    User getUserById(Long id);
    User getUserByUsername(String username);
    User createUser(User user);
    User updateUser(User user);
    User deleteUser(Long id);
    long getUsersCount();
    //    List<User> createUsersBatch(List<User> users);
}
