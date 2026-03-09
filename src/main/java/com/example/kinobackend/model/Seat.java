package com.example.kinobackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    @Column(name = "row_index")
    private int rowIndex;

    @Column(name = "seat_number")
    private int seatNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowNumber(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
