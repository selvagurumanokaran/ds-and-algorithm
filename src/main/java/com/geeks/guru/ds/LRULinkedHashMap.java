package com.geeks.guru.ds;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRULinkedHashMap extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    LRULinkedHashMap(int capacity) {
        super(capacity, 1, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > this.capacity;
    }

    /*public int get(Integer key) {
        return getOrDefault(key, -1);
    }*/

    public static void main(String[] args) {
        LRULinkedHashMap lru = new LRULinkedHashMap(2);
        lru.put(1, 1);
        lru.put(2, 1);
        lru.put(3, 1);
        lru.put(21, 0);
        System.out.println(lru.getOrDefault(2, -1));
        System.out.println(lru.get(3));
    }
}
