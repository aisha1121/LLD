package org.example.vehiclecost;

import org.example.vehicle.VehicleType;

public interface VehicleCost {
    int getTotalCost(int hours, VehicleType vehicleType);
}
