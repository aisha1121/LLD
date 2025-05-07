package org.example;

public class Seat {
    private int seatNumber;
    private SeatType seatType;
    private SeatStatus seatStatus;
    private int cost;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.seatStatus = SeatStatus.AVAILABLE;
        if (seatNumber <= 50) {
            this.seatType = SeatType.SILVER;
            // should we add decorator pattern for movie based pricing and having a multiplier here?
            this.cost = 100;
        }
        else {
            this.seatType = SeatType.GOLD;
            this.cost = 200;
        }
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
