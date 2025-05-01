package org.example;

import org.example.Entity.Board;
import org.example.Entity.Piece;
import org.example.Entity.PieceType;
import org.example.Entity.Player;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class TicTacToe {
    Deque<Player> players;
    Board board;

    public void initialiseGame() {
        players = new LinkedList<>();
        System.out.println("---------Enter details to start the game--------");
        Scanner sc = new Scanner(System.in);
        boolean initialisationPhase = true;
        while (initialisationPhase) {
            System.out.println("1. To enter player name and pieceType\n2. To enter board size\n3. Stop");
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Enter player name and piece type (X or O): ");
                    String name = sc.next();
                    String pieceType = sc.next().toUpperCase();
                    try {
                        Player player = new Player(name, PieceType.valueOf(pieceType));
                        players.add(player);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid piece type! Please enter X or O.");
                    }
                    break;
                case 2:
                    System.out.println("Enter board size (minimum 3): ");
                    int size = sc.nextInt();
                    if (size < 3) {
                        System.out.println("Board size must be at least 3. Try again.");
                    } else {
                        board = new Board(size);
                    }
                    break;
                case 3:
                    initialisationPhase = false;
                    break;
                default:
                    System.out.println("Press a valid option!");
                    break;
            }
        }
        System.out.println("-----Starting the game------");
    }

    public String startGame() {
        boolean searchingForWinner = true;
        while (searchingForWinner) {
            // get the player
            Player player = players.removeFirst();

            // print the board
            board.printBoard();

            // if no empty cell then return tie
            ArrayList<Pair> list = board.getEmptyCells();
            if (list.isEmpty()) {
                System.out.println("The board is full");
                searchingForWinner = false;
                continue;
            }
            // ask user to input row, col for their turn
            System.out.println("Player: " + player.getName() + " .Enter the row,col.");
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            String[] rowCol = input.split(",");
            int row = Integer.parseInt(rowCol[0]);
            int col = Integer.parseInt(rowCol[1]);
            boolean pieceAdded = board.addPiece(row, col, player.getType());

            // if incorrect input -> add user again at first and ask to enter again -> continue;
            if (!pieceAdded) {
                System.out.println("Enter valid row,col.");
                players.addFirst(player);
                continue;
            }

            // if correct input -> add user at last
            players.addLast(player);

            // if winner found -> return winner
            boolean winnerFound = isWinner(row, col, player.getType());
            if (winnerFound) {
                return player.getName();
            }
        }
        return "tie";
    }

    private boolean isWinner(int row, int col, PieceType type) {
        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diagonalMatch = true;
        boolean antidiagonalMatch = true;
        Piece[][] gameBoard = board.getBoard();

        // for row check
        for (int i = 0 ; i < board.getSize(); i++) {
            if (gameBoard[row][i] == null ||  gameBoard[row][i].getType() != type) {
                rowMatch = false;
                break;
            }
        }

        // for col check
        for (int i = 0 ; i < board.getSize(); i++) {
            if (gameBoard[i][col] == null ||  gameBoard[i][col].getType() != type) {
                colMatch = false;
                break;
            }
        }

        // for diagonal check
        if (row != col) diagonalMatch = false;
        else {
            for (int i = 0; i < board.getSize(); i++) {
                if (gameBoard[i][i] == null || gameBoard[i][i].getType() != type) {
                    diagonalMatch = false;
                    break;
                }
            }
        }

        // for antidiagonal check
        if (col != (board.getSize() - row - 1)) antidiagonalMatch = false;
        else {
            int n = board.getSize();
            for (int i = 0; i < n; i++) {
                if (gameBoard[i][n-i-1] == null ||  gameBoard[i][n-i-1].getType() != type) {
                    antidiagonalMatch = false;
                    break;
                }
            }
        }
        return rowMatch || colMatch || diagonalMatch || antidiagonalMatch;
    }
}
