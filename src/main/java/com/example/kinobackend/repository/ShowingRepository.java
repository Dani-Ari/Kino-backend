package com.example.kinobackend.repository;

import com.example.kinobackend.model.Showing;
import com.example.kinobackend.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ShowingRepository extends JpaRepository<Showing, Integer> {

    boolean existsByTheatreAndStartTime(Theatre theatre, LocalDateTime startTime);
}
