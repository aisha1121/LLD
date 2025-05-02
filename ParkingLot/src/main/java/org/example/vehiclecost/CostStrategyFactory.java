package org.example.vehiclecost;

import org.example.vehicle.VehicleType;

public class CostStrategyFactory {
    public static CostStrategy getStrategy(VehicleType vehicleType) {
        return switch (vehicleType) {
            case CAR -> new CarCostStrategy();
            case BIKE -> new BikeCostStrategy();
            case TRUCK -> new TruckCostStrategy();
            default -> throw new IllegalArgumentException("Unsupported vehicle type");
        };
    }
}
