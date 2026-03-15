package com.example.kinobackend.config;

import com.example.kinobackend.enums.ReservationStatus;
import com.example.kinobackend.enums.TicketStatus;
import java.util.List;
import com.example.kinobackend.model.*;
import com.example.kinobackend.repository.*;
import com.example.kinobackend.service.ShowingService;
import com.example.kinobackend.service.TheatreService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Component
//@Profile("!docker")  // Add this line
public class DataInitializer implements ApplicationRunner {
    private final TheatreService theatreService;
    private final ShowingService showingService;
    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;
    private final TicketRepository ticketRepository;
    private final EmployeeRepository employeeRepository;

    public DataInitializer(TheatreService theatreService, ShowingService showingService,
                           MovieRepository movieRepository, CategoryRepository categoryRepository,
                           CustomerRepository customerRepository, ReservationRepository reservationRepository,
                           TicketRepository ticketRepository, EmployeeRepository employeeRepository) {
        this.theatreService = theatreService;
        this.showingService = showingService;
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.reservationRepository = reservationRepository;
        this.ticketRepository = ticketRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Theatres + seats (auto-generated)
        Theatre large = theatreService.addTheatre(createTheatre("Large Theatre", 25, 16));
        Theatre small = theatreService.addTheatre(createTheatre("Small Theatre", 20, 12));

        // Categories
        Category comedy = categoryRepository.save(createCategory("Comedy"));
        Category action = categoryRepository.save(createCategory("Action"));
        Category horror = categoryRepository.save(createCategory("Horror"));
        Category thriller = categoryRepository.save(createCategory("Thriller"));
        Category adventure = categoryRepository.save(createCategory("Adventure"));
        Category kids = categoryRepository.save(createCategory("Kids"));
        Category family = categoryRepository.save(createCategory("Family"));

        // Movies
        Movie m1 = movieRepository.save(createMovie("The Damnation", 111, 18));
        Movie m2 = movieRepository.save(createMovie("2 Nights at Teddy's", 78, null));
        Movie m3 = movieRepository.save(createMovie("Scream 8", 101, 16));
        Movie m4 = movieRepository.save(createMovie("The Mandalorian & Grogu", 163, null));

        // Showings
        Showing s1 = showingService.addShowing(createShowing(m1, small, LocalDateTime.of(2026, 3, 5, 21, 45)));
        Showing s2 = showingService.addShowing(createShowing(m2, small, LocalDateTime.of(2026, 3, 6, 14, 10)));
        Showing s3 = showingService.addShowing(createShowing(m3, large, LocalDateTime.of(2026, 3, 5, 21, 0)));
        Showing s4 = showingService.addShowing(createShowing(m4, large, LocalDateTime.of(2026, 3, 6, 21, 0)));

        // Customers
        Customer c1 = customerRepository.save(createCustomer("Donald Trump", "+1 202 456-1111"));
        Customer c2 = customerRepository.save(createCustomer("Mette Frederiksen", "+45 19101977"));
        Customer c3 = customerRepository.save(createCustomer("Lars Kragh Andersen", "+45 1337903"));
        Customer c4 = customerRepository.save(createCustomer("Bente M", "+45 22301928"));
        Customer c5 = customerRepository.save(createCustomer("Lars Ulykke", "+45 66666666"));
        Customer c6 = customerRepository.save(createCustomer("Inger Roberg", "+45 10102112"));
        Customer c7 = customerRepository.save(createCustomer("Pynte Sigurdsson", "+45 28934012"));
        Customer c8 = customerRepository.save(createCustomer("Mogens G", "+45 19262008"));
        Customer c9 = customerRepository.save(createCustomer("Jeffrey Kennedy", "+92 1005 50002983"));

        // Reservations
        Reservation r1 = reservationRepository.save(createReservation(c1, s3, ReservationStatus.CANCELLED));
        Reservation r2 = reservationRepository.save(createReservation(c2, s2, ReservationStatus.CONFIRMED));
        Reservation r3 = reservationRepository.save(createReservation(c3, s1, ReservationStatus.CANCELLED));
        Reservation r4 = reservationRepository.save(createReservation(c4, s4, ReservationStatus.CONFIRMED));
        Reservation r5 = reservationRepository.save(createReservation(c5, s1, ReservationStatus.CONFIRMED));
        Reservation r6 = reservationRepository.save(createReservation(c6, s3, ReservationStatus.CONFIRMED));
        Reservation r7 = reservationRepository.save(createReservation(c7, s4, ReservationStatus.CONFIRMED));
        Reservation r8 = reservationRepository.save(createReservation(c8, s4, ReservationStatus.CONFIRMED));
        Reservation r9 = reservationRepository.save(createReservation(c9, s4, ReservationStatus.CONFIRMED));

        attachTickets(r2, 2);
        attachTickets(r4, 3);
        attachTickets(r5, 1);
        attachTickets(r6, 2);
        attachTickets(r7, 2);
        attachTickets(r8, 1);
        attachTickets(r9, 3);

        // Employees
        employeeRepository.save(createEmployee("SALES", "Frodo Andersen"));
        employeeRepository.save(createEmployee("SALES", "Mo Galko"));
        employeeRepository.save(createEmployee("INSPECTOR", "Heidi Blåstrup"));
        employeeRepository.save(createEmployee("OPERATOR", "Dan Mark"));
    }

    private Theatre createTheatre(String name, int rows, int seatsPerRow) {
        Theatre t = new Theatre(); t.setName(name); t.setRowCount(rows); t.setSeatsPerRow(seatsPerRow); return t;
    }
    private Movie createMovie(String title, int duration, Integer ageLimit) {
        Movie m = new Movie(); m.setTitle(title); m.setDuration(duration); m.setAgeLimit(ageLimit); return m;
    }
    private Category createCategory(String name) {
        Category c = new Category(); c.setName(name); return c;
    }
    private Showing createShowing(Movie movie, Theatre theatre, LocalDateTime startTime) {
        Showing s = new Showing(); s.setMovie(movie); s.setTheatre(theatre); s.setStartTime(startTime); return s;
    }
    private Customer createCustomer(String name, String phone) {
        Customer c = new Customer(); c.setName(name); c.setPhone(phone); return c;
    }

    private Reservation createReservation(Customer customer, Showing showing, ReservationStatus status) {
        Reservation r = new Reservation();
        r.setCustomer(customer);
        r.setShowing(showing);
        r.setStatus(status);
        r.setReservationTime(Timestamp.from(Instant.now()));
        return r;
    }
    private void attachTickets(Reservation reservation, int amount) {
        List<Ticket> available = ticketRepository.findByShowingAndStatus(
                reservation.getShowing(), TicketStatus.AVAILABLE);

        for (int i = 0; i < amount && i < available.size(); i++) {
            Ticket t = available.get(i);
            t.setReservation(reservation);
            t.setStatus(TicketStatus.RESERVED);
            ticketRepository.save(t);
        }
    }

    private Employee createEmployee(String role, String name) {
        Employee e = new Employee(); e.setRole(role); e.setName(name); return e;
    }
}