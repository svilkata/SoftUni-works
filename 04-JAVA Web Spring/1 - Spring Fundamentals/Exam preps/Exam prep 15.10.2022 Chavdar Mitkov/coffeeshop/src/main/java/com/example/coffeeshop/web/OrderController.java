package com.example.coffeeshop.web;

import com.example.coffeeshop.model.binding.OrderAddBindingModelDTO;
import com.example.coffeeshop.model.service.OrderServiceModelDTO;
import com.example.coffeeshop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String model() {
        return "order-add.html";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid OrderAddBindingModelDTO orderAddBindingModelDTO, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderAddBindingModelDTO", orderAddBindingModelDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModelDTO", bindingResult);

            return "redirect:add";
        }

        //add to the DB
        orderService.addOrder(modelMapper
                .map(orderAddBindingModelDTO, OrderServiceModelDTO.class));
        return "redirect:/";
    }


//    The name of the model attribute to bind to.
//    The default model attribute name is inferred from the declared attribute type
//    (i.e. the method parameter type or method return type), based on the non-qualified class name:
//    e.g. "orderAddress" for class "mypackage.OrderAddress",
//    or "orderAddressList" for "List<mypackage.OrderAddress>".

    @ModelAttribute()
    public OrderAddBindingModelDTO orderAddBindingModelDTO(){
        return new OrderAddBindingModelDTO();
    }

    @GetMapping("/ready/{id}")
    public String ready(@PathVariable Long id){
        this.orderService.readyOrder(id);

        return "redirect:/";
    }

}
