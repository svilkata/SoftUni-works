package com.example.customvalidation.controllers;

import com.example.customvalidation.models.RegistrationDTO;
import com.example.customvalidation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private List<RegistrationDTO> usersDtos;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        this.usersDtos = new ArrayList<>();
//        this.users = Arrays.asList(
//                new User("peshi@abv.bg", "Pesho", "123"),
//                new User("goshi@abv.bg", "Gosho", "123")
//        );
    }

    @GetMapping("/") //вземи данните за всички
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        modelAndView.addObject("usersDtos", this.usersDtos);

        return modelAndView;
    }

    @PostMapping("/") //покажи всички
    public String storeContact(@Valid RegistrationDTO dto, BindingResult validationResult) {
        this.usersDtos.add(dto);

        if (validationResult.hasErrors()) {
            System.out.println("The password is not valid!!!");
        }

        this.userService.register(dto);

        return "redirect:/"; //върни ме на началната страница
    }

    @DeleteMapping("/contacts/{username}") //това взема от URL-а
    public String deleteContact(@PathVariable String username) {
        this.usersDtos = this.usersDtos
                .stream()
                .filter(c -> !c.getUsername().equals(username))
                .collect(Collectors.toList());

        return "redirect:/"; //върни ме на началната страница
    }
}
