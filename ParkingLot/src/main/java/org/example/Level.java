package org.example;

import org.example.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final int floor;
    private List<ParkingSpot> parkingSpotList;

    public Level(int floor, int noOfSpots) {
        this.floor = floor;
        this.parkingSpotList = new ArrayList<>();

        // lets divide spots in 50 : 40 : 10 ratio of bike : car : trucks
        int bikeSpots = (int) (0.5 * noOfSpots);
        int carSpots = (int) (0.4 * noOfSpots);

        for (int i = 1; i <= bikeSpots; i++) {
            parkingSpotList.add(new ParkingSpot(i, VehicleType.BIKE));
        }
        for (int i = bikeSpots + 1; i <= bikeSpots + carSpots; i++) {
            parkingSpotList.add(new ParkingSpot(i, VehicleType.CAR));
        }
        for (int i = bikeSpots + carSpots + 1; i <= noOfSpots; i++) {
            parkingSpotList.add(new ParkingSpot(i, VehicleType.TRUCK));
        }
    }

    public int getFloor() {
        return floor;
    }

    public List<ParkingSpot> getParkingSpotList() {
        return parkingSpotList;
    }

}
