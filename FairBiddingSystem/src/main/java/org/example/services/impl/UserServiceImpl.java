package org.example.services.impl;

import org.example.entities.BaseUser;
import org.example.entities.Product;
import org.example.entities.User;
import org.example.services.BiddingService;
import org.example.services.UserService;

import java.util.*;

public class UserServiceImpl implements UserService {
    Map<String, User> users;
    BiddingService biddingService;

    public UserServiceImpl(BiddingService biddingService) {
        users = new HashMap<>();
        this.biddingService = biddingService;
    }

    @Override
    public void register(BaseUser base) {
        if (users.containsKey(base.getId())) {
            System.out.println("User already registered.");
            return;
        }
        users.putIfAbsent(base.getId(), (User) base);
        System.out.println(base.getId() + " registered!");
    }

    @Override
    public void bid(String userId, String productId, int price) {
        User user = users.get(userId);
        if (user == null) {
            throw new IllegalArgumentException(userId + " not found!");
        }
        biddingService.placeBid(userId, productId, price);

    }

    @Override
    public void showHistory(String userId) {
        User user = users.get(userId);
        if (user == null) {
            throw new IllegalArgumentException(userId + " not found!");
        }
        List<String> history = biddingService.getUserHistory(userId);
        if (history != null) {
            System.out.println("History of: " + userId);
            for (String entry : history) {
                System.out.println(entry);
            }
        }
    }


}
