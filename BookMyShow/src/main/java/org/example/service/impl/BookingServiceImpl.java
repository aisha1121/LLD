package org.example.service.impl;

import org.example.Booking;
import org.example.BookingStatus;
import org.example.Seat;
import org.example.SeatStatus;
import org.example.service.BookingService;

import java.util.HashMap;
import java.util.List;

public class BookingServiceImpl implements BookingService {
    HashMap<String, Booking> bookingMap = new HashMap<>();

    @Override
    public Booking createBooking(List<Seat> selectedSeats) {
        int totalPrice = 0;
        for (Seat seat : selectedSeats) {
            seat.setSeatStatus(SeatStatus.BOOKED);
            totalPrice += seat.getCost();
        }
        Booking booking = new Booking(totalPrice, selectedSeats);
        bookingMap.put(booking.getBookingId(), booking);
        return booking;
    }

    @Override
    public void confirmBooking(String bookingId) {
        // do payment
        Booking booking = bookingMap.get(bookingId);
        if (booking != null) {
            booking.setBookingStatus(BookingStatus.CONFIRMED);
            System.out.println("Booking confirmed for bookingId: " + bookingId);
        }
    }

    @Override
    public void cancelBooking(String bookingId) {
        Booking booking = bookingMap.get(bookingId);
        if (booking != null) {
            booking.setBookingStatus(BookingStatus.CANCELLED);
            System.out.println("Booking cancelled for bookingId: " + bookingId);
        }

    }
}
