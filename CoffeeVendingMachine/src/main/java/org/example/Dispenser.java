package org.example;

public class Dispenser {
    public void prepareDrink(CoffeeRecipe coffeeRecipe) {
        System.out.println(coffeeRecipe.getCoffeeTypeEnum().name() + " is ready. Please collect.");
    }
}
