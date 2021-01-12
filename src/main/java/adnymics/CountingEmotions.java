package adnymics;

import java.util.Objects;

public class CountingEmotions {
    public int count(String emotionString) {
        Objects.requireNonNull(emotionString, "One should have emotions :)");
        int emotions = 0;
        for (int i = 0; i < emotionString.length(); ) {
            if (emotionString.charAt(i) == ':') {
                i++;
                if (i < emotionString.length()) {
                    if (emotionString.charAt(i) == ')' || emotionString.charAt(i) == '(') {
                        emotions++;
                        i++;
                    }
                }
            } else {
                i++;
            }
        }
        return emotions;
    }

    public static void main(String[] args) {
        CountingEmotions countingEmotions = new CountingEmotions();
        System.out.println(countingEmotions.count(":):):):):)"));
        System.out.println(countingEmotions.count(":(:(:(:(:("));
        System.out.println(countingEmotions.count(":(:(:):):("));
        System.out.println(countingEmotions.count("::(:((:):)::((((((("));
        System.out.println(countingEmotions.count(":())):::(:(:):)(:(:)"));
        System.out.println(countingEmotions.count("(((((((((((((((()))))))))))))))::::::::::::::"));
        System.out.println(countingEmotions.count(""));
    }
}
