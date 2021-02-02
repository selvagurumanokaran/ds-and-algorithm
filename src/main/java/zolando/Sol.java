package zolando;

import java.util.*;

public class Sol {
    public int solution3(String S) {

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            map.put(S.charAt(i), map.getOrDefault(S.charAt(i), 0) + 1);
        }


        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        int finalLength = list.get(0).getValue();
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getValue().compareTo(list.get(i - 1).getValue()) >= 0) {
                int value = list.get(i - 1).getValue() - 1;
                list.get(i).setValue(value);
                finalLength += value;
                if (value == 0) break;
            } else {
                finalLength += list.get(i).getValue();
            }
        }

        return S.length() - finalLength;
    }

    public int solution4(int[] A) {
        // write your code in Java SE 8
        Integer[] indexArr = new Integer[A.length - 2];
        for (int i = 1; i < A.length - 1; i++) {
            indexArr[i - 1] = i;
        }

        Arrays.sort(indexArr, Comparator.comparing(i -> A[i]));
        int minDistance = Integer.MAX_VALUE;
        for (int i = 1; i < indexArr.length; i++) {
            int distance = Math.abs(indexArr[i] - indexArr[i - 1]);
            if (distance > 1 && A[indexArr[i]] + A[indexArr[i - 1]] < minDistance) {
                minDistance = A[indexArr[i]] + A[indexArr[i - 1]];
            }
        }
        return minDistance;
    }

    public int solution5(String S) {
        // write your code in Java SE 8
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < S.length() - 1; i++) {
            int val = Integer.parseInt(S.substring(i, i + 2));
            max = Math.max(val, max);
        }

        return max;
    }
}
