package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.UserRegisterDto;
import bg.softuni.mobilele.service.UserService;
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
public class UserRegistrationController {
    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userModel")
    public void initUserModel(Model model){ //изпълнява се в рамките на текущия контролер само!!!
        model.addAttribute("userModel", new UserRegisterDto());
    }

    @GetMapping("/register")
    public String register() {
        // когато зареждаме за първи път страницата, то автомиатично ще влезе към модела атрибут userModel == празен new UserRegisterDto()
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDto userRegisterDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userRegisterDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
            return "redirect:/users/register";
        }

        userService.registerAndLogin(userRegisterDto);
        return "redirect:/";
    }
}
