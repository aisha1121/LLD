package org.example;

import org.example.payment.UPIPayment;

import java.time.LocalDateTime;
import java.util.List;

public class CarRentalSystemDemo {
    public static void main(String[] args) {
        RentalSystemService rentalSystemService = RentalSystemService.getInstance(new UPIPayment());

        rentalSystemService.addCar(new Car("HYUNDAI", "CRETA", "KA7483", 2000));
        rentalSystemService.addCar(new Car("HYUNDAI", "I20", "KA7000", 1500));
        rentalSystemService.addCar(new Car("HYUNDAI", "I10", "KA8000", 1200));
        rentalSystemService.addCar(new Car("HYUNDAI", "VENUE", "KA9000", 1000));

        Customer customer = new Customer("Aayusha", "DL859380");

        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(2);

        List<Car> availableCars = rentalSystemService.searchCarByCriteria("HYUNDAI", "CRETA", startDate, endDate);
        if (!availableCars.isEmpty()) {
            Car selectedCar = availableCars.getFirst();
            Reservation reservation = rentalSystemService.makeReservation(selectedCar, startDate, endDate, customer);
            if (reservation != null) {
                boolean paymentSuccess = rentalSystemService.processPayment(reservation.getReservationId());
                if (paymentSuccess) {
                    System.out.println("Reservation successful. Reservation id: " + reservation.getReservationId());
                } else {
                    System.out.println("Payment failed. Reservation cancelled");
                    rentalSystemService.cancelReservation(reservation.getReservationId());
                }
            } else {
                System.out.println("Car is not available for given dates!");
            }
        } else {
            System.out.println("No car available with the given criteria.");
        }
    }
}
