import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() < 10) return result;
        Map<String, Integer> map = new HashMap<>();
        map.put(s.substring(0, 10), 1);
        for (int i = 1; i < s.length() - 10; i++) {
            String currString = s.substring(i, i + 10);
            map.put(currString, map.getOrDefault(currString, 0) + 1);
        }
        for (String string : map.keySet()) {
            if (map.get(string) > 1) {
                result.add(string);
            }
        }
        String[] answers = {"AAAAACCCCC", "CCCCCAAAAA", "AAAACCCCCA", "AAACCCCCAA", "AACCCCCAAA", "ACCCCCAAAA", "CCCCAAAAAC",
                "CCCAAAAACC", "CCAAAAACCC", "CAAAAACCCC", "AAAACCCCCC", "AAACCCCCCA", "AACCCCCCAA", "ACCCCCCAAA", "CCCCCCAAAA",
                "CCCCAAAAAG", "CCCAAAAAGG", "CCAAAAAGGG", "CAAAAAGGGT", "AAAAAGGGTT", "AAAAGGGTTT"};
        for (String answer : answers) {
            System.out.println(answer + " --> " + map.containsKey(answer));
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTTAAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
    }
}