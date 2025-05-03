package org.example;

import org.example.constants.TransactionType;
import org.example.entities.ATM;
import org.example.entities.Card;
import org.example.entities.User;

public class ATMRoomDemo {
    public static void main(String[] args) {
        ATMRoom atmRoom = ATMRoom.getInstance();
        ATM atm = atmRoom.getAtm();
        User user = atmRoom.getUser();
        Card card = user.getCard();

        // case 1: checking the balance
        atm.getAtmState().insertCard(card);
        atm.getAtmState().authenticateCard(card, 1234);

        atm.getAtmState().selectOperation(card, TransactionType.CHECK_BALANCE);

        // case 2: withdrawal of money
        atm.getAtmState().insertCard(card);
        atm.getAtmState().authenticateCard(card, 1234);

        atm.getAtmState().selectOperation(card, TransactionType.CASH_WITHDRAWAL);
    }
}
