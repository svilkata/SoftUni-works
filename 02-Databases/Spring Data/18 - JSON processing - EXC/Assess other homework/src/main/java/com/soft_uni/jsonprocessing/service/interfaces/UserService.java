package com.soft_uni.jsonprocessing.service.interfaces;


import com.soft_uni.jsonprocessing.model.dto.UserDto.UserNameAgeAndSoldProductsDto;
import com.soft_uni.jsonprocessing.model.dto.UserDto.UserSellerDto;
import com.soft_uni.jsonprocessing.model.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void seedUsers() throws IOException;
    User findRandomUser();
    List<UserSellerDto> getAllUsersWithAtLeastOneProductSold();
    List<UserNameAgeAndSoldProductsDto> getUsersWithMoreThanOneProductSold();
    }
