package org.example;

import org.example.constants.CoffeeTypeEnum;

public class CoffeeVendingMachineDemo {
    public static void main(String[] args) {
        CoffeeVendingMachine coffeeVendingMachine = CoffeeVendingMachine.getInstance();
        try {
            coffeeVendingMachine.displayMenu();

            processRequest(CoffeeTypeEnum.LATTE, 9, coffeeVendingMachine);
            processRequest(CoffeeTypeEnum.CAPPUCCINO, 80, coffeeVendingMachine);
            processRequest(CoffeeTypeEnum.ESPRESSO, 100, coffeeVendingMachine);

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }

    }

    private static void processRequest(CoffeeTypeEnum coffeeTypeEnum, int pricePaid, CoffeeVendingMachine coffeeVendingMachine) {
        CoffeeRecipe coffeeRecipe  = coffeeVendingMachine.searchForCoffee(coffeeTypeEnum);
        if (coffeeRecipe != null) {
            coffeeVendingMachine.dispenseDrink(coffeeRecipe, pricePaid);
        } else {
            System.out.println("Invalid coffee type entered.");
        }

    }
}
