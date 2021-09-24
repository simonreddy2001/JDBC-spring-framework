package com.experis.JDBCproject.controllers;

import com.experis.JDBCproject.data_access.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThymeleafController {
    private final GenreRepository genreRepository = new GenreRepository();
    private final TrackRepository trackRepository = new TrackRepository();
    private final ArtistRepository artistRepository = new ArtistRepository();
    private final TrackInfoRepository trackInfoRepository = new TrackInfoRepository();
    private final GenreInfoRepository genreInfoRepository = new GenreInfoRepository();
    private final ArtistInfoRepository artistInfoRepository = new ArtistInfoRepository();

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("genres", genreRepository.generateRandomGenres());
        model.addAttribute("tracks", trackRepository.getRandomTracks());
        model.addAttribute("artists", artistRepository.getRandomArtists());
        return "index";
    }

    @RequestMapping(value = "/track/search", method = RequestMethod.GET)
    public String searchTrack() {
        return "search-track";
    }
    @RequestMapping(value = "/genre/search", method = RequestMethod.GET)
    public String searchGenre() {
        return "search-genre";
    }
    @RequestMapping(value = "/artist/search", method = RequestMethod.GET)
    public String searchArtist() {
        return "search-artist";
    }

    @RequestMapping(value = "/track/search", method = RequestMethod.POST)
    public String searchTrack(@RequestParam("searchTerm") String searchTerm, Model model) {

        model.addAttribute("success", true);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("trackInfo", trackInfoRepository.getTrackInfo(searchTerm));
        return "search-track";
    }
    @RequestMapping(value = "/genre/search", method = RequestMethod.POST)
    public String searchGenre(@RequestParam("searchTerm") String searchTerm, Model model) {

        model.addAttribute("success", true);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("genreInfo", genreInfoRepository.getGenreInfo(searchTerm));
        return "search-genre";
    }
    @RequestMapping(value = "/artist/search", method = RequestMethod.POST)
    public String searchArtist(@RequestParam("searchTerm") String searchTerm, Model model) {

        model.addAttribute("success", true);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("artistInfo", artistInfoRepository.getArtistInfo(searchTerm));
        return "search-artist";
    }
}
