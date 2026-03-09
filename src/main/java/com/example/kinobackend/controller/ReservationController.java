package com.example.kinobackend.controller;

import com.example.kinobackend.model.Reservation;
import com.example.kinobackend.model.Ticket;
import com.example.kinobackend.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservations")
@CrossOrigin(origins = "*")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getReservations() {
        return reservationService.getReservations();
    }

    @GetMapping("/showing/{showingId}/available")
    public ResponseEntity<List<Ticket>> getAvailableTickets(@PathVariable int showingId) {
        return ResponseEntity.ok(reservationService.getAvailableTickets(showingId));
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Map<String, Object> body) {
        int customerId = ((Number) body.get("customerId")).intValue();
        int showingId = ((Number) body.get("showingId")).intValue();

        List<Integer> ticketIds = (List<Integer>) body.get("ticketIds");
        Reservation reservation = reservationService.createReservation(customerId, showingId, ticketIds);
        return ResponseEntity.ok(reservation);
    }
}
