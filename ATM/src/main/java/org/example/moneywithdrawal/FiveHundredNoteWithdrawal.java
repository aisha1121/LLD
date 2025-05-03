package org.example.moneywithdrawal;

import org.example.entities.ATM;

import static org.example.constants.Constant.FIVE_HUNDRED;

public class FiveHundredNoteWithdrawal extends MoneyWithdrawalProcessor{
    public FiveHundredNoteWithdrawal(MoneyWithdrawalProcessor next) {
        super(next);
    }

    @Override
    public void withdrawMoney(ATM atm, int withdrawMoney) {
        int requiredCountOf2kNotes = withdrawMoney / FIVE_HUNDRED;
        int remaining = withdrawMoney % FIVE_HUNDRED;
        int countOf2kNotes = atm.getNoOfTwoThousandNotes();

        if (requiredCountOf2kNotes <= countOf2kNotes) {
            atm.setNoOfTwoThousandNotes(countOf2kNotes - requiredCountOf2kNotes);
        } else {
            atm.setNoOfTwoThousandNotes(0);
            remaining += (requiredCountOf2kNotes - countOf2kNotes)*FIVE_HUNDRED;
        }

        if (remaining != 0) {
            super.withdrawMoney(atm, remaining);
        }
    }
}
