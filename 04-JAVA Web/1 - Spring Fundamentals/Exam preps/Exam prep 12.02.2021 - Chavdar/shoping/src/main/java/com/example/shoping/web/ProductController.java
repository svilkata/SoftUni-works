package com.example.shoping.web;

import com.example.shoping.models.dtos.binding.ProductAddBindingDto;
import com.example.shoping.security.LoggedUser;
import com.example.shoping.service.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final LoggedUser loggedUser;
    private final ProductServiceImpl productService;

    public ProductController(LoggedUser loggedUser, ProductServiceImpl productService) {
        this.loggedUser = loggedUser;
        this.productService = productService;
    }

    @ModelAttribute("productAddBindingDto")
    public ProductAddBindingDto initProductAddDto(){
        return new ProductAddBindingDto();
    }

    @GetMapping("/add")
    public String add(){
        //If user is not logged in
        if (loggedUser.getId() == null) {
            return "redirect:/";
        }

        return "product-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ProductAddBindingDto productAddBindingDto,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes){
        //If user is not logged in
        if (loggedUser.getId() == null) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !this.productService.createProduct(productAddBindingDto)) {
            redirectAttributes.addFlashAttribute("productAddBindingDto", productAddBindingDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.productAddBindingDto", bindingResult);

            return "redirect:add";
        }


        return "redirect:/home";
    }

    @GetMapping("/buy/{id}")
    public String buyById(@PathVariable Long id){
        productService.buyById(id);

        return "redirect:/home";
    }

    @GetMapping("/buy/all")
    public String buyAll(){
        productService.buyAll();

        return "redirect:/home";
    }

}
