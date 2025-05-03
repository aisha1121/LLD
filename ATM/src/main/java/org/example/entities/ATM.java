package org.example.entities;

import org.example.atmstate.ATMState;
import org.example.atmstate.IdleState;

import static org.example.constants.Constant.*;

public class ATM {
    private ATMState atmState;
    private int atmBalance;
    private int noOfTwoThousandNotes;
    private int noOfHundredNotes;
    private int noOfFiveHundredNotes;

    public ATM() {
        this.atmState = new IdleState();
    }

    public ATM(int noOfTwoThousandNotes, int noOfHundredNotes, int noOfFiveHundredNotes) {
        this.atmState = new IdleState();
        this.noOfTwoThousandNotes = noOfTwoThousandNotes;
        this.noOfFiveHundredNotes = noOfFiveHundredNotes;
        this.noOfHundredNotes = noOfHundredNotes;
        this.atmBalance = getTotalAmount();

    }

    public int getTotalAmount() {
        int totalBalance = (TWO_THOUSAND * noOfTwoThousandNotes) + (FIVE_HUNDRED * noOfFiveHundredNotes) + (HUNDRED * noOfHundredNotes);
        setAtmBalance(totalBalance);
        return totalBalance;
    }

    public int getAtmBalance() {
        return atmBalance;
    }

    public void setAtmBalance(int atmBalance) {
        this.atmBalance = atmBalance;
    }

    public ATMState getAtmState() {
        return atmState;
    }

    public void setAtmState(ATMState atmState) {
        this.atmState = atmState;
    }

    public int getNoOfTwoThousandNotes() {
        return noOfTwoThousandNotes;
    }

    public void setNoOfTwoThousandNotes(int noOfTwoThousandNotes) {
        this.noOfTwoThousandNotes = noOfTwoThousandNotes;
    }

    public int getNoOfHundredNotes() {
        return noOfHundredNotes;
    }

    public void setNoOfHundredNotes(int noOfHundredNotes) {
        this.noOfHundredNotes = noOfHundredNotes;
    }

    public int getNoOfFiveHundredNotes() {
        return noOfFiveHundredNotes;
    }

    public void setNoOfFiveHundredNotes(int noOfFiveHundredNotes) {
        this.noOfFiveHundredNotes = noOfFiveHundredNotes;
    }

    public void deductMoney(int withdrawalMoney) {
        setAtmBalance(getAtmBalance() - withdrawalMoney);
    }
}
