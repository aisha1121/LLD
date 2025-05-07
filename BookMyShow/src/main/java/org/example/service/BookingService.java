package org.example.service;

import org.example.Booking;
import org.example.Seat;

import java.util.List;

public interface BookingService {
    Booking createBooking(List<Seat> selectedSeats);
    void confirmBooking(String bookingId);
    void cancelBooking(String bookingId);
}
