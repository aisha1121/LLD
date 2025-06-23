package org.example.services;

import org.example.entities.BaseUser;

public interface VendorService {
    void register(BaseUser base);
    void addItem(String vendorId, String productId, int basePrice);
    void startBid(String vendorId, String productId);
    void endBid(String vendorId, String productId);
    void showAllBiddings(String vendorId);
}
