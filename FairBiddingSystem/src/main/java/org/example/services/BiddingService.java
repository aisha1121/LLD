package org.example.services;

import org.example.entities.Product;

import java.util.List;

public interface BiddingService {

    void getAllBiddingProducts();

    void addProduct(Product product);

    void endBid(String vendorId, String productId);

    void startBid(String vendorId, String productId);

    void placeBid(String userId, String productId, int price);

    List<Product> getProducts(String vendorId);

    List<String> getUserHistory(String userId);
}
