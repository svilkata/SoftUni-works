package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.security.LoggedUser;
import com.example.spotifyplaylistapp.service.SongsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final SongsServiceImpl songsService;

    public HomeController(LoggedUser loggedUser, SongsServiceImpl songsService) {
        this.loggedUser = loggedUser;
        this.songsService = songsService;
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

        model.addAttribute("popSongs", this.songsService.getAllSongs().getPopSongs());
        model.addAttribute("rockSongs", this.songsService.getAllSongs().getRockSongs());
        model.addAttribute("jazzSongs", this.songsService.getAllSongs().getJazzSongs());
        model.addAttribute("totalDurationOfPlaylist", this.songsService.getAllMinutesPlaylistSongs());
        model.addAttribute("currentUserPlaylistSongs", this.songsService.getAllSongs().getCurrentUserPlaylistSongs());

        return "home";
    }

    @GetMapping("/songs/addToPlaylist/{songId}")
    public String addToPlaylist(@PathVariable(name = "songId") Long songId){
        //if user is logged-out
        if (loggedUser.getId() == null) {
            return "redirect:/";
        }

        this.songsService.addSongToPlaylist(songId);

        return "redirect:/home";
    }

    @GetMapping("/songs/removeAll")
    public String removeAllSongsFromPlaylist(){
        //if user is logged-out
        if (loggedUser.getId() == null) {
            return "redirect:/";
        }

        this.songsService.removeAllSongsFromUserPlaylist();

        return "redirect:/home";
    }


}
