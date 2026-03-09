package com.example.kinobackend.service;

import com.example.kinobackend.enums.ShowingStatus;
import com.example.kinobackend.model.Ticket;
import com.example.kinobackend.repository.TicketRepository;
import org.springframework.stereotype.Service;
import com.example.kinobackend.enums.TicketStatus;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public void sellTicket(int ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.getShowing().getStatus() == ShowingStatus.CANCELLED) {
            throw new RuntimeException("Cannot sell ticket for cancelled showing");
        }

        if (ticket.getStatus() == TicketStatus.SOLD) {
            throw new RuntimeException("Ticket already sold");
        }

        ticket.setStatus(TicketStatus.SOLD);
        ticketRepository.save(ticket);
    }
}