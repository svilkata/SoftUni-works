package com.example.battleships.webcontrollers;

import com.example.battleships.models.dto.service.FightingDetailsServiceModelDto;
import com.example.battleships.models.entities.ShipEntity;
import com.example.battleships.security.LoggedUser;
import com.example.battleships.services.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final ShipService shipService;

    public HomeController(LoggedUser loggedUser, ShipService shipService) {
        this.loggedUser = loggedUser;
        this.shipService = shipService;
    }

    @GetMapping("/")
    public String loggedOutIndex(){
        //if user is already logged-in
        if (loggedUser.getId() != null) {
            return "redirect:/home";
        }
        return "index";
    }

    @ModelAttribute("fireModel")
    public FightingDetailsServiceModelDto initFireModel(){
        return new FightingDetailsServiceModelDto();
    }

    @GetMapping("/home")
    public String loggedInIndex(Model model){
        //if user is logged-out
        if (loggedUser.getId() == null) {
            return "redirect:/";
        }


        List<ShipEntity> allShips = this.shipService.findAllShips();
        List<ShipEntity> ownShips = new ArrayList<>();
        List<ShipEntity> otherUsersShips = new ArrayList<>();

        allShips
                .forEach(sh -> {
                    if (sh.getUser().getId() == loggedUser.getId()) {
                        ownShips.add(sh);
                    } else {
                        otherUsersShips.add(sh);
                    }
                });

        model.addAttribute("allShips", allShips);
        model.addAttribute("ownShips", ownShips);
        model.addAttribute("otherUsersShips", otherUsersShips);

        return "home";
    }

    @PostMapping("/home")
    public String fightModeOn(FightingDetailsServiceModelDto fightingDetailsServiceModelDtoModel) {

        this.shipService.fire(fightingDetailsServiceModelDtoModel);


        return "redirect:/home";
    }


}
