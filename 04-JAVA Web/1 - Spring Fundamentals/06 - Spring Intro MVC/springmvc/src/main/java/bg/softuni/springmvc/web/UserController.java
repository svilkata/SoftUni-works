package bg.softuni.springmvc.web;

import bg.softuni.springmvc.web.model.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hello/{id}/test")
    public String hello(Model model, @PathVariable("id") Integer id){
        model.addAttribute("num", id);
        return "hello.html";
    }

   @GetMapping
    public String newUser(){
       return "newuser.html";
    }

    @PostMapping
    public String createUser(UserDto userDto){
        System.out.println("Creating new user ..." + userDto);
        return "usercreated.html";
    }
}















