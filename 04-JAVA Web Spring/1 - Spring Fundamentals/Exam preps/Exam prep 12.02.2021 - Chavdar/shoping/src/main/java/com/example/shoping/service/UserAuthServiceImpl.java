package com.example.shoping.service;

import com.example.shoping.models.dtos.binding.UserLoginBindingDto;
import com.example.shoping.models.dtos.binding.UserRegistrationBindingDto;
import com.example.shoping.models.entities.UserEntity;
import com.example.shoping.repositories.UserRepository;
import com.example.shoping.security.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthServiceImpl {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser userSession;

    @Autowired
    public UserAuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, LoggedUser userSession) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userSession = userSession;
    }

    public boolean register(UserRegistrationBindingDto userRegistrationBindingDto){
        if (!userRegistrationBindingDto.getPassword().equals(userRegistrationBindingDto.getConfirmPassword())){
            return false;
        }

        //check for duplicate username in the db
        Optional<UserEntity> userOptByUsername = this.userRepository.findByUsername(userRegistrationBindingDto.getUsername());
        if (userOptByUsername.isPresent()) {
            return false;
        }

        //check for duplicate email in the db
        Optional<UserEntity> userOptByEmail = this.userRepository.findByEmail(userRegistrationBindingDto.getEmail());
        if (userOptByEmail.isPresent()) {
            return false;
        }

        UserEntity user = this.modelMapper.map(userRegistrationBindingDto, UserEntity.class);

        this.userRepository.save(user);

        return true;
    }

    public boolean login(UserLoginBindingDto userLoginBindingDto) {
        Optional<UserEntity> userOpt = this.userRepository
                .findByUsernameAndPassword(userLoginBindingDto.getUsername(), userLoginBindingDto.getPassword());

        if (userOpt.isEmpty()) {
            return false;
        }

        //actual login
        this.userSession.login(userOpt.get());

        return true;
    }
}
