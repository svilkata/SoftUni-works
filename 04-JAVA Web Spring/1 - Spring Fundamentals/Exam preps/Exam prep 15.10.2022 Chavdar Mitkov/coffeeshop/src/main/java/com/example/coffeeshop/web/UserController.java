package com.example.coffeeshop.web;

import com.example.coffeeshop.model.binding.UserLoginBindingModelDTO;
import com.example.coffeeshop.model.binding.UserRegisterBindingModelDTO;
import com.example.coffeeshop.model.service.UserServiceModelDTO;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModelDTO userRegisterBindingModelDTO,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){

        System.out.println("User Controller layer: " + userRegisterBindingModelDTO);

        if (bindingResult.hasErrors() ||
                !userRegisterBindingModelDTO.getPassword().equals(userRegisterBindingModelDTO.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userToRegister", userRegisterBindingModelDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userToRegister",
                    bindingResult);

            return "redirect:/users/register";
        }

        //save in db
        userService.registerUser(modelMapper
                .map(userRegisterBindingModelDTO, UserServiceModelDTO.class));

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login(Model model){
        if (!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
        }

        return "login.html";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModelDTO userLoginBindingModelDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userToLogin", userLoginBindingModelDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userToLogin",
                    bindingResult);

            return "redirect:login";
        }

        UserServiceModelDTO userServiceModel = userService.findByUserNameAndPassword(userLoginBindingModelDTO.getUsername(),
                userLoginBindingModelDTO.getPassword());

        //user not found
        if (userServiceModel == null) {
            redirectAttributes.addFlashAttribute("userToLogin", userLoginBindingModelDTO);
            redirectAttributes.addFlashAttribute("isFound", false);

            return "redirect:login"; //re-direct-ва към текущия път плюс login, като ни връща и сесията JSessionId
        }

        //logging user
        userService.loginUser(userServiceModel.getId(), userLoginBindingModelDTO.getUsername());

        return "redirect:/";
    }

    @ModelAttribute(name = "userToRegister")
    public UserRegisterBindingModelDTO userRegisterBindingModelDTO(){
        return new UserRegisterBindingModelDTO();
    }

    @ModelAttribute(name = "userToLogin")
    public UserLoginBindingModelDTO userLoginBindingModelDTO(){
        return new UserLoginBindingModelDTO();
    }


    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        //другият вариант е да занулим инстанцията на @Bean-а CurrentUser
//        currentUser.setId(null);
//        currentUser.setUsername(null);

        return "redirect:/";
    }
}
