package org.example;

import org.example.entities.Board;
import org.example.entities.Dice;
import org.example.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class SnakeAndLadderGame {
    private final Board board;
    private final Dice dice;
    private final List<Player> players;
    private int currentPlayerIndex = 0;

    public SnakeAndLadderGame(List<String> playersName) {
        this.board = new Board();
        this.dice = new Dice();
        this.players = new ArrayList<>();
        for (String player : playersName) {
            players.add(new Player(player));
        }
    }

    public void play() {
        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);
            int diceRoll = dice.roll();
            int newPosition = currentPlayer.getCurrentPosition() + diceRoll;

            if (newPosition <= board.getBoardSize()) {
                currentPlayer.setCurrentPosition(board.getNewPosition(newPosition));
            }

            System.out.println(currentPlayer.getName() + " rolled a dice: " + diceRoll + " and moved to position: " + currentPlayer.getCurrentPosition());
            if (currentPlayer.getCurrentPosition() == board.getBoardSize()) {
                System.out.println(currentPlayer.getName() + " wins!");
                break;
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }
}
