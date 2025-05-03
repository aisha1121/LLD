package org.example.moneywithdrawal;

import org.example.entities.ATM;

import static org.example.constants.Constant.HUNDRED;

public class HundredNoteWithdrawal extends MoneyWithdrawalProcessor{
    public HundredNoteWithdrawal(MoneyWithdrawalProcessor next) {
        super(next);
    }

    @Override
    public void withdrawMoney(ATM atm, int withdrawMoney, int noOf2kNotes, int noOf500Notes, int noOf100Notes) {
        int requiredCountOf100Notes = withdrawMoney / HUNDRED;
        int remaining = withdrawMoney % HUNDRED;
        int countOf100Notes = atm.getNoOfHundredNotes();

        if (requiredCountOf100Notes <= countOf100Notes) {
            noOf100Notes = requiredCountOf100Notes;
            atm.setNoOfHundredNotes(countOf100Notes - requiredCountOf100Notes);
        } else {
            atm.setNoOfHundredNotes(0);
            noOf100Notes = requiredCountOf100Notes - countOf100Notes;
            remaining += noOf100Notes * HUNDRED;
        }

        if (remaining != 0) {
            System.out.println("Something went wrong!");
        } else {
            System.out.println("2k notes: " + noOf2kNotes + "\n500 notes: " + noOf500Notes + "\n100 notes: " + noOf100Notes);
        }
    }
}
