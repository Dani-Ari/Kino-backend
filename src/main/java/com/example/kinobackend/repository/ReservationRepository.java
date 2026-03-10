package com.example.kinobackend.repository;

import com.example.kinobackend.model.Reservation;
import com.example.kinobackend.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
