package org.example.manager;

import org.example.entities.Inventory;
import org.example.entities.InventoryBlock;
import org.example.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InventoryManager {
    private volatile static InventoryManager instance;
    private final Map<String, Product> productMap = new ConcurrentHashMap<>();
    private final Map<String, Inventory> inventoryMap = new ConcurrentHashMap<>();
    private final Set<String> confirmOrders = ConcurrentHashMap.newKeySet();
    private final Map<String, InventoryBlock> blockMap = new ConcurrentHashMap<>();
    private final Lock lock = new ReentrantLock();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private InventoryManager() {
        scheduler.scheduleAtFixedRate(this::cleanExpiredBlocks, 0, 1, TimeUnit.SECONDS);
    }

    public static InventoryManager getInstance() {
        if (instance == null) {
            synchronized (InventoryManager.class) {
                if (instance == null) {
                    instance = new InventoryManager();
                }
            }
        }
        return instance;
    }

    public void createProduct(String productld, String name, Integer count) {
        productMap.putIfAbsent(productld, new Product(productld, name, count));
    }

    public int getinventory(String productid) {
        Product product = productMap.get(productid);
        return product != null ? product.getAvailableCount() : 0;
    }

    public void createInventory(String inventoryId, List<String> productIds) {
        Inventory inventory = new Inventory(inventoryId);
        for (String productId : productIds) {
            inventory.addProduct(productId);
        }
        inventoryMap.putIfAbsent(inventoryId, inventory);
    }

    public boolean blockinventory(String productld, int count, String orderld) {
        lock.lock();
        try {
            Product product = productMap.get(productld);
            if (product == null || !product.decrease(count)) {
                return false;
            }
            InventoryBlock block = new InventoryBlock(orderld, productld, count);
            blockMap.put(orderld, block);
            return true;
        } finally {
            lock.unlock();
        }
    }

    public boolean confirmOrder(String orderld) {
        lock.lock();
        try {
            InventoryBlock inventoryBlock = blockMap.get(orderld);
            if (inventoryBlock == null || inventoryBlock.isExpired()) {
                releaseInventory(orderld);
                return false;
            }
            blockMap.remove(orderld);
            confirmOrders.add(orderld);
            return true;
        } finally {
             lock.unlock();
        }
    }

    private void releaseInventory(String orderld) {
        lock.lock();
        try {
            InventoryBlock inventoryBlock = blockMap.remove(orderld);
            if (inventoryBlock != null && !confirmOrders.contains(orderld)) {
                Product product = productMap.get(inventoryBlock.getProductId());
                if (product != null) {
                    product.increase(inventoryBlock.getCount());
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // clean the expired ones
    // run a scheduler
    public void cleanExpiredBlocks() {
        for (String orderId : new ArrayList<>(blockMap.keySet())) {
            InventoryBlock block = blockMap.get(orderId);
            if (block != null && block.isExpired()) {
                releaseInventory(orderId);
            }
        }
    }

}
