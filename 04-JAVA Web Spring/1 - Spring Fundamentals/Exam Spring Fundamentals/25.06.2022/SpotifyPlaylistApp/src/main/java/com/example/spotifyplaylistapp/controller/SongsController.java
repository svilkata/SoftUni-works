package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.binding.SongCreateAddBindingDto;
import com.example.spotifyplaylistapp.security.LoggedUser;
import com.example.spotifyplaylistapp.service.SongsServiceImpl;
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
@RequestMapping("/songs")
public class SongsController {
    private final LoggedUser loggedUser;
    private final SongsServiceImpl songsService;

    public SongsController(LoggedUser loggedUser, SongsServiceImpl songsService) {
        this.loggedUser = loggedUser;
        this.songsService = songsService;
    }

    @ModelAttribute("songCreateAddBindingDto")
    public SongCreateAddBindingDto initProductAddDto(){
        return new SongCreateAddBindingDto();
    }

    @GetMapping("/createadd")
    public String add(){
        //If user is not logged in
        if (loggedUser.getId() == null) {
            return "redirect:/";
        }

        return "song-add";
    }

    @PostMapping("/createadd")
    public String addConfirm(@Valid SongCreateAddBindingDto songCreateAddBindingDto,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes){
        //If user is not logged in
        if (loggedUser.getId() == null) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !this.songsService.createProduct(songCreateAddBindingDto)) {
            redirectAttributes.addFlashAttribute("songCreateAddBindingDto", songCreateAddBindingDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.songCreateAddBindingDto", bindingResult);

            return "redirect:createadd";
        }


        return "redirect:/home";
    }


}
