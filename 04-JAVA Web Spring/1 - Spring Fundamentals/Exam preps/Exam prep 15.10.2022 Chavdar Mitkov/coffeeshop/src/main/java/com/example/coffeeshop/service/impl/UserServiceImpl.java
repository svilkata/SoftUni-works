package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.entity.User;
import com.example.coffeeshop.model.service.UserServiceModelDTO;
import com.example.coffeeshop.model.view.UserViewModelDTO;
import com.example.coffeeshop.repository.UserRepository;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public UserServiceModelDTO registerUser(UserServiceModelDTO userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        System.out.println("User Service Impl layer: " + user);


        //we save here
        return modelMapper.map(userRepository.save(user), userServiceModel.getClass());
    }

    @Override
    public UserServiceModelDTO findByUserNameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModelDTO.class))
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        this.currentUser.setId(id);
        this.currentUser.setUsername(username);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository
                .findById(id)
                .orElseThrow(null);
    }

    @Override
    public List<UserViewModelDTO> findAllUserAndCountOfTheirOrdersByCountDesc() {
        List<UserViewModelDTO> result = this.userRepository.findAllByOrdersCountDescending()
                .stream()
                .map(user -> {
                    UserViewModelDTO userViewModelDTO = new UserViewModelDTO();
                    userViewModelDTO.setUsername(user.getUsername());
                    userViewModelDTO.setCountOfOrders(user.getOrders().size());

                    return userViewModelDTO;
                })
                .collect(Collectors.toList());

        return result;
    }
}
