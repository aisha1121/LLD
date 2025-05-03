package org.example.atmstate;

import org.example.entities.ATM;
import org.example.entities.Card;
import org.example.moneywithdrawal.MoneyWithdrawalFactory;
import org.example.moneywithdrawal.MoneyWithdrawalProcessor;

import static org.example.constants.Constant.HUNDRED;

public class CashWithdrawalState extends ATMState {
    public CashWithdrawalState(ATM atm) {
        this.atm = atm;
        System.out.println("Please enter withdrawal amount");
    }

    @Override
    public void withdrawCash(Card card, int withdrawalMoney) {
        // case 1: when atm doesn't have money
        // case 2: when bank account doesn't have sufficient money
        // case 3: withdraw

        if (withdrawalMoney % HUNDRED != 0) {
            System.out.println("Please enter amount multiple of 100!!");
            exit();
        }

        if (withdrawalMoney > atm.getTotalAmount()) {
            System.out.println("ATM doesn't have sufficient cash");
            exit();
        } else if (withdrawalMoney > card.getBankBalance()) {
            System.out.println("Bank account doesn't have sufficient cash");
            exit();
        } else {

            atm.deductMoney(withdrawalMoney);
            card.deductMoney(withdrawalMoney);
            MoneyWithdrawalProcessor moneyWithdrawalProcessor = MoneyWithdrawalFactory.getInstance();
            moneyWithdrawalProcessor.withdrawMoney(atm, withdrawalMoney);
            System.out.println("Please collect money from tray.");
            exit();
        }

    }

    @Override
    public void exit() {
        System.out.println("Collect your card!");
        atm.setAtmState(new IdleState(atm));
    }
}
