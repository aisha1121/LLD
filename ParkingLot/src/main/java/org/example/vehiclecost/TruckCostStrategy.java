package org.example.vehiclecost;

public class TruckCostStrategy implements CostStrategy{
    @Override
    public int calculateCost(int hours) {
        return 30 * hours;
    }
}
