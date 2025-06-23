package org.example;

import org.example.entities.BaseUser;
import org.example.entities.User;
import org.example.entities.Vendor;
import org.example.services.BiddingService;
import org.example.services.UserService;
import org.example.services.VendorService;
import org.example.services.impl.BiddingServiceImpl;
import org.example.services.impl.UserServiceImpl;
import org.example.services.impl.VendorServiceImpl;

public class FKBiddingSystem {

    private static volatile FKBiddingSystem instance;

    private final VendorService vendorService;
    private final UserService userService;
    private final BiddingService biddingService;


    private FKBiddingSystem() {
        biddingService = new BiddingServiceImpl();
        userService = new UserServiceImpl(biddingService);
        vendorService = new VendorServiceImpl(biddingService);
    }

    public static FKBiddingSystem getInstance() {
        if (instance == null) {
            synchronized (FKBiddingSystem.class) {
                if (instance == null) {
                    instance = new FKBiddingSystem();
                }
            }
        }
        return instance;
    }

    public void addItem(String vendor, String product, int basePrice) {
        vendorService.addItem(vendor, product, basePrice);
    }

    public void startBid(String vendor, String product) {
        vendorService.startBid(vendor, product);
    }

    public void register(BaseUser baseUser) {
        if (baseUser instanceof User) {
            userService.register(baseUser);
        } else if (baseUser instanceof Vendor) {
            vendorService.register(baseUser);
        } else {
            System.out.println("Type not supported!");
        }
    }

    public void showAllItemsForBid() {
        biddingService.getAllBiddingProducts();
    }

    public void placeBid(String userId, String productId, int bidAmount) {
        userService.bid(userId, productId, bidAmount);
    }

    public void endBid(String vendorId, String productId) {
        vendorService.endBid(vendorId, productId);
    }

    public void showUserHistory(String userId) {
        userService.showHistory(userId);
    }

    public void showVendorProducts(String vendorId) {
        vendorService.showAllBiddings(vendorId);
    }
}
