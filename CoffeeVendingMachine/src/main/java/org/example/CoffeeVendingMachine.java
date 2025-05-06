package org.example;

import org.example.constants.CoffeeTypeEnum;
import org.example.constants.IngredientEnum;
import org.example.ingredient.IngredientStore;

import java.util.HashMap;
import java.util.Map;

public class CoffeeVendingMachine {
    private static volatile CoffeeVendingMachine instance;
    private final IngredientStore ingredientStore;
    private final Map<String, CoffeeRecipe> recipes = new HashMap<>();
    private final Dispenser dispenser;
    private final PaymentProcessor paymentProcessor;

    private CoffeeVendingMachine(){
        this.dispenser = new Dispenser();
        this.paymentProcessor = new PaymentProcessor();
        this.ingredientStore = new IngredientStore();
        initialiseRecipes();
    }

    private void initialiseRecipes() {
        recipes.put(CoffeeTypeEnum.ESPRESSO.name(), new CoffeeRecipe(CoffeeTypeEnum.ESPRESSO, 40,
                Map.of(IngredientEnum.WATER.name(), 20, IngredientEnum.COFFEE.name(), 2)));
        recipes.put(CoffeeTypeEnum.CAPPUCCINO.name(), new CoffeeRecipe(CoffeeTypeEnum.CAPPUCCINO, 80,
                Map.of(IngredientEnum.MILK.name(), 20, IngredientEnum.WATER.name(), 10, IngredientEnum.COFFEE.name(), 2)));        
        recipes.put(CoffeeTypeEnum.LATTE.name(), new CoffeeRecipe(CoffeeTypeEnum.LATTE, 75,
                Map.of(IngredientEnum.MILK.name(), 25, IngredientEnum.WATER.name(), 10, IngredientEnum.COFFEE.name(), 2)));    
    }

    public static CoffeeVendingMachine getInstance() {
        if (instance == null) {
            synchronized (CoffeeVendingMachine.class) {
                if (instance == null) {
                    instance = new CoffeeVendingMachine();
                }
            }
        }
        return instance;
    }


    public synchronized CoffeeRecipe searchForCoffee(CoffeeTypeEnum coffeeTypeEnum) {
        return recipes.getOrDefault(coffeeTypeEnum.name(), null);
    }

    public synchronized void dispenseDrink(CoffeeRecipe coffeeRecipe, int pricePaid) {
        if (pricePaid < coffeeRecipe.getPrice()) {
            System.out.println("Insufficient price paid for " + coffeeRecipe.getCoffeeTypeEnum().name() + "!. Please pay " + coffeeRecipe.getPrice() + " to proceed!");
            return;
        }
        if (!ingredientStore.hasSufficientIngredients(coffeeRecipe.getIngredients())) {
            System.out.println("Insufficient ingredients to prepare " + coffeeRecipe.getCoffeeTypeEnum().name() + " coffee.");
            return;
        }

        ingredientStore.consume(coffeeRecipe.getIngredients());
        dispenser.prepareDrink(coffeeRecipe);

        double change = paymentProcessor.processPayment(pricePaid, coffeeRecipe.getPrice());
        if (change > 0) {
            System.out.println("Please collect your change: Rs " + change + " from tray.");
        }
    }

    public void displayMenu() {
        System.out.println("Available coffee type and its price:");
        for (Map.Entry<String, CoffeeRecipe> entry : recipes.entrySet()) {
            String coffee = entry.getKey();
            int price = entry.getValue().getPrice();
            System.out.println(coffee + ": Rs " + price);
        }
    }
}
