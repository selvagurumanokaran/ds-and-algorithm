package fivetron;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stock {
    public static List<Integer> getUnallottedUsers(List<List<Integer>> bids, int totalShares) {
        Collections.sort(bids, (bid1, bid2) -> {
            int diff = Integer.compare(bid1.get(2), bid2.get(2));
            if (diff != 0) return diff;
            return Integer.compare(bid2.get(3), bid1.get(3));
        });
        List<Integer> result = new ArrayList<>();
        print(bids);

        while (!bids.isEmpty() || totalShares > 0) {
            int currSharePrice = bids.get(bids.size() - 1).get(2);
            int count = 1, currTotalShares = bids.get(bids.size() - 1).get(1);
            System.out.println("Running for price " + currSharePrice);
            for (int i = bids.size() - 2; i >= 0 && currSharePrice == bids.get(i).get(2); i--, count++) {
                currTotalShares += bids.get(i).get(1);
            }
            System.out.println("Total count " + count);
            if (currTotalShares <= totalShares) {
                System.out.println("Allocating & remove all " + count);
                removeNumberOfElements(bids, count);
                totalShares -= currTotalShares;
                System.out.println("Allocating & remove all " + count);
            } else {
                if (count <= totalShares) {
                    System.out.println("Everybody gets at least one share " + count);
                    removeNumberOfElements(bids, count);
                    totalShares = 0;
                } else {
                    removeNumberOfElements(bids, count - totalShares);
                    totalShares = 0;
                }
            }
        }
        print(bids);
        for (List<Integer> list : bids) {
            result.add(list.get(0));
        }
        return result;
    }

    private static void removeNumberOfElements(List<List<Integer>> bids, int count) {
        for (int i = 1; i <= count; i++) {
            bids.remove(bids.size() - 1);
        }
    }

    private static void print(List<List<Integer>> bids) {
        for (List<Integer> list : bids) {
            System.out.println();
            for (Integer ele : list) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
    }
}
