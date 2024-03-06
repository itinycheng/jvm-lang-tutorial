package com.tiny.lang.java.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * lru.
 */
public class LruCache<K extends Comparable<K>, V> {

    Node<V> head;
    Node<V> tail;
    Map<K, Node<V>> cache;
    int capacity;

    public LruCache(int capacity) {
        this.cache = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public void put(K k, V v) {
        Node<V> existedNode = cache.get(k);
        if (existedNode != null) {
            removeNode(k, existedNode);
        }

        if (cache.size() >= capacity) {
            // remove tail node
            throw new RuntimeException("overflow");
        }

        putToHead(k, v);
        this.capacity++;
    }

    public V get(K k) {
        Node<V> node = cache.get(k);
        if (node == null) {
            return null;
        }

        V v = node.value;
        put(k, v);
        return v;
    }

    private void removeNode(K k, Node<V> node) {
        Node<V> prev = node.prev;
        Node<V> next = node.next;
        prev.next = next;
        next.prev = prev;

        cache.remove(k);
        this.capacity--;
    }

    private void putToHead(K k, V v) {
        Node<V> newHead = new Node<>();
        newHead.value = v;
        newHead.prev = null;
        newHead.next = this.head;

        this.head.prev = newHead;
        this.head = newHead;
        this.cache.put(k, head);
    }


    static class Node<T> {

        T value;

        Node<T> prev;

        Node<T> next;

    }

}
