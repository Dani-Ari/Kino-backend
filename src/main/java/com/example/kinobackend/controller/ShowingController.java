package com.example.kinobackend.controller;


import com.example.kinobackend.model.Showing;
import com.example.kinobackend.service.ShowingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/showings")
@CrossOrigin(origins = "*")
public class ShowingController {
    private final ShowingService showingService;

    public ShowingController(ShowingService showingService) {
        this.showingService = showingService;

    }

    @GetMapping
    public List<Showing> getAllShowings() {
        return showingService.getAllShowings();
    }

    @PostMapping
    public ResponseEntity<Showing> addShowing(@Valid @RequestBody Showing showing, BindingResult br) {

        if (br.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(showingService.addShowing(showing));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> cancelShowing(@PathVariable int id) {
        showingService.cancelShowing(id);
        return ResponseEntity.noContent().build();
    }
}
