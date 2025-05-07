package org.example;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class Show {
    private String id;
    private String movieId;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<Seat> seatList;

    public Show(String movieId, LocalTime startTime, LocalTime endTime, List<Seat> seatList) {
        this.id = UUID.randomUUID().toString().substring(0, 5);
        this.movieId = movieId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seatList = seatList;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }

    public Seat getById(int id) {
        for (Seat seat : seatList) {
            if (seat.getSeatNumber() == id) return seat;
        }
        return null;
    }
}
