package org.example.services;

import org.example.entities.BaseUser;
import org.example.entities.Product;

import java.util.List;

public interface UserService {
    void register(BaseUser base);
    void bid(String userId, String productId, int price);

    void showHistory(String userId);
}
