package deliveryhero;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {
    public int solution1(String S) {
        // write your code in Java 8
        Map<Character, Integer> map = S.chars().mapToObj(c -> (char) c).collect(Collectors.toMap(Function.identity(), c -> 1, Integer::sum));
        if (map.containsKey('D') && (map.containsKey('B') || map.containsKey('C'))) {
            map.remove('D');
        }
        if (map.containsKey('C') && (map.containsKey('B') || map.containsKey('A'))) {
            map.remove('C');
        }
        if (map.containsKey('B') && map.containsKey('A')) {
            map.remove('B');
        }

        return map.values().stream().mapToInt(i -> i).sum();

    }

    public int[] solution2(int X, int Y) {
        // write your code in Java SE 8
        int result[] = new int[2];
        if (2 * X <= Y) {

        } else {
            return result;
        }
        return result;
    }


}
