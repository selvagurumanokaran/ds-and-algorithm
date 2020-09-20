package com.geeks.guru.ds.array;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ArrayProblems {

    public static void main(String[] args) {
        int arr[] = {-1, 1, 3, 1, 5, 7, -1, 5, 3};
        //printAllPair(arr, 0);
        printMaxNumInWindow(arr, 3);
    }

    public static void printAllPair(int[] arr, int sum) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int lookupVal = sum - arr[i];
            Integer count = map.get(lookupVal);
            if (count != null) {
                for (int j = 0; j < count; j++)
                    System.out.print("(" + arr[i] + "," + lookupVal + ") ");
            }
            Integer val = map.get(arr[i]);
            val = val == null ? 0 : val;
            map.put(arr[i], val + 1);
        }
    }

    public static void printMaxNumInWindow(int[] arr, int k) {
        Deque<Integer> queue = new LinkedList<>();
        int i = 0;
        for (; i < k; i++) {
            while (!queue.isEmpty() && arr[i] > arr[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        for (; i < arr.length; i++) {
            System.out.print(arr[queue.peekFirst()] + " ");
            while (!queue.isEmpty() && queue.peekFirst() <= i - k) {
                queue.pollFirst();
            }
            while (!queue.isEmpty() && arr[i] > arr[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        System.out.print(arr[queue.peekFirst()]);
        System.out.println();
    }

}
