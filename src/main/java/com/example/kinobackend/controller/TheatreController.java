package com.example.kinobackend.controller;

import com.example.kinobackend.model.Theatre;
import com.example.kinobackend.service.TheatreService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/theatres")
@CrossOrigin(origins = "*")
public class TheatreController {
    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @GetMapping
    public List<Theatre> getTheatres() {
        return theatreService.getTheatres();
    }
}
