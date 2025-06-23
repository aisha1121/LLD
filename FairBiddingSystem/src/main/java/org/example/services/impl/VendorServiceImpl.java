package org.example.services.impl;

import org.example.entities.BaseUser;
import org.example.entities.Product;
import org.example.entities.Vendor;
import org.example.services.BiddingService;
import org.example.services.VendorService;

import java.util.*;

public class VendorServiceImpl implements VendorService {
    Map<String, Vendor> vendors;
    Map<String, Integer> vendorProductMap;
    BiddingService biddingService;
    private static final int MAX_ITEMS = 2;

    public VendorServiceImpl(BiddingService biddingService) {
        vendors = new HashMap<>();
        vendorProductMap = new HashMap<>();
        this.biddingService = biddingService;
    }

    @Override
    public void register(BaseUser base) {
        if (vendors.containsKey(base.getId())) {
            System.out.println("Vendor already registered.");
            return;
        }
        vendors.putIfAbsent(base.getId(), (Vendor) base);
        System.out.println(base.getId() + " registered!");
    }

    @Override
    public void addItem(String vendorId, String productId, int basePrice) {
        Product product = new Product(productId, basePrice, vendorId);
        if (!vendors.containsKey(vendorId)) {
            throw new IllegalArgumentException(vendorId + " not present in system!");
        }

        if (vendorProductMap.getOrDefault(vendorId, 0) == MAX_ITEMS) {
            throw new IllegalArgumentException("Maximum no of items reached by " + vendorId);
        }
        biddingService.addProduct(product);
        vendorProductMap.put(vendorId, vendorProductMap.getOrDefault(vendorId, 0) + 1);
    }

    @Override
    public void startBid(String vendorId, String productId) {
        if (!vendors.containsKey(vendorId)) {
            throw new IllegalArgumentException(vendorId + " not present in system!");
        }

        biddingService.startBid(vendorId, productId);
    }

    @Override
    public void endBid(String vendorId, String productId) {
        if (!vendors.containsKey(vendorId)) {
            throw new IllegalArgumentException(vendorId + " not present in system!");
        }
        biddingService.endBid(vendorId, productId);
    }

    @Override
    public void showAllBiddings(String vendorId) {
        if (!vendors.containsKey(vendorId)) {
            throw new IllegalArgumentException(vendorId + " not present in system!");
        }
        System.out.println("Products of " + vendorId + " are: ");
        List<Product> productList = biddingService.getProducts(vendorId);
        for (Product product : productList) {
            System.out.println(product.getProductId() + " Base Price: " + product.getBasePrice()
                                + " Sold Price: " + product.getSoldPrice() + " Profit: "
                    + (product.getSoldPrice() > product.getBasePrice() ? product.getSoldPrice() - product.getBasePrice() : 0));
        }

    }
}
