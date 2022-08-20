package com.example.shoping.web;

import com.example.shoping.models.entities.enums.CategoryName;
import com.example.shoping.security.LoggedUser;
import com.example.shoping.service.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final ProductServiceImpl productService;

    public HomeController(LoggedUser loggedUser, ProductServiceImpl productService) {
        this.loggedUser = loggedUser;
        this.productService = productService;
    }

    @GetMapping("/")
    public String loggedOutIndex(){
        //if user is already logged-in
        if (loggedUser.getId() != null) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String loggedInIndex(Model model){
        //if user is logged-out
        if (loggedUser.getId() == null) {
            return "redirect:/";
        }

        model.addAttribute("totalSum", productService.getTotalSum());
        model.addAttribute("drinks",
                productService.findAllProductsByCategoryName(CategoryName.DRINK));

        model.addAttribute("food",
                productService.findAllProductsByCategoryName(CategoryName.FOOD));

        model.addAttribute("household",
                productService.findAllProductsByCategoryName(CategoryName.HOUSEHOLD));

        model.addAttribute("other",
                productService.findAllProductsByCategoryName(CategoryName.OTHER));

        return "home";
    }


}
