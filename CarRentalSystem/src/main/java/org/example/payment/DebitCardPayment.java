package org.example.payment;

public class DebitCardPayment implements PaymentProcessor{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment of " + amount + " done via debit card");
        return true;
    }
}
