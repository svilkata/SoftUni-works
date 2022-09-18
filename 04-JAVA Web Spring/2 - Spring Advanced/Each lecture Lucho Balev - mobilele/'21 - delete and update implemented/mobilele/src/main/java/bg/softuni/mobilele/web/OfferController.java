package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.AddOfferDTO;
import bg.softuni.mobilele.model.dto.OfferDetailsViewDTO;
import bg.softuni.mobilele.model.dto.OfferUpdateBindingFlashAttrModelDTO;
import bg.softuni.mobilele.model.dto.OfferUpdateModelDTO;
import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;
import bg.softuni.mobilele.service.BrandService;
import bg.softuni.mobilele.service.OfferService;
import bg.softuni.mobilele.user.MobileleUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class OfferController {
    private final OfferService offerService;
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    public OfferController(OfferService offerService, BrandService brandService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/offers/all")
    public String allOffers(Model model) {
        //Get from the database all offers
        model.addAttribute("offers", this.offerService.findAllOffers());
        return "offers";
    }

    @GetMapping("/offers/add")
    public String addOffer(Model model) {
        if (!model.containsAttribute("addOfferModel")) {
            model.addAttribute("addOfferModel", new AddOfferDTO());
        }

        //Get from the database
        model.addAttribute("brands", this.brandService.getBrands());

        return "offer-add";
    }

    @PostMapping("/offers/add")
    public String addOffer(@Valid AddOfferDTO addOfferModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
//                           Principal principal
                           @AuthenticationPrincipal MobileleUser user
    ) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferModel", addOfferModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferModel", bindingResult);
            return "redirect:/offers/add";
        }

        //Save in the DB
//        this.offerService.addOffer(addOfferModel, principal);
        this.offerService.addOffer(addOfferModel, user.getUserIdentifier());

        return "redirect:/offers/all";
    }

    @GetMapping("/offers/{id}/details")
    public String showOfferDetail(@PathVariable Long id, Model model) {
        model.addAttribute("currOfferDetail", offerService.findById(id));

        return "details";
    }

    @DeleteMapping("/offers/{id}")
    public String deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }

    @GetMapping("/offers/{id}/edit")
    public String editOffer(@PathVariable Long id, Model model) {
        OfferDetailsViewDTO offerDetailsView = offerService.findById(id);
        OfferUpdateBindingFlashAttrModelDTO offerUpdateModelDTO = modelMapper.map(offerDetailsView,
                OfferUpdateBindingFlashAttrModelDTO.class);

        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());
        model.addAttribute("offerModel", offerUpdateModelDTO);

        return "update";
    }

    @GetMapping("/offers/{id}/edit/errors")
    public String editOfferErrors(@PathVariable Long id, Model model) {
        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());

        return "update";
    }

    @PatchMapping("/offers/{id}/edit")
    public String editOffer(@PathVariable Long id,
                            @Valid OfferUpdateBindingFlashAttrModelDTO offerModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        //validation of OfferUpdateBindingFlashAttrModelDTO object

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);
            return "redirect:/offers/" + id + "/edit/errors"; //за да не се пренамажат грешките добавяме /errors
        } else {
            // Валидирания модел инстанция на OfferUpdateBindingFlashAttrModelDTO  го записваме като инстанция на OfferUpdateModelDTO
            OfferUpdateModelDTO modelUpdateService = modelMapper.map(offerModel, OfferUpdateModelDTO.class);
            modelUpdateService.setId(id);
            offerService.updateOffer(modelUpdateService);

            return "redirect:/offers/" + id + "/details";
        }
    }
}
