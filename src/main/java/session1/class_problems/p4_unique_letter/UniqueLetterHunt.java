package session1.class_problems.p4_unique_letter;

import java.util.LinkedHashMap;
import java.util.Map;

public class UniqueLetterHunt {
    public void findFirstNonRepeatingChar(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println("No Non-Repeating Character Found");
            return;
        }
        
        Map<Character, Integer> counts = new LinkedHashMap<>();
        for (char c : text.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 1) {
                System.out.println("First Non-Repeating Character: '" + entry.getKey() + "'");
                return;
            }
        }
        System.out.println("No Non-Repeating Character Found");
    }
}
