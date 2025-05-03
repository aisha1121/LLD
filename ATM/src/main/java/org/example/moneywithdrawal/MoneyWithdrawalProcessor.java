package org.example.moneywithdrawal;

import org.example.entities.ATM;

public abstract class MoneyWithdrawalProcessor {
    public MoneyWithdrawalProcessor next;

    public MoneyWithdrawalProcessor(MoneyWithdrawalProcessor next) {
        this.next = next;
    }

    public void withdrawMoney(ATM atm, int withdrawMoney, int noOf2kNotes, int noOf500Notes, int noOf100Notes) {
        if (next != null) {
            next.withdrawMoney(atm, withdrawMoney, noOf2kNotes, noOf500Notes, noOf100Notes);
        }
    }
}
