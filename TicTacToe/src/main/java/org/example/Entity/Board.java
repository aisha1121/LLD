package org.example.Entity;

import org.example.Pair;

import java.util.ArrayList;

public class Board {
    private int size;
    private Piece[][] board;

    public Board(int size) {
        this.size = size;
        board = new Piece[size][size];
    }

    public Piece[][] getBoard() {
        return board;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) System.out.print(board[i][j].getType().name());
                else System.out.print(" ");
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public ArrayList<Pair> getEmptyCells() {
        ArrayList<Pair> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) list.add(new Pair(i, j));
            }
        }
        return list;
    }

    public boolean addPiece(int row, int col, PieceType type) {
        if (row >= size || col >= size || board[row][col] != null) return false;
        board[row][col] = new Piece(type);
        return true;
    }
}
