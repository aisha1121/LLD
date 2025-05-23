package org.example.entities;

import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    private String productId;
    private String name;
    private AtomicInteger availableCount;

    public Product(String productId, String name, int count) {
        this.productId = productId;
        this.name = name;
        this.availableCount = new AtomicInteger(count);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableCount() {
        return availableCount.get();
    }

    public boolean decrease(int quantity) {
        while (true) {
            int current = availableCount.get();
            if(current < quantity)  return false;
            if(availableCount.compareAndSet(current, current - quantity))   return true;
        }
    }

    public void increase(int quantity){
        availableCount.addAndGet(quantity);
    }
}
