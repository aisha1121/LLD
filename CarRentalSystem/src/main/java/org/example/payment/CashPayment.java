package org.example.payment;

public class CashPayment implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment of " + amount + " done via cash");
        return true;
    }
}
