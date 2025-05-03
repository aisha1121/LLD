package org.example.moneywithdrawal;

import org.example.entities.ATM;

import static org.example.constants.Constant.HUNDRED;

public class HundredNoteWithdrawal extends MoneyWithdrawalProcessor{
    public HundredNoteWithdrawal(MoneyWithdrawalProcessor next) {
        super(next);
    }

    @Override
    public void withdrawMoney(ATM atm, int withdrawMoney) {
        int requiredCountOf2kNotes = withdrawMoney / HUNDRED;
        int remaining = withdrawMoney % HUNDRED;
        int countOf2kNotes = atm.getNoOfTwoThousandNotes();

        if (requiredCountOf2kNotes <= countOf2kNotes) {
            atm.setNoOfTwoThousandNotes(countOf2kNotes - requiredCountOf2kNotes);
        } else {
            atm.setNoOfTwoThousandNotes(0);
            remaining += (requiredCountOf2kNotes - countOf2kNotes)*HUNDRED;
        }

        if (remaining != 0) {
            System.out.println("Something went wrong!");
        }
    }
}
