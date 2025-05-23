package org.example.entities;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    private String id;
    private Set<String> productIds;

    public Inventory(String id) {
        this.id = id;
        this.productIds = ConcurrentHashMap.newKeySet();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<String> getProducts() {
        return productIds;
    }


    public void addProduct(String productId){
        productIds.add(productId);
    }

    public void removeProduct(String productId) {
        productIds.remove(productId);
    }
}
