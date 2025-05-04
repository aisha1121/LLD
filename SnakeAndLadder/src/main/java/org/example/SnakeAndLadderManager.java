package org.example;

import java.util.ArrayList;
import java.util.List;

public class SnakeAndLadderManager {
    private static volatile SnakeAndLadderManager instance;
    private final List<SnakeAndLadderGame> snakeAndLadderGameList;

    private SnakeAndLadderManager() {
        snakeAndLadderGameList = new ArrayList<>();
    }

    public static SnakeAndLadderManager getInstance() {
        if (instance == null) {
            synchronized (SnakeAndLadderManager.class) {
                if (instance == null) {
                    instance = new SnakeAndLadderManager();
                }
            }
        }
        return instance;
    }


    public void startGame(List<String> players) {
        SnakeAndLadderGame game = new SnakeAndLadderGame(players);
        snakeAndLadderGameList.add(game);
        new Thread(() -> game.play()).start();
    }
}
