package org.example;

public class PaymentProcessor {
    public double processPayment(int pricePaid, int price) {
        double change = pricePaid - price;
        System.out.println("Payment processed of Rs " + pricePaid);
        return change;
    }
}
