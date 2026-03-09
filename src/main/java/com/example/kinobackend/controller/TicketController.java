package com.example.kinobackend.controller;

import com.example.kinobackend.model.Ticket;
import com.example.kinobackend.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "*")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @PatchMapping("/{id}/sell")
    public void sellTicket(@PathVariable int id) {
        ticketService.sellTicket(id);
    }
}