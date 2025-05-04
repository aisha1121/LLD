package org.example.entities;

public class Dice {
    public int roll() {
        return (int) ((Math.random()*10)%6 + 1);
    }
}
