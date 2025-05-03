package org.example.entities;

import static org.example.constants.Constant.*;

public class Card {
    private String cardNo;
    private String cardHolderName;
    private int pin;
    private int cvv;
    private String expiryDate;
    private BankAccount bankAccount;

    public Card(String cardNo, String cardHolderName, int pin) {
        this.cardNo = cardNo;
        this.cardHolderName = cardHolderName;
        this.pin = pin;
        initialiseBankDetails();
    }

    private void initialiseBankDetails() {
        this.cvv = CVV;
        this.expiryDate = EXPIRY_DATE;
        this.bankAccount = new BankAccount(cardNo, BANK_BALANCE);
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public int getBankBalance() {
        return bankAccount.getBankBalance();
    }

    public void deductMoney(int withdrawalMoney) {
        bankAccount.setBankBalance(getBankBalance() - withdrawalMoney);
    }
}
