import java.util.*;
import java.util.stream.IntStream;

public class Google {
    /*
        Given a source image consisting of only black and white pixels, redraw that image using these provided functions:
        int readSource(int x1, int y1, int x2, int y2);
        This function will return 1 if all pixels in the source image within the rectangle with corners (x1, y1) and (x2, y2) are black.
        It will return -1 if all pixels are white. It will return 0 if some are black and some are white.
        void writeTarget(int x1, int y1, int x2, int y2);
        This function will draw a completely black rectangle with corners (x1, y1) and (x2, y2) on the target image.
    */

    public int solution1(int[] A) {
        // write your code in Java SE 8
        Map<Integer, Boolean> map = new HashMap<>();
        int[] result = new int[1];
        for (int i = 0; i < A.length; i++) {
            int currEle = A[i];
            Optional<Integer> optionalMax = map.keySet().stream().filter(ele -> currEle + ele == 0).findFirst();
            optionalMax.ifPresent(ele -> result[0] = Math.max(result[0], Math.abs(ele)));
            map.put(currEle, true);
        }
        return result[0];
    }

    /******************
     *
     * Zalando
     *
     ******************/
    //Series of Light bulbs - any bulb at position k, will turn on only if 1 to k are on, will be on only if the first one
    // int[] arr = {2,1,3,5,4}; should return 3
    public int solution2(int[] A) {

        if (A.length <= 1) return 1;
        class Result {
            int max;
            int expectedSum;
            int currSum;
            int count;
        }

        Result result = new Result();
        result.max = A[0];
        result.expectedSum = (A[0] * (A[0] + 1)) / 2;
        result.currSum = A[0];
        IntStream.range(1, A.length)
                .map(i -> A[i])
                .forEach(ele -> {
                    if (ele > result.max) {
                        result.max = ele;
                        result.expectedSum = (ele * (ele + 1)) / 2;
                    }
                    //result.max = Math.max(result.max, ele);
                    result.currSum += ele;
                    if (result.expectedSum == result.currSum) result.count++;
                });
        return result.count;
        // write your code in Java SE 8
        /*boolean[] aux = new boolean[A.length];
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            boolean found = true;
            aux[A[i] - 1] = true;
            for (int k = A[i] - 1; k >= 0; k--) {
                found &= aux[k];
            }
            if (found) {
                count++;
            }
        }

        return count;*/
    }

    // Find num of transactions for a number to become 0. if the num is even you can divide by 2, if odd, minus 1
    public int solution3(String S) {
        // write your code in Java SE 8
        int V = Integer.parseInt(S, 2);
        int count = 0;
        while (V != 0) {
            if (V % 2 == 0) V /= 2;
            else V -= 1;
            ++count;
        }
        return count;
    }

    //Find number of pairs (i,j) in A where A[i] = A[j] and i != j and i < j
    public int solution(int[] A) {
        // write your code in Java SE 8
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (map.containsKey(A[i])) {
                count += map.get(A[i]);
                map.put(A[i], map.get(A[i]) + 1);
            } else {
                map.put(A[i], 1);
            }
        }
        return Math.min(1000000000, count);
    }

    /******************
     *
     * Zalando
     *
     ******************/
    public static void main(String[] args) {
        Google google = new Google();
        //int[] arr = {-1, 1, 2, 1, 2, 1};
        int[] arr = {2, 1, 3, 5, 4};
        //int[] arr = {2,3,4,1,5};
        //int[] arr = {1, 3, 4, 2, 5};
        //int[] arr = {3, 2, -2, 5, 3};
        //int[] arr = {3};
        //int[] arr = {3, 5, 6, 1, 7, };
        //int[] arr = {3, 5, 6, 3, 3, 5};
        System.out.println(google.solution2(arr));
        /*int result = google.findEqualSum("0001");
        System.out.println(result);*/

        // System.out.println(Integer.MAX_VALUE > 1000000000);
    }
}
