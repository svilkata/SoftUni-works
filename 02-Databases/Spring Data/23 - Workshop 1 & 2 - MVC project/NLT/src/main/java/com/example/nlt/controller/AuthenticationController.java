package com.example.nlt.controller;

import com.example.nlt.models.dto.RegistrationDTO;
import com.example.nlt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AuthenticationController {
    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String registerView(RegistrationDTO registrationDTO) {
//        ModelAndView modelAndView = new ModelAndView("user/register");
//        modelAndView.addObject("registrationDTO", registrationDTO);

        return "user/register";
    }

    @PostMapping(value = "/users/register")
    public String doRegister(@Valid RegistrationDTO dto, BindingResult validationResult) {
//        RegistrationDTO dto = new RegistrationDTO("user", "pass", "pass", "mail@abv.bg");
        if (validationResult.hasErrors()) {
            return "user/register";
        }

        userService.register(dto);

        return "user/login";
    }
}
