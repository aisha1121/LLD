package org.example.payment;

public class UPIPayment implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment of " + amount + " done via UPI");
        return true;
    }
}
