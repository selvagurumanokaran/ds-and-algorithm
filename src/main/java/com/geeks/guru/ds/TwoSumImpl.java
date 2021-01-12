package com.geeks.guru.ds;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

interface TwoSum {

    /* Store in a data structure*/
    public void store(int input);


    /**
     * Returns true if there is any pair of numbers that have sum equal to @param test, and false otherwise.
     */
    public boolean test(int test);
}

public class TwoSumImpl implements TwoSum {

    Map<Integer, Integer> map;

    TwoSumImpl() {
        map = new HashMap<>();
    }

    public void store(int input) {
        map.merge(input, 1, (oldVal, currVal) -> oldVal + currVal);
    }

    public boolean test(int test) {
        //if (map.size() == 0) return false;
        Set<Integer> keys = map.keySet();
        for (Integer key : keys) {
            Integer val = map.get(test - key);
            if (val != null) {
                if (test - key == key) {
                    if (val >= 2)
                        return true;
                    else continue;
                }
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {


    }
}
