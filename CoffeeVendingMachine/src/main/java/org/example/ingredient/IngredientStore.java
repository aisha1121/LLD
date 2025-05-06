package org.example.ingredient;

import org.example.constants.IngredientEnum;

import java.util.HashMap;
import java.util.Map;

/*
    * better way to create IngredientStoreManager which handles all the operations
    * here after every consumption if any ingredient reaches threshold then we can notify them
    * eg by doing observer design pattern
    *
 */
public class IngredientStore {
    private final Map<String, Integer> ingredients = new HashMap<>();

    public IngredientStore() {
        initialiseIngredientStore();
    }

    private void initialiseIngredientStore() {
        ingredients.put(IngredientEnum.MILK.name(), 100);
        ingredients.put(IngredientEnum.WATER.name(), 100);
        ingredients.put(IngredientEnum.COFFEE.name(), 100);
    }

    public synchronized void refill(IngredientEnum ingredientEnum, int quantity) {
        ingredients.put(ingredientEnum.name(), ingredients.getOrDefault(ingredientEnum.name(), 0) + quantity);
    }

    public synchronized boolean hasSufficientIngredients(Map<String, Integer> requiredIngredients) {
        for (Map.Entry<String, Integer> entry : requiredIngredients.entrySet()) {
            String ingredientType = entry.getKey();
            int quantity = entry.getValue();
            if (ingredients.getOrDefault(ingredientType, 0) < quantity) return false;
        }
        return true;
    }

    public void consume(Map<String, Integer> requiredIngredients) {
        for (Map.Entry<String, Integer> entry : requiredIngredients.entrySet()) {
            String ingredientType = entry.getKey();
            int quantity = entry.getValue();
            ingredients.put(ingredientType, ingredients.get(ingredientType) - quantity);
            // check threshold and send notification
        }
    }
}
