package org.example.atmstate;

import org.example.entities.ATM;
import org.example.entities.Card;

public class CheckBalanceState extends ATMState {

    public CheckBalanceState(ATM atm, Card card) {
        this.atm = atm;
        displayBalance(card);
    }

    @Override
    public void displayBalance(Card card) {
        System.out.println("Your current bank balance is: " + card.getBankBalance());
        exit();
    }

    @Override
    public void exit() {
        System.out.println("Collect your card!");
        atm.setAtmState(new IdleState(atm));
    }
}
