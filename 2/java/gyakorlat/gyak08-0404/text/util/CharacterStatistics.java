package text.util;

import java.util.HashMap;
import java.util.Map;

public class CharacterStatistics {
    private HashMap<Character, Integer> charToCount;

    public CharacterStatistics(String text) {
        charToCount = new HashMap<>();

        for (char c : text.toCharArray()) {
            charToCount.put(c, charToCount.getOrDefault(c, 0) + 1);
        }

        // getOrDefault() nélkül
        /*
        for (char c : text.toCharArray()) {
            if (charToCount.containsKey(c)) {
                charToCount.put(c, charToCount.get(c) + 1);
            } else {
                charToCount.put(c, 1);
            }
        }
        */
    }

    public int getCount(char c) {
        return charToCount.getOrDefault(c, 0);
    }

    @Override
    public String toString() {
        String result = "";

        for (Map.Entry<Character, Integer> entry : charToCount.entrySet()) {
            result += entry.getKey() + "(" + entry.getValue() + ") ";
        }

        return result;
    }
}