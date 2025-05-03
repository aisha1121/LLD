package org.example;

import org.example.atmstate.IdleState;
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
