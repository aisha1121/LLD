package org.example;


import org.example.entities.User;
import org.example.entities.Vendor;

public class BiddingSystemDemo {
    public static void main(String[] args) {
        FKBiddingSystem biddingSystem = FKBiddingSystem.getInstance();

        try {
            biddingSystem.register(new User("User1", "9999999999", "u1@gmail.com"));
            biddingSystem.register(new User("User2", "9999999990", "u2@gmail.com"));
            biddingSystem.register(new User("User3", "9999999991", "u3@gmail.com"));

            biddingSystem.register(new Vendor("Vendor1", "9999999992", "v1@gmail.com"));
            biddingSystem.register(new Vendor("Vendor2", "9999999993", "v2@gmail.com"));
            biddingSystem.register(new Vendor("Vendor3", "9999999994", "v3@gmail.com"));

            biddingSystem.addItem("Vendor1", "Product1", 200);
            biddingSystem.addItem("Vendor1", "Product2", 300);
            biddingSystem.addItem("Vendor1", "Product3", 200);


            biddingSystem.addItem("Vendor2", "Product3", 500);

            biddingSystem.startBid("Vendor1", "Product1");

            biddingSystem.showAllItemsForBid();

            biddingSystem.placeBid("User1", "Product1", 300);
            biddingSystem.placeBid("User2", "Product1", 400);
            biddingSystem.placeBid("User1", "Product3", 500);

            biddingSystem.endBid("Vendor1", "Product1");

            biddingSystem.showUserHistory("User1");
            biddingSystem.showVendorProducts("Vendor1");
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }




    }
}
