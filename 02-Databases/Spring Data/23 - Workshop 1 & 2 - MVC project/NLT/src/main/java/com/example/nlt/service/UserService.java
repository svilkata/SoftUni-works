package com.example.nlt.service;

import com.example.nlt.repositories.UserRepository;
import com.example.nlt.models.User;
import com.example.nlt.models.dto.RegistrationDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @Autowired
    public UserService(@Qualifier(value = "default") ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public void register(RegistrationDTO dto){
        User user = this.modelMapper.map(dto, User.class);

        this.userRepository.save(user);
    }
}
