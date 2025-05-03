package org.example;

import org.example.atmstate.IdleState;
import org.example.constants.TransactionType;
import org.example.entities.ATM;
import org.example.entities.Card;
import org.example.entities.User;

public class ATMRoom {

    private ATM atm;
    private User user;
    private static volatile ATMRoom atmRoom;
    private ATMRoom() {
        initialiseATMRoom();
    }

    public void performTransaction(TransactionType transactionType, int pin, int withdrawalMoney) {
        Card card = user.getCard();
        atm.getAtmState().insertCard(card);
        atm.getAtmState().authenticateCard(card, 1234);

        atm.getAtmState().selectOperation(card, transactionType);
        performOperation(card, transactionType, withdrawalMoney);
    }

    private void performOperation(Card card, TransactionType transactionType, int withdrawalMoney) {
        switch (transactionType) {
            case CHECK_BALANCE:
                atm.getAtmState().displayBalance(card);
                break;
            case CASH_WITHDRAWAL:
                atm.getAtmState().withdrawCash(card, withdrawalMoney);
                break;
            default:
                System.out.println("INVALID transaction");
        }
        System.out.println();
    }

    private void initialiseATMRoom() {
        createATM();
        createUser();
    }

    public void createUser() {
        Card card = new Card("12345678", "Aayusha", 1234);
        this.user = new User("Aayusha", card);
    }

    private void createATM() {
        this.atm = new ATM(10, 10, 10);
        atm.setAtmState(new IdleState(atm));
    }

    public static ATMRoom getInstance() {
        if (atmRoom == null) {
            synchronized (ATMRoom.class) {
                if (atmRoom == null) {
                    atmRoom = new ATMRoom();
                }
            }
        }
        return atmRoom;
    }

    public ATM getAtm() {
        return atm;
    }

    public User getUser() {
        return user;
    }
}
