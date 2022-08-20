package com.example.battleships.webcontrollers;

import com.example.battleships.models.dto.binding.CreateShipDto;
import com.example.battleships.security.LoggedUser;
import com.example.battleships.services.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ShipController {
    private final ShipService shipService;
    private final LoggedUser loggedUser;

    public ShipController(ShipService shipService, LoggedUser loggedUser) {
        this.shipService = shipService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("createShipDto")
    public CreateShipDto initCreateShipDto(){
        return new CreateShipDto();
    }

    @GetMapping("/ships/add")
    public String addShip(){
        //If user is not logged in
        if (loggedUser.getId() == null) {
            return "redirect:/";
        }
        return "ship-add";
    }

    @PostMapping("/ships/add")
    public String addShipConfirm(@Valid CreateShipDto createShipDto,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes){

        //If user is not logged in
        if (loggedUser.getId() == null) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !this.shipService.create(createShipDto)) {
            redirectAttributes.addFlashAttribute("createShipDto", createShipDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createShipDto", bindingResult);

            return "redirect:/ships/add";
        }

        return "redirect:/home";
    }

}
