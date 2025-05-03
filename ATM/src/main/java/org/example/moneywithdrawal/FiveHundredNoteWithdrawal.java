package org.example.moneywithdrawal;

import org.example.entities.ATM;

import static org.example.constants.Constant.FIVE_HUNDRED;

public class FiveHundredNoteWithdrawal extends MoneyWithdrawalProcessor{
    public FiveHundredNoteWithdrawal(MoneyWithdrawalProcessor next) {
        super(next);
    }

    @Override
    public void withdrawMoney(ATM atm, int withdrawMoney, int noOf2kNotes, int noOf500Notes, int noOf100Notes) {
        int requiredCountOf500Notes = withdrawMoney / FIVE_HUNDRED;
        int remaining = withdrawMoney % FIVE_HUNDRED;
        int countOf500Notes = atm.getNoOfFiveHundredNotes();

        if (requiredCountOf500Notes <= countOf500Notes) {
            noOf500Notes = requiredCountOf500Notes;
            atm.setNoOfFiveHundredNotes(countOf500Notes - requiredCountOf500Notes);
        } else {
            atm.setNoOfFiveHundredNotes(0);
            noOf500Notes = requiredCountOf500Notes - countOf500Notes;
            remaining += noOf500Notes * FIVE_HUNDRED;
        }

        if (remaining != 0) {
            super.withdrawMoney(atm, remaining, noOf2kNotes, noOf500Notes, noOf100Notes);
        } else {
            System.out.println("2k notes: " + noOf2kNotes + "\n500 notes: " + noOf500Notes + "\n100 notes: " + noOf100Notes);
        }
    }
}
