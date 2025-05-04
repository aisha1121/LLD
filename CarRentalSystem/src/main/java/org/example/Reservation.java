package org.example;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Reservation {
    private final String reservationId;
    private final Car car;
    private final Customer customer;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final double totalPrice;

    public Reservation(Car car, Customer customer, LocalDateTime startDate, LocalDateTime endDate) {
        this.car = car;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationId = generateId();
        this.totalPrice = getTotalPrice();
    }

    public double getTotalPrice() {
        return car.getRentalPricePerDay() * (ChronoUnit.DAYS.between(startDate, endDate) + 1);
    }

    private String generateId() {
        return "RES"+UUID.randomUUID().toString().substring(0, 5);
    }

    public String getReservationId() {
        return reservationId;
    }

    public Car getCar() {
        return car;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }



}
