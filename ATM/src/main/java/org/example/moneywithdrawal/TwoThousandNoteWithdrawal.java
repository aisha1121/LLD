package org.example.moneywithdrawal;

import org.example.entities.ATM;

import static org.example.constants.Constant.TWO_THOUSAND;

public class TwoThousandNoteWithdrawal extends MoneyWithdrawalProcessor {
    public TwoThousandNoteWithdrawal(MoneyWithdrawalProcessor next) {
        super(next);
    }

    @Override
    public void withdrawMoney(ATM atm, int withdrawMoney) {
        int requiredCountOf2kNotes = withdrawMoney / TWO_THOUSAND;
        int remaining = withdrawMoney % TWO_THOUSAND;
        int countOf2kNotes = atm.getNoOfTwoThousandNotes();

        if (requiredCountOf2kNotes <= countOf2kNotes) {
            atm.setNoOfTwoThousandNotes(countOf2kNotes - requiredCountOf2kNotes);
        } else {
            atm.setNoOfTwoThousandNotes(0);
            remaining += (requiredCountOf2kNotes - countOf2kNotes)*TWO_THOUSAND;
        }

        if (remaining != 0) {
            super.withdrawMoney(atm, remaining);
        }
    }
}
