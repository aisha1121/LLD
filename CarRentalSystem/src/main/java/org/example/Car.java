package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class Car {
    private final String company;
    private final String model;
    private final String plateNumber;
    private final double rentalPricePerDay;
    private final ReentrantLock lock = new ReentrantLock();

    public Car(String company, String model, String plateNumber, double rentalPricePerDay) {
        this.company = company;
        this.model = model;
        this.plateNumber = plateNumber;
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }

    public String getCompany() {
        return company;
    }

    public String getModel() {
        return model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }
}
