package com.example.kinobackend.repository;

import com.example.kinobackend.model.Seat;
import com.example.kinobackend.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    List<Seat> findByTheatre(Theatre theatre);
}
