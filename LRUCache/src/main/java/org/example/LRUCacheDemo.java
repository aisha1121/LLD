package org.example;

public class LRUCacheDemo {
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "aisha");
        cache.put(2, "ankit");
        cache.put(3, "divya");

        System.out.println(cache.get(3));
        System.out.println(cache.get(2));

        cache.put(4, "aayusha");
        System.out.println(cache.get(1));
        System.out.println(cache.get(4));

        cache.put(4, "aisha");
        System.out.println(cache.get(4));

    }
}
