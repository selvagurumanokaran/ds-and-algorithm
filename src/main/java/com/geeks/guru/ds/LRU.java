package com.geeks.guru.ds;

import java.util.HashMap;
import java.util.Map;

class DoublyLinkedList {
    int data;
    DoublyLinkedList next;
    DoublyLinkedList prev;
}

public class LRU {
    int c;
    DoublyLinkedList head;
    DoublyLinkedList tail;
    Map<Integer, DoublyLinkedList> map;

    LRU(int capacity) {
        map = new HashMap<>();
        c = capacity;
    }

    int get(int x) {
        if (map.containsKey(x)) {
            DoublyLinkedList curr = map.get(x);
            remove(curr);
            addFirst(curr);
            return x;
        }
        return -1;
    }

    void put(int x) {
        if (map.containsKey(x)) {
            DoublyLinkedList curr = map.get(x);
            remove(curr);
            addFirst(curr);
        } else {
            DoublyLinkedList node = new DoublyLinkedList();
            if (map.size() == c) {
                remove(tail);
                map.remove(tail.data);
                addFirst(node);
            } else {
                node.data = x;
                map.put(x, node);
                addFirst(node);
            }

        }
    }

    void addFirst(DoublyLinkedList node) {
        node.prev = null;
        node.next = head;
        if (head != null)
            head.prev = node;
        head = node;
        if (tail == null)
            tail = node;
    }

    void remove(DoublyLinkedList node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
        node.next = null;
        node.prev = null;
    }

    public static void main(String[] args) {
        LRU lru = new LRU(3);
        lru.put(1);
        lru.put(2);
        lru.put(3);
        lru.put(4);
        lru.put(5);
    }

}
