package com.example.kinobackend.service;

import com.example.kinobackend.model.Theatre;
import com.example.kinobackend.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreService {
    private final TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public List<Theatre> getTheatres() {
        return theatreRepository.findAll();
    }
}
