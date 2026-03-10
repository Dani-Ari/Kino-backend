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
import jakarta.transaction.Transactional;
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

    @Transactional
    public Reservation createReservation(String name, String phone, int showingId, List<Integer> ticketIds) {

        if (ticketIds == null || ticketIds.isEmpty()) {
            throw new IllegalArgumentException("No tickets selected");
        }

        Showing showing = showingRepository.findById(showingId)
                .orElseThrow(() -> new IllegalArgumentException("Showing not found"));

        Customer customer = customerRepository.findByPhone(phone);

        if (customer == null) {
            customer = new Customer();
            customer.setName(name);
            customer.setPhone(phone);
            customer = customerRepository.save(customer);
        }

        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setShowing(showing);
        reservation.setReservationTime(Timestamp.from(Instant.now()));
        reservation.setStatus(ReservationStatus.CONFIRMED);

        Reservation savedReservation = reservationRepository.save(reservation);

        List<Ticket> tickets = ticketRepository.findAllById(ticketIds);

        if (tickets.size() != ticketIds.size()) {
            throw new IllegalArgumentException("One or more tickets do not exist");
        }

        for (Ticket ticket : tickets) {

            if (ticket.getShowing().getId() != showingId) {
                throw new IllegalArgumentException("Ticket does not belong to this showing");
            }

            if (ticket.getStatus() != TicketStatus.AVAILABLE) {
                throw new IllegalStateException("Ticket " + ticket.getId() + " is not available");
            }

            ticket.setStatus(TicketStatus.RESERVED);
            ticket.setReservation(savedReservation);
        }

        ticketRepository.saveAll(tickets);

        return savedReservation;
    }

    @Transactional
    public void cancelReservation(int id) {

        Reservation reservation =
                reservationRepository.findById(id).orElseThrow();

        reservation.setStatus(ReservationStatus.CANCELLED);

        List<Ticket> tickets =
                ticketRepository.findByReservation(reservation);

        for (Ticket ticket : tickets) {

            ticket.setStatus(TicketStatus.AVAILABLE);
            ticket.setReservation(null);

        }

        ticketRepository.saveAll(tickets);

    }
}
