package com.example.battleships.webcontrollers;

import com.example.battleships.models.dto.binding.UserLoginDto;
import com.example.battleships.models.dto.binding.UserRegistrationDto;
import com.example.battleships.security.LoggedUser;
import com.example.battleships.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {
    private AuthService authService;
    private LoggedUser loggedUser;

    @Autowired
    public AuthController(AuthService authService, LoggedUser loggedUser) {
        this.authService = authService;
        this.loggedUser = loggedUser;
    }


//    @ModelAttribute
//    public UserRegistrationDto initRegistrationDto(){
//        return new UserRegistrationDto();
//    }

    @GetMapping("/users/register")
    public String register(Model model) {
        //If user has already logged in
        if (loggedUser.getId() != null) {
            return "redirect:/home";
        }

        if (!model.containsAttribute("userRegistrationDto")) {
            model.addAttribute("userRegistrationDto", new UserRegistrationDto());
        }

        return "register";
    }

    @PostMapping("/users/register")
    public String registerConfirm(@Valid UserRegistrationDto userRegistrationDto,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //If user has already logged in
        if (loggedUser.getId() != null) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors() || !this.authService.register(userRegistrationDto)) {
            redirectAttributes.addFlashAttribute("userRegistrationDto", userRegistrationDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);

            return "redirect:/users/register";
        }

        return "redirect:/users/login";
    }


    @ModelAttribute("loginDto")
    public UserLoginDto initLoginDto() {
        return new UserLoginDto();
    }


    @GetMapping("/users/login")
    public String login(Model model) {
//        if (!model.containsAttribute("loginDto")) {
//            model.addAttribute("loginDto", new UserLoginDto());
//        }

        //If user has already logged in
        if (loggedUser.getId() != null) {
            return "redirect:/home";
        }

        return "login";
    }


    @PostMapping("/users/login")
    public String loginConfirm(@Valid UserLoginDto userLoginDto,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //If user has already logged in
        if (loggedUser.getId() != null) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDto", userLoginDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginDto", bindingResult);

            return "redirect:/users/login";
        }


        if (!this.authService.login(userLoginDto)) {
            redirectAttributes.addFlashAttribute("loginDto", userLoginDto);
            redirectAttributes.addFlashAttribute("badCredentials", true);

            return "redirect:/users/login";
        }

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String loggingOut(){
        //if user is logged-out
        if (loggedUser.getId() == null) {
            return "redirect:/";
        }

        this.loggedUser.logout();
        return "redirect:/";
    }
}
