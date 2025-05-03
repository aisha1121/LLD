package org.example.entities;

public class User {
    private String userName;
    private Card card;

    public User(String userName, Card card) {
        this.userName = userName;
        this.card = card;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

}
