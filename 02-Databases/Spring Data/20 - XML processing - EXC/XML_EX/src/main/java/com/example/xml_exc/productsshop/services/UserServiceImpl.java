package com.example.xml_exc.productsshop.services;

import com.example.xml_exc.productsshop.entities.users.ExportOneUserWithSoldProductsDTO;
import com.example.xml_exc.productsshop.entities.users.ExportSellersDTO;
import com.example.xml_exc.productsshop.entities.users.User;
import com.example.xml_exc.productsshop.repositories.UserRepository;
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
    public ExportSellersDTO findAllWithSoldProducts() {
        List<User> users = this.userRepository.findAllWithSoldProducts();

        List<ExportOneUserWithSoldProductsDTO> dtos =
                users
                        .stream()
                        .map(u -> this.modelMapper.map(u, ExportOneUserWithSoldProductsDTO.class))
                        .collect(Collectors.toList());




        return new ExportSellersDTO(dtos);
    }
}
