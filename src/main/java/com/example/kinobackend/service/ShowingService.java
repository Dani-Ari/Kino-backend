package com.example.kinobackend.service;

import com.example.kinobackend.model.Showing;
import com.example.kinobackend.repository.ShowingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowingService {
    private final ShowingRepository showingRepository;

    public ShowingService(ShowingRepository showingRepository) {
        this.showingRepository = showingRepository;
    }

    public List<Showing> getAllShowings() {
        return showingRepository.findAll();
    }

    public Showing addShowing(Showing showing) {

        if (showingRepository.existsByTheatreAndStartTime(showing.getTheatre(), showing.getStartTime())) {
            throw new RuntimeException("Theatre already has a showing at this time!");
        }
        return showingRepository.save(showing);
    }

    public void deleteShowing(int id) {
        if (!showingRepository.existsById(id)) {
            throw new RuntimeException("Showing not found");
        }
        showingRepository.deleteById(id);
    }
}
