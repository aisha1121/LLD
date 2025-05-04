package org.example.payment;

public class CreditCardPayment implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment of " + amount + " done via credit card");
        return true;
    }
}
