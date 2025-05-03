package org.example.entities;

public class BankAccount {
    private String accountNumber;
    private int bankBalance;

    public BankAccount(String accountNumber, int bankBalance) {
        this.accountNumber = accountNumber;
        this.bankBalance = bankBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(int bankBalance) {
        this.bankBalance = bankBalance;
    }
}
