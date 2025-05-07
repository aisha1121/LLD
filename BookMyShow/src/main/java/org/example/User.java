package org.example;

import java.util.UUID;

public class User {
    private String userId;
    private String name;

    public User(String name) {
        this.userId = UUID.randomUUID().toString().substring(0, 5);
        this.name = name;
    }
}
