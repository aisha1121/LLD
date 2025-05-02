package org.example.vehiclecost;

public class CarCostStrategy implements CostStrategy {
    @Override
    public int calculateCost(int hours) {
        return 20 * hours;
    }
}
