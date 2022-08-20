package com.example.battleships.services;

import com.example.battleships.models.dto.binding.UserLoginDto;
import com.example.battleships.models.dto.binding.UserRegistrationDto;
import com.example.battleships.models.entities.UserEntity;
import com.example.battleships.repositories.UserRepository;
import com.example.battleships.security.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final LoggedUser userSession;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, LoggedUser userSession,
                       ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean register(UserRegistrationDto userRegistrationDto){
        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())){
            return false;
        }

        //check for duplicate email in the db
        Optional<UserEntity> userOptByEmail = this.userRepository.findByEmail(userRegistrationDto.getEmail());
        if (userOptByEmail.isPresent()) {
            return false;
        }

        //check for duplicate username in the db
        Optional<UserEntity> userOptByUsername = this.userRepository.findByUsername(userRegistrationDto.getUsername());
        if (userOptByUsername.isPresent()) {
            return false;
        }

//        UserEntity user = modelMapper.map(userRegistrationDto, UserEntity.class);
        UserEntity user = new UserEntity();
        user.setUsername(userRegistrationDto.getUsername());
        user.setEmail(userRegistrationDto.getEmail());
        user.setFullName(userRegistrationDto.getFullName());
        user.setPassword(userRegistrationDto.getPassword());

        this.userRepository.save(user);

        return true;
    }

    public boolean login(UserLoginDto userLoginDto) {
        Optional<UserEntity> userOpt = this.userRepository
                .findByUsernameAndPassword(userLoginDto.getUsername(), userLoginDto.getPassword());

        if (userOpt.isEmpty()) {
            return false;
        }

        //actual login
        this.userSession.login(userOpt.get());

        return true;
    }
}
