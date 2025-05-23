package org.example.entities;

import java.util.UUID;

public class Order {
    private String orderId;
    private String productId;
    private int count;

    public Order(String productId, int count) {
        this.orderId = UUID.randomUUID().toString();
        this.productId = productId;
        this.count = count;
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
}
