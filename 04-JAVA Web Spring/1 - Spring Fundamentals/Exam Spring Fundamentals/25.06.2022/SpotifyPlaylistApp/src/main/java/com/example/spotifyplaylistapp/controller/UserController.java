package com.example.spotifyplaylistapp.controller;


import com.example.spotifyplaylistapp.model.dto.binding.UserLoginBindingDto;
import com.example.spotifyplaylistapp.model.dto.binding.UserRegistrationBindingDto;
import com.example.spotifyplaylistapp.security.LoggedUser;
import com.example.spotifyplaylistapp.service.UserAuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserAuthServiceImpl userAuthService;
    private final LoggedUser loggedUser;

    @Autowired
    public UserController(UserAuthServiceImpl userAuthService, LoggedUser loggedUser) {
        this.userAuthService = userAuthService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute
    public UserRegistrationBindingDto initRegistrationDto() {
        return new UserRegistrationBindingDto();
    }

    @GetMapping("/register")
    public String register() {
        //If user has already logged in
        if (loggedUser.getId() != null) {
            return "redirect:/home";
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegistrationBindingDto userRegistrationBindingDto,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //If user has already logged in
        if (loggedUser.getId() != null) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors() || !this.userAuthService.register(userRegistrationBindingDto)) {
            redirectAttributes.addFlashAttribute("userRegistrationBindingDto", userRegistrationBindingDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegistrationBindingDto", bindingResult);

            return "redirect:register";
        }

        return "redirect:login";
    }

    //    @ModelAttribute
//    public UserLoginBindingDto initUserLoginDto(Model model) {
//        model.addAttribute("notFound", false);
//        return new UserLoginBindingDto();
//    }

    @GetMapping("/login")
    public String login(Model model) {
        //If user has already logged in
        if (loggedUser.getId() != null) {
            return "redirect:/home";
        }

        if (!model.containsAttribute("userLoginBindingDto")) {
            model.addAttribute("userLoginBindingDto", new UserLoginBindingDto());
            model.addAttribute("notFound", false);
        }

        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingDto userLoginBindingDto,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes){
        //If user has already logged in
        if (loggedUser.getId() != null) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingDto", userLoginBindingDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userLoginBindingDto", bindingResult);

            return "redirect:login";
        }

        //User login
        if (!this.userAuthService.login(userLoginBindingDto)) {
            redirectAttributes.addFlashAttribute("userLoginBindingDto", userLoginBindingDto);
            redirectAttributes.addFlashAttribute("notFound", true);

            return "redirect:login";
        }

        return "redirect:/home";
    }


    @GetMapping("/logout")
    public String loggingOut(){
        //if user is logged-out
        if (loggedUser.getId() == null) {
            this.loggedUser.logout(); //we make both name and id to be null
            return "redirect:/";
        }

        this.userAuthService.logoutUser();

        return "redirect:/";
    }

}
