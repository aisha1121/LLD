package org.example.parkinglotservice;

import org.example.vehicle.Vehicle;

public interface ParkingService {
    void parkVehicle(Vehicle vehicle);
    void unparkVehicle(Vehicle vehicle);
    void displayAllParkingSpots();
}
