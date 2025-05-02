package org.example;

import org.example.vehicle.Vehicle;
import org.example.vehicle.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingSpot {
    private final int spotNumber;
    private final VehicleType vehicleType;
    private Vehicle vehicle;
    private LocalDateTime entryTime;

    public ParkingSpot(int spotNumber, VehicleType vehicleType) {
        this.spotNumber = spotNumber;
        this.vehicleType = vehicleType;
    }

    public int calculateHours() {
        if (entryTime == null) {
            throw new IllegalArgumentException("Entry time is not set.");
        }
        int hours = (int) Duration.between(entryTime, LocalDateTime.now().plusHours(2)).toHours();
        entryTime = null;
        return hours;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }
}
