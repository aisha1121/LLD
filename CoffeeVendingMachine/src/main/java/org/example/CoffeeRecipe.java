package org.example;

import org.example.constants.CoffeeTypeEnum;

import java.util.Map;

public class CoffeeRecipe {
    private final CoffeeTypeEnum coffeeTypeEnum;
    private final int price;
    private final Map<String, Integer> ingredients;

    public CoffeeRecipe(CoffeeTypeEnum coffeeTypeEnum, int price, Map<String, Integer> ingredients) {
        this.coffeeTypeEnum = coffeeTypeEnum;
        this.price = price;
        this.ingredients = ingredients;
    }

    public CoffeeTypeEnum getCoffeeTypeEnum() {
        return coffeeTypeEnum;
    }

    public int getPrice() {
        return price;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }
}
