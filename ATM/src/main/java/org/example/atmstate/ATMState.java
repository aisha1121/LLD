package org.example.atmstate;

import org.example.constants.TransactionType;
import org.example.entities.ATM;
import org.example.entities.Card;

public abstract class ATMState {
    ATM atm;
    public void insertCard(Card card) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void authenticateCard(Card card, int pin) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void selectOperation(Card card, TransactionType type) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void withdrawCash(Card card, int withdrawalMoney) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void displayBalance(Card card) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void exit() {
        System.out.println("OOPS!! Something went wrong");
    }

}
