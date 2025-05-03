package org.example.parkinglotservice;

import org.example.Level;
import org.example.ParkingLot;
import org.example.ParkingSpot;
import org.example.vehicle.Vehicle;
import org.example.vehiclecost.VehicleCost;
import org.example.vehiclecost.VehicleCostImpl;

import java.time.LocalDateTime;

public class ParkingServiceImpl implements ParkingService {
    private final ParkingLot parkingLot;

    public ParkingServiceImpl(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public synchronized void parkVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            System.out.println("Invalid vehicle.");
            return;
        }

//        for (Level level : levels) {
//            Optional<ParkingSpot> parkingSpot = level.getParkingSpotList().stream()
//                    .filter(spot -> spot.isAvailable() && spot.getVehicleType().equals(vehicle.getVehicleType()))
//                    .findFirst()
//        }
        for (Level level : parkingLot.getLevels()) {
            for (ParkingSpot parkingSpot : level.getParkingSpotList()) {
                if (parkingSpot.isAvailable() && parkingSpot.getVehicleType().equals(vehicle.getVehicleType())) {
                    parkingSpot.setVehicle(vehicle);
                    parkingSpot.setEntryTime(LocalDateTime.now());
                    System.out.println("Vehicle parked successfully at floor: " + level.getFloor() + " at parking spot: " + parkingSpot.getSpotNumber());
                    return;
                }
            }
        }
        System.out.println("Vehicle cannot be parked. No space available");
    }

    @Override
    public synchronized void unparkVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            System.out.println("Invalid vehicle.");
            return;
        }

        for (Level level : parkingLot.getLevels()) {
            for (ParkingSpot parkingSpot : level.getParkingSpotList()) {
                if (parkingSpot.getVehicle() == vehicle) {

                    int totalHours = parkingSpot.calculateHours();
                    int totalCost = calculateCost(totalHours, vehicle);

                    System.out.println("Amount to be paid: " + totalCost);
                    System.out.println("Vehicle: " + vehicle.getLicensePlate() + " has exit successfully");
                    parkingSpot.setVehicle(null);
                    return;
                }
            }
        }

        System.out.println("No such vehicle exist!!");
    }

    private int calculateCost(int hours, Vehicle vehicle) {
        VehicleCost costCalculator = new VehicleCostImpl();
        return costCalculator.getTotalCost(hours, vehicle.getVehicleType());
    }

    @Override
    public void displayAllParkingSpots() {
        for (Level level : parkingLot.getLevels()) {
            System.out.println("Floor: " + level.getFloor() + " availability: ");
            for (ParkingSpot parkingSpot : level.getParkingSpotList()) {
                System.out.println("Parking spot: " + parkingSpot.getSpotNumber() + ((parkingSpot.isAvailable()) ? " available for " : " occupied by ") + parkingSpot.getVehicleType());
            }
        }
    }
}
