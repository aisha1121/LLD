package org.example.services.impl;

import org.example.entities.Product;
import org.example.services.BiddingService;

import java.util.ArrayList;
import java.util.*;

public class BiddingServiceImpl implements BiddingService {
    Map<String, Product> products;
    // {productId -> Map<userId, maxAmt>}
    Map<String, Map<String, Integer>> productUserBidMap;
    // {userId -> List[productId:amt]}
    Map<String, List<String>> userBids;

    public BiddingServiceImpl() {
        products = new HashMap<>();
        productUserBidMap = new HashMap<>();
        userBids = new HashMap<>();
    }

    @Override
    public void getAllBiddingProducts() {
        List<Product> productList = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.isAvailableForSold()) {
                productList.add(product);
            }
        }
        System.out.println("Products for bidding are: ");
        for (Product product : productList) {
            System.out.println(product.getProductId() + " " + product.getBasePrice());
        }
    }

    @Override
    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    @Override
    public void endBid(String vendorId, String productId) {
        for (Product product : products.values()) {
            if (product.getProductId().equals(productId) && product.getVendorId().equals(vendorId)) {
                product.setAvailableForSold(false);
                Map<String, Integer> userBidMap = productUserBidMap.get(productId);
                if (userBidMap.isEmpty()) {
                    System.out.println("Bid ended for " + vendorId + " with sold price 0 for " + productId);
                    return;
                }
                String userId = null;
                int bidAmt = product.getBasePrice();
                for (Map.Entry<String, Integer> entry : userBidMap.entrySet()) {
                    if (entry.getValue() > bidAmt) {
                        userId = entry.getKey();
                        bidAmt = entry.getValue();
                    }
                }

                if (bidAmt > product.getBasePrice()) {
                    product.setSoldPrice(bidAmt);
                    int profit = product.getSoldPrice() - product.getBasePrice();
                    System.out.println("Bid ended for " + vendorId + " " + userId + " won the bid with bid price " + bidAmt
                                        + " and you made a profit of " + profit);
                }
                return;
            }
        }
        System.out.println(productId + " not found for " + vendorId);
    }

    @Override
    public void startBid(String vendorId, String productId) {
        Product product = products.get(productId);
        if (product == null) {
            throw new IllegalArgumentException(productId + " not found!");
        }
        if (!product.getVendorId().equals(vendorId)) {
            throw new IllegalArgumentException(productId + " doesn't belongs to " + vendorId);
        }
        product.setAvailableForSold(true);
        productUserBidMap.putIfAbsent(productId, new HashMap<>());
    }

    @Override
    public void placeBid(String userId, String productId, int price) {
        Product product = products.get(productId);
        if (product == null || !product.isAvailableForSold()) {
            throw new IllegalArgumentException(productId + " doesn't exist or bidding is currently not open!");
        }

        if (price < product.getBasePrice()) {
            System.out.println("BidAmount should be >= " + product.getBasePrice());
            return;
        }
        userBids.putIfAbsent(userId, new ArrayList<>());
        userBids.getOrDefault(userId, new ArrayList<>()).add(productId + ":" + price);
        productUserBidMap.putIfAbsent(productId, new HashMap<>());
        Map<String, Integer> userBidMap = productUserBidMap.get(productId);
        int currAmt = userBidMap.getOrDefault(userId, 0);
        if (currAmt < price) {
            userBidMap.put(userId, price);
        }
        System.out.println("Bid placed by " + userId + " of amount " + price);
    }

    @Override
    public List<Product> getProducts(String vendorId) {
        List<Product> productList = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getVendorId().equals(vendorId)) {
                productList.add(product);
            }
        }
        return productList;
    }

    @Override
    public List<String> getUserHistory(String userId) {
        List<String> history = userBids.get(userId);
        List<String> userHistory = null;
        // TODO
        return userHistory;
    }
}
