package org.example.entities;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int BOARD_SIZE = 100;
    private final List<Snake> snakeList;
    private final List<Ladder> ladderList;

    public Board() {
        snakeList = new ArrayList<>();
        ladderList = new ArrayList<>();
        initialiseBoard();
    }

    private void initialiseBoard() {
        snakeList.add(new Snake(50, 24));
        snakeList.add(new Snake(78, 5));
        snakeList.add(new Snake(99, 30));
        snakeList.add(new Snake(45, 9));

        ladderList.add(new Ladder(7, 52));
        ladderList.add(new Ladder(33, 77));
        ladderList.add(new Ladder(69, 92));
        ladderList.add(new Ladder(11, 89));
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    public int getNewPosition(int newPosition) {
        for (Snake snake : snakeList) {
            if (newPosition == snake.getStart()) {
                return snake.getEnd();
            }
        }

        for (Ladder ladder : ladderList) {
            if (newPosition == ladder.getStart()) {
                return ladder.getEnd();
            }
        }

        return newPosition;
    }
}
