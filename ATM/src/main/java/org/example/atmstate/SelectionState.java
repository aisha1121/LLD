package org.example.atmstate;

import org.example.constants.TransactionType;
import org.example.entities.ATM;
import org.example.entities.Card;

public class SelectionState extends ATMState {
    public SelectionState(ATM atm) {
        this.atm = atm;
        showAllOperations();
    }

    @Override
    public void selectOperation(Card card, TransactionType type) {
        switch (type) {
            case CASH_WITHDRAWAL:
                atm.setAtmState(new CashWithdrawalState(atm));
                break;
            case CHECK_BALANCE:
                atm.setAtmState(new CheckBalanceState(atm, card));
                break;
            default:
                System.out.println("Invalid option picked");
                exit();
        }
    }

    private void showAllOperations() {
        System.out.println("Select the operation!!");
        TransactionType.showAllTransactionTypes();
    }

    @Override
    public void exit() {
        System.out.println("Collect your card!");
        atm.setAtmState(new IdleState(atm));
    }
}
