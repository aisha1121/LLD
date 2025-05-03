package org.example.constants;

public enum TransactionType {
    CASH_WITHDRAWAL,
    CHECK_BALANCE;

    public static void showAllTransactionTypes() {
        for (TransactionType type : TransactionType.values()) {
            System.out.println(type.name());
        }
    }
}
