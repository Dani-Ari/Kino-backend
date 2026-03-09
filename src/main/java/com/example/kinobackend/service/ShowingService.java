package com.example.kinobackend.service;

import com.example.kinobackend.enums.ShowingStatus;
import com.example.kinobackend.model.Seat;
import com.example.kinobackend.model.Showing;
import com.example.kinobackend.model.Ticket;
import com.example.kinobackend.repository.SeatRepository;
import com.example.kinobackend.repository.ShowingRepository;
import com.example.kinobackend.repository.TheatreRepository;
import com.example.kinobackend.repository.TicketRepository;
import com.example.kinobackend.enums.TicketStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowingService {
    private final ShowingRepository showingRepository;
    private final SeatRepository seatRepository;
    private final TicketRepository ticketRepository;

    public ShowingService(ShowingRepository showingRepository, SeatRepository seatRepository, TicketRepository ticketRepository) {
        this.showingRepository = showingRepository;
        this.seatRepository = seatRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<Showing> getAllShowings() {
        return showingRepository.findAll();
    }

    public Showing addShowing(Showing showing) {

        if (showingRepository.existsByTheatreAndStartTime(showing.getTheatre(), showing.getStartTime())) {
            throw new RuntimeException("Theatre already has a showing at this time!");
        }

        Showing saved = showingRepository.save(showing);

        List<Seat> seats = seatRepository.findByTheatre(showing.getTheatre());
        for (Seat seat : seats) {
            Ticket ticket = new Ticket();
            ticket.setShowing(saved);
            ticket.setSeat(seat);
            ticket.setStatus(TicketStatus.AVAILABLE);
            ticketRepository.save(ticket);
        }
        return saved;
    }

    public void cancelShowing(int id) {
        Showing showing = showingRepository.findById(id).orElseThrow(() -> new RuntimeException(("Showing not found!")));
        showing.setStatus(ShowingStatus.CANCELLED);
        showingRepository.save(showing);
    }
}
