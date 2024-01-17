package com.example.demo.web;

import com.example.demo.service.OfferService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class OfferController {

    private OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    // @PreAuthorize("hasRole('ADMIN')")  - only 1 user type can access it
    // @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'TOZI_ONZI')")  //several user types can access it
    // @PreAuthorize("@offerService.isOwner(#principal.name, #uuid)") //isOwner here refers to offerService - specific users can access it
    @PreAuthorize("isOwner(#uuid)")  //isOwner here refers to OwnerSecurityExpressionRoot - more logic which users to have access
    @DeleteMapping("/offers/{id}")
    public String deleteOffer(@PathVariable("id") UUID uuid) {
        offerService.deleteOfferById(uuid);

        return "redirect:/offers/all";
    }
}
