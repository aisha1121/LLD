package org.example;

import org.example.payment.PaymentProcessor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RentalSystemService {
    private static volatile RentalSystemService instance;
    private final Map<String, Car> cars;
    private final Map<String, Reservation> reservations;
    private final PaymentProcessor paymentProcessor;

    private RentalSystemService(PaymentProcessor paymentProcessor) {
        cars = new ConcurrentHashMap<>();
        reservations = new ConcurrentHashMap<>();
        this.paymentProcessor = paymentProcessor;
    }

    public static RentalSystemService getInstance(PaymentProcessor paymentProcessor) {
        if (instance == null) {
            synchronized (RentalSystemService.class) {
                if (instance == null) {
                    instance = new RentalSystemService(paymentProcessor);
                }
            }
        }
        return instance;
    }

    public void addCar(Car car) {
        cars.putIfAbsent(car.getPlateNumber(), car);
    }

    public List<Car> searchCarByCriteria(String company, String model, LocalDateTime startDate, LocalDateTime endDate) {
        List<Car> availableCars = new ArrayList<>();
        for (Car car : cars.values()) {
            if (car.getCompany().equalsIgnoreCase(company)
                    && car.getModel().equalsIgnoreCase(model)
                    && isCarAvailable(car, startDate, endDate)) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    private boolean isCarAvailable(Car car, LocalDateTime startDate, LocalDateTime endDate) {
        for (Reservation reservation : reservations.values()) {
            if (reservation.getCar().equals(car)
                    && startDate.isBefore(reservation.getEndDate())
                    && endDate.isAfter(reservation.getStartDate())) {
                return false;
            }
        }
        return true;
    }

    public Reservation makeReservation(Car car, LocalDateTime startDate, LocalDateTime endDate, Customer customer) {
        car.lock();
        try {
            Reservation reservation = null;
            if (isCarAvailable(car, startDate, endDate)) {
                reservation = new Reservation(car, customer, startDate, endDate);
                reservations.put(reservation.getReservationId(), reservation);
            }
            return reservation;
        } finally {
            car.unlock();
        }
    }

    public void cancelReservation(String reservationId) {
        Reservation reservation = reservations.get(reservationId);
        Car car = reservation.getCar();
        car.lock();
        try {
            reservations.remove(reservationId);
        } finally {
            car.unlock();
        }
    }

    public boolean processPayment(String reservationId) {
        return paymentProcessor.processPayment(reservations.get(reservationId).getTotalPrice());
    }

}
