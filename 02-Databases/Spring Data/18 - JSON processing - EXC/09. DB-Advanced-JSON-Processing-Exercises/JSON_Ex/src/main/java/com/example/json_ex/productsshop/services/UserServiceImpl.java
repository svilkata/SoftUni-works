package com.example.json_ex.productsshop.services;

import com.example.json_ex.productsshop.entities.users.User;
import com.example.json_ex.productsshop.entities.users.UserWithSoldProductsDTO;
import com.example.json_ex.productsshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        modelMapper = new ModelMapper();
    }

    @Override
    @Transactional
    public List<UserWithSoldProductsDTO> getUsersWithSoldProducts() {
        List<User> allWithSoldProducts = this.userRepository.findAllWithSoldProducts();
        List<UserWithSoldProductsDTO> allMappedToDTO = allWithSoldProducts.stream()
                .map(u -> this.modelMapper.map(u, UserWithSoldProductsDTO.class))
                .collect(Collectors.toList());

        return allMappedToDTO;
    }

    @Override
    @javax.transaction.Transactional
    public List<User> getUsersWithSoldProductsOrderedByCount() {
        List<User> all = this.userRepository.findAllWithSoldProductsOrderByCount();
//        all.get(0).getSellingItems().size();

        return all;
    }
}
