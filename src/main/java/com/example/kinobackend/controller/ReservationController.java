package com.example.kinobackend.controller;

import com.example.kinobackend.model.Reservation;
import com.example.kinobackend.model.Ticket;
import com.example.kinobackend.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

        int showingId = ((Number) body.get("showingId")).intValue();
        String name = (String) body.get("name");
        String phone = (String) body.get("phone");

        List<Integer> ticketIds = new ArrayList<>();

        List<?> rawIds = (List<?>) body.get("ticketIds");
        if (rawIds != null) {
            for (Object id : rawIds) {
                ticketIds.add(((Number) id).intValue());
            }
        }

        Reservation reservation =
                reservationService.createReservation(name, phone, showingId, ticketIds);

        return ResponseEntity.ok(reservation);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable int id) {

        reservationService.cancelReservation(id);

        return ResponseEntity.noContent().build();
    }
}
