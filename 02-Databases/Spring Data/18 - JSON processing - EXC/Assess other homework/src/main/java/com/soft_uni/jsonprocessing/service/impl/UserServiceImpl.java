package com.soft_uni.jsonprocessing.service.impl;

import com.google.gson.Gson;
import com.soft_uni.jsonprocessing.model.dto.UserDto.UserNameAgeAndSoldProductsDto;
import com.soft_uni.jsonprocessing.model.dto.UserDto.UserSeedDto;
import com.soft_uni.jsonprocessing.model.dto.UserDto.UserSellerDto;
import com.soft_uni.jsonprocessing.model.entity.User;
import com.soft_uni.jsonprocessing.repository.ProductRepository;
import com.soft_uni.jsonprocessing.repository.UserRepository;
import com.soft_uni.jsonprocessing.service.interfaces.UserService;
import com.soft_uni.jsonprocessing.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.soft_uni.jsonprocessing.constants.Constants.*;


@Service
public class UserServiceImpl implements UserService {

    private static final String USERS_FILE_PATH = "users.json";

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final ProductRepository productRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, ProductRepository productRepository) throws IOException {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.productRepository = productRepository;
    }


    @Override
    public void seedUsers() throws IOException {
        if (userRepository.count() > 0) {
            return;
        }

        String usersContent = Files.readString(Path.of(RESOURCE_FILE_PATH + USERS_FILE_PATH));
        UserSeedDto[] users = gson.fromJson(usersContent, UserSeedDto[].class);
        Arrays.stream(users)
                .filter(validationUtil::isValid)
                .map(userSeedDto -> modelMapper.map(userSeedDto, User.class))
                .forEach(userRepository::save);
    }

    @Override
    public User findRandomUser() {
        Long id = ThreadLocalRandom.current().nextLong(1, userRepository.count() + 1);
        return userRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<UserSellerDto> getAllUsersWithAtLeastOneProductSold() {
        return userRepository
                .findAllUsersWithAtLeastOneProductSoldOrderedByLastNameThenFirstName()
                .stream()
                .map(user -> {
                    user.setSoldProducts(productRepository.findAllBySellerIdAndBuyerIsNotNull(user.getId()));
                    return modelMapper.map(user, UserSellerDto.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<UserNameAgeAndSoldProductsDto> getUsersWithMoreThanOneProductSold() {
        return userRepository.findAllUsersWithAtLeastOneProductSoldOrderedByCountOfProductsDescTheLastName()
                .stream()
                .map(user -> modelMapper.map(user, UserNameAgeAndSoldProductsDto.class))
                .collect(Collectors.toList());
    }


}
