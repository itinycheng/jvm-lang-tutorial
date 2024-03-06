package com.tiny.lang.java.interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * lfu.
 */
public class LfuCache<K extends Comparable<K>, V> {

    Map<K, Node<K, V>> dataCache;

    Node<K, V>[] freqCache;

    int capacity;

    public LfuCache(int capacity) {
        this.capacity = capacity;
        this.dataCache = new HashMap<>(capacity);
        this.freqCache = new Node[capacity];
    }

    public void put(K k, V v) {
        Node<K, V> node = dataCache.get(k);
        if (node != null) {
            node.v = v;
            return;
        }

        if (dataCache.size() >= capacity) {
            removeLowFreqNode(1);
        }

        node = newNode(k, v);
        dataCache.put(k, node);
        freqCache[dataCache.size()] = node;
        this.capacity++;
    }


    public V get(K k) {
        Node<K, V> node = dataCache.get(k);
        if (node == null) {
            return null;
        }

        node.freq++;
        return node.v;
    }

    private void removeLowFreqNode(int count) {
        Arrays.sort(freqCache);
        int maxIdx = dataCache.size() - 1;
        for (int i = 0; i < count; i++) {
            int idx = maxIdx - i;
            if (idx > 0) {
                Node<K, V> rmNode = freqCache[idx];
                dataCache.remove(rmNode.k);
                freqCache[idx] = null;
            }
        }
    }

    private Node<K, V> newNode(K k, V v) {
        Node<K, V> kvNode = new Node<>();
        kvNode.k = k;
        kvNode.v = v;
        kvNode.freq = 1L;
        return kvNode;
    }

    static class Node<K, V> implements Comparable<Node<K, V>> {

        K k;

        V v;

        Long freq;

        @Override
        public int compareTo(Node<K, V> o) {
            return this.freq.compareTo(o.freq);
        }
    }
}
