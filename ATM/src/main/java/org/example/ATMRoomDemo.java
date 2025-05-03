package org.example;

import org.example.constants.TransactionType;

public class ATMRoomDemo {
    public static void main(String[] args) {
        ATMRoom atmRoom = ATMRoom.getInstance();
        atmRoom.performTransaction(TransactionType.CHECK_BALANCE, 1234, 0);
        atmRoom.performTransaction(TransactionType.CASH_WITHDRAWAL, 1234, 2100);
        System.out.println(atmRoom.getAtm().getAtmBalance());
    }
}