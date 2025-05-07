package org.example;

import java.util.List;
import java.util.UUID;

public class Booking {
    private String bookingId;
    private int price;
    private List<Seat> selectedSeats;
    private BookingStatus bookingStatus;

    public Booking(int price, List<Seat> selectedSeats) {
        this.bookingId = UUID.randomUUID().toString().substring(0, 8);
        this.price = price;
        this.selectedSeats = selectedSeats;
        this.bookingStatus = BookingStatus.PENDING;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Seat> getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(List<Seat> selectedSeats) {
        this.selectedSeats = selectedSeats;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getBookingId() {
        return bookingId;
    }
}
