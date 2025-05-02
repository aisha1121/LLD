package org.example.vehiclecost;

public class BikeCostStrategy implements CostStrategy {
    @Override
    public int calculateCost(int hours) {
        return 10 * hours;
    }
}
