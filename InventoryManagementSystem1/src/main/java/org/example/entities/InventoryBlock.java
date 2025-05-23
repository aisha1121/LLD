package org.example.entities;

import java.time.Duration;
import java.time.LocalDateTime;

public class InventoryBlock {
    private String orderId;
    private String productId;
    private int count;
    private LocalDateTime currentDate;

    public InventoryBlock(String orderId, String productId, int count) {
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
        this.currentDate = LocalDateTime.now();
    }

    public boolean isExpired() {
        Duration duration = Duration.between(currentDate, LocalDateTime.now());
        return duration.toSeconds() >= 5;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDateTime getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDateTime currentDate) {
        this.currentDate = currentDate;
    }
}
