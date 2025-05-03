package org.example.moneywithdrawal;

import org.example.entities.ATM;

public abstract class MoneyWithdrawalProcessor {
    public MoneyWithdrawalProcessor next;

    public MoneyWithdrawalProcessor(MoneyWithdrawalProcessor next) {
        this.next = next;
    }

    public void withdrawMoney(ATM atm, int withdrawMoney) {
        if (next != null) {
            next.withdrawMoney(atm, withdrawMoney);
        }
    }
}
