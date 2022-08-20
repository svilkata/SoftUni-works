package com.example.coffeeshop.web;

import com.example.coffeeshop.model.view.OrderViewModelDTO;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public HomeController(CurrentUser currentUser, OrderService orderService, UserService userService) {
        this.currentUser = currentUser;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {

        if (currentUser.getId() == null) {
            return "index.html";
        }

        List<OrderViewModelDTO> orders = orderService.findAllOrderOrderByPriceDesc();
        model.addAttribute("orders", orders);
        model.addAttribute("totalTime", orders
                .stream()
                .map(orderViewModelDTO -> orderViewModelDTO.getCategory().getNeededTime())
                .reduce((acc, res) -> (acc + res))
                .orElse(0)
        );

        model.addAttribute("users", this.userService.findAllUserAndCountOfTheirOrdersByCountDesc());

        return "home.html";
    }
}
