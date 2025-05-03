package org.example.moneywithdrawal;

import org.example.entities.ATM;

import static org.example.constants.Constant.TWO_THOUSAND;

public class TwoThousandNoteWithdrawal extends MoneyWithdrawalProcessor {
    public TwoThousandNoteWithdrawal(MoneyWithdrawalProcessor next) {
        super(next);
    }

    @Override
    public void withdrawMoney(ATM atm, int withdrawMoney, int noOf2kNotes, int noOf500Notes, int noOf100Notes) {
        int requiredCountOf2kNotes = withdrawMoney / TWO_THOUSAND;
        int remaining = withdrawMoney % TWO_THOUSAND;
        int countOf2kNotes = atm.getNoOfTwoThousandNotes();

        if (requiredCountOf2kNotes <= countOf2kNotes) {
            noOf2kNotes = requiredCountOf2kNotes;
            atm.setNoOfTwoThousandNotes(countOf2kNotes - requiredCountOf2kNotes);
        } else {
            atm.setNoOfTwoThousandNotes(0);
            noOf2kNotes = requiredCountOf2kNotes - countOf2kNotes;
            remaining += noOf2kNotes * TWO_THOUSAND;
        }

        if (remaining != 0) {
            super.withdrawMoney(atm, remaining, noOf2kNotes, noOf500Notes, noOf100Notes);
        } else {
            System.out.println("2k notes: " + noOf2kNotes + "\n500 notes: " + noOf500Notes + "\n100 notes: " + noOf100Notes);
        }
    }
}
