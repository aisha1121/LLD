package org.example;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private static volatile ParkingLot parkingLot;
    private final List<Level> levels;
    private ParkingLot() {
        this.levels = new ArrayList<>();
    }
    public static ParkingLot getInstance() {
        if (parkingLot == null) {
            synchronized (ParkingLot.class) {
                if (parkingLot == null) {
                    parkingLot = new ParkingLot();
                }
            }
        }
        return parkingLot;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void addLevel(Level level) {
        levels.add(level);
    }

}
