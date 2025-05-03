package org.example.atmstate;

import org.example.entities.ATM;
import org.example.entities.Card;

public class HasCardState extends ATMState {

    public HasCardState(ATM atm) {
        this.atm = atm;
        System.out.println("Enter the pin!");
    }

    @Override
    public void authenticateCard(Card card, int pin) {
        if (pin == card.getPin()) {
            System.out.println("Authentication successful");
            atm.setAtmState(new SelectionState(atm));
        } else {
            System.out.println("Invalid pin entered");
            exit();
        }
    }

    @Override
    public void exit() {
        System.out.println("Collect your card!");
        atm.setAtmState(new IdleState(atm));
    }
}
