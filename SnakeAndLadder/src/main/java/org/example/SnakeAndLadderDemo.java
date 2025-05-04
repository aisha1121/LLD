package org.example;

import java.util.Arrays;
import java.util.List;

public class SnakeAndLadderDemo {
    public static void main(String[] args) {
        SnakeAndLadderManager manager = SnakeAndLadderManager.getInstance();

        // first game
        List<String> players1 = Arrays.asList("Aisha", "Ankit", "Divya");
        manager.startGame(players1);

        // second game
        List<String> players2 = Arrays.asList("Rohan", "Happy", "Shilpi");
        manager.startGame(players2);
    }
}
