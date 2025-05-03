package org.example.moneywithdrawal;

public class MoneyWithdrawalFactory {
    public static MoneyWithdrawalProcessor getInstance() {
        return new TwoThousandNoteWithdrawal(new FiveHundredNoteWithdrawal(new HundredNoteWithdrawal(null)));
    }
}
