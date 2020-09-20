import java.util.*;
import java.util.stream.Collectors;

public class RoyalRumble {

    private Map<Character, Integer> romanNumeralMap;

    public RoyalRumble() {
        romanNumeralMap = new HashMap<>();
        romanNumeralMap.put('I', 1);
        romanNumeralMap.put('V', 5);
        romanNumeralMap.put('X', 10);
        romanNumeralMap.put('L', 50);
    }

    public List<String> getSortedList(List<String> names) {
        TreeMap<String, List<String>> nameMap = names.stream()
                .map(name -> {
                    if (name.split(" ").length != 2) {
                        throw new IllegalArgumentException("Invalid firstname");
                    }
                    return name;
                })
                .collect(Collectors.groupingBy(name -> name.split(" ")[0], TreeMap::new, Collectors.toList()));

        List<String> sortedNames = nameMap.values().stream()
                .flatMap(namesList -> {
                    Collections.sort(namesList, Comparator.comparingInt(name -> getNumeralValue(name.split(" ")[1])));
                    return namesList.stream();
                })
                .collect(Collectors.toList());
        return sortedNames;
    }

    private int getNumeralValue(String romanString) {
        int result = 0;
        for (int i = 0; i < romanString.length(); i++) {

            int v1 = Optional.ofNullable(romanNumeralMap.get(romanString.charAt(i))).orElseThrow(() -> new IllegalArgumentException("Roman Letter should be between 1 and 50"));

            if (i + 1 < romanString.length()) {
                int v2 = Optional.ofNullable(romanNumeralMap.get(romanString.charAt(i + 1))).orElseThrow(() -> new IllegalArgumentException("Roman Letter should be between 1 and 50"));
                if (v1 >= v2) {
                    result += v1;
                } else {
                    result += v2 - v1;
                    i++;
                }
            } else {
                result += v1;
            }
        }

        return result;
    }

}
