package org.example;

import org.example.parkinglotservice.ParkingService;
import org.example.parkinglotservice.ParkingServiceImpl;
import org.example.vehicle.Bike;
import org.example.vehicle.Car;
import org.example.vehicle.Truck;
import org.example.vehicle.Vehicle;

public class ParkingLotDemo {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.addLevel(new Level(1, 10));
        parkingLot.addLevel(new Level(2, 20));

        Vehicle car = new Car("KA-45783");
        Vehicle bike = new Bike("KA-YR84U3");
        Vehicle truck = new Truck("KA-Y83283");

        ParkingService parkingService = new ParkingServiceImpl(parkingLot);

        parkingService.parkVehicle(car);
        parkingService.parkVehicle(bike);
        parkingService.parkVehicle(truck);

        parkingService.displayAllParkingSpots();

        parkingService.unparkVehicle(car);

        parkingService.displayAllParkingSpots();
    }
}