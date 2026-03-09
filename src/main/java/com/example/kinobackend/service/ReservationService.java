package com.example.kinobackend.service;

import com.example.kinobackend.enums.ReservationStatus;
import com.example.kinobackend.model.Customer;
import com.example.kinobackend.model.Reservation;
import com.example.kinobackend.model.Showing;
import com.example.kinobackend.model.Ticket;
import com.example.kinobackend.repository.CustomerRepository;
import com.example.kinobackend.repository.ReservationRepository;
import com.example.kinobackend.repository.ShowingRepository;
import com.example.kinobackend.repository.TicketRepository;
import com.example.kinobackend.enums.TicketStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private final ShowingRepository showingRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              TicketRepository ticketRepository,
                              CustomerRepository customerRepository,
                              ShowingRepository showingRepository) {
        this.reservationRepository = reservationRepository;
        this.ticketRepository = ticketRepository;
        this.customerRepository = customerRepository;
        this.showingRepository = showingRepository;
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public List<Ticket> getAvailableTickets(int showingId) {
        Showing showing = showingRepository.findById(showingId).orElseThrow();
        return ticketRepository.findByShowingAndStatus(showing, TicketStatus.AVAILABLE);
    }

    public Reservation createReservation(int customerId, int showingId, List<Integer> ticketIds) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        Showing showing = showingRepository.findById(showingId).orElseThrow();

        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setShowing(showing);
        reservation.setReservationTime(Timestamp.from(Instant.now()));
        reservation.setStatus(ReservationStatus.CONFIRMED);
        Reservation saved = reservationRepository.save(reservation);

        for (int ticketId : ticketIds) {
            Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();

            if (ticket.getShowing().getId() != showingId) {
                throw new RuntimeException("Ticket does not belong to this showing");
            }

            if (ticket.getStatus() != TicketStatus.AVAILABLE) {
                throw new RuntimeException("Ticket " + ticketId + " is not available!");
            }
            ticket.setStatus(TicketStatus.RESERVED);
            ticket.setReservation(saved);
            ticketRepository.save(ticket);
        }
        return saved;
    }
}
