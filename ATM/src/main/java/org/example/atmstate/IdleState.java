package org.example.atmstate;

import org.example.entities.ATM;
import org.example.entities.Card;

public class IdleState extends ATMState {

    public IdleState() {

    }
    public IdleState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("Card inserted!!");
        atm.setAtmState(new HasCardState(atm));
    }
}
