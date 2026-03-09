package com.example.kinobackend.service;

import com.example.kinobackend.model.Seat;
import com.example.kinobackend.model.Theatre;
import com.example.kinobackend.repository.SeatRepository;
import com.example.kinobackend.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreService {
    private final TheatreRepository theatreRepository;
    private final SeatRepository seatRepository;

    public TheatreService(TheatreRepository theatreRepository, SeatRepository seatRepository) {
        this.theatreRepository = theatreRepository;
        this.seatRepository = seatRepository;
    }

    public List<Theatre> getTheatres() {
        return theatreRepository.findAll();
    }

    public Theatre addTheatre(Theatre theatre) {
        Theatre saved = theatreRepository.save(theatre);
        for (int row = 1; row <= theatre.getRowCount(); row++) {
            for (int seat = 1; seat <= theatre.getSeatsPerRow(); seat++) {
                Seat s = new Seat();
                s.setTheatre(saved);
                s.setRowNumber(row);
                s.setSeatNumber(seat);
                seatRepository.save(s);
            }
        }
        return saved;
    }
}
