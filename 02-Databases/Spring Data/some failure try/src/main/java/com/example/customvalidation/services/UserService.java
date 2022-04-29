package com.example.customvalidation.services;

import com.example.customvalidation.models.RegistrationDTO;
import com.example.customvalidation.models.User;
import com.example.customvalidation.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @Autowired
    public UserService(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public void register(RegistrationDTO dto){
        User user = this.modelMapper.map(dto, User.class);

        this.userRepository.save(user);
    }
}
