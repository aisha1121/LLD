package org.example;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private final int capacity;
    private final Node<K, V> dummyHead;
    private final Node<K, V> dummyTail;
    private final Map<K, Node<K, V>> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        dummyHead = new Node<>(null, null);
        dummyTail = new Node<>(null, null);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public V get(K key) {
        Node<K, V> node = cache.get(key);
        if (node == null) return null;
        moveToHead(node);
        return node.value;
    }

    public void put(K key, V value) {
        Node<K, V> node = cache.get(key);
        if (node != null) {
            node.value = value;
            moveToHead(node);
        } else {
            Node<K, V> newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            if (cache.size() > capacity) {
                Node<K, V> removedNode = removeTail();
                cache.remove(removedNode.key);
            }
        }
    }

    private Node<K,V> removeTail() {
        Node<K, V> removedNode = dummyTail.prev;
        removedNode.prev.next = dummyTail;
        dummyTail.prev = removedNode.prev;
        return removedNode;
    }

    private void addToHead(Node<K,V> newNode) {
        newNode.next = dummyHead.next;
        newNode.prev = dummyHead;
        dummyHead.next = newNode;
        newNode.next.prev = newNode;
    }

    private void moveToHead(Node<K,V> node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(Node<K,V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
