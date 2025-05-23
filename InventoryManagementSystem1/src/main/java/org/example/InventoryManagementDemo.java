package org.example;


import org.example.manager.InventoryManager;

import java.util.Arrays;

public class InventoryManagementDemo {
    public static void main(String[] args) {
        InventoryManager inventoryManager = InventoryManager.getInstance();
        // add product
        inventoryManager.createProduct("P1", "iphone", 10);
        inventoryManager.createProduct("P2", "laptop", 10);
        inventoryManager.createProduct("P3", "bottle", 10);


        // create inventory
        inventoryManager.createInventory("I1", Arrays.asList("P1", "P2", "P3"));

        // case1: if we are blocking
        // getinventory
        int count = inventoryManager.getinventory("P1");
        System.out.println("Count of product P1: " + count);

        // blockInventory
        try {
            inventoryManager.blockinventory("P1", 5, "order1");
            System.out.println("after blocking count: " + inventoryManager.getinventory("P1"));
            Thread.sleep(2 );

            // confirm
            boolean confirmedOrder = inventoryManager.confirmOrder("order1");
            System.out.println("Order confirmation: " + confirmedOrder);

            // print available product
            System.out.println(inventoryManager.getinventory("P1"));


            // case2: failure case
            inventoryManager.blockinventory("P2", 5, "order2");
            System.out.println("Current count: " + inventoryManager.getinventory("P2"));
            Thread.sleep(6000);

            System.out.println("Current count of P2: " + inventoryManager.getinventory("P2"));
        } catch (Exception e) {
            System.out.println("Exception is: " + e.getMessage());
        }
    }
}
