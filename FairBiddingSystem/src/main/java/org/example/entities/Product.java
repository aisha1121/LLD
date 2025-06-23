package org.example.entities;

public class Product {
    private String productId;
    private int basePrice;
    private int soldPrice;
    private boolean isAvailableForSold;
    private String vendorId;

    public Product(String productId, int basePrice, String vendorId) {
        this.productId = productId;
        this.basePrice = basePrice;
        this.soldPrice = 0;
        this.isAvailableForSold = false;
        this.vendorId = vendorId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(int soldPrice) {
        this.soldPrice = soldPrice;
    }

    public boolean isAvailableForSold() {
        return isAvailableForSold;
    }

    public void setAvailableForSold(boolean availableForSold) {
        isAvailableForSold = availableForSold;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
}
