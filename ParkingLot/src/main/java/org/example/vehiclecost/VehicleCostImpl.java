package org.example.vehiclecost;

import org.example.vehicle.VehicleType;

public class VehicleCostImpl implements VehicleCost{
    @Override
    public int getTotalCost(int hours, VehicleType vehicleType) {
        CostStrategy costStrategy = CostStrategyFactory.getStrategy(vehicleType);
        return costStrategy.calculateCost(hours);
    }
}
