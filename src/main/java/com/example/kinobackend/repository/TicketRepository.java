package com.example.kinobackend.repository;

import com.example.kinobackend.enums.TicketStatus;
import com.example.kinobackend.model.Showing;
import com.example.kinobackend.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findByShowingAndStatus(Showing showing, TicketStatus status);
}
