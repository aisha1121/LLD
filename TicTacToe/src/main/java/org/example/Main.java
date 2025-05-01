package org.example;

public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.initialiseGame();
        System.out.println("Winner is: " + game.startGame());
    }
}