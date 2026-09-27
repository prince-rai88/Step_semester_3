package session2.assignment_problems.p5_word_frequency;

import java.util.*;

public class WordFrequencyFilter {
    public void printFilteredWordFrequency(String feedback) {
        if (feedback == null) return;
        
        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};
        Set<String> stopSet = new HashSet<>(Arrays.asList(stopWords));
        
        String cleaned = feedback.toLowerCase().replace(".", "").replace(",", "");
        String[] words = cleaned.split("\\s+");
        
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty() && !stopSet.contains(word)) {
                freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
            }
        }
        
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freqMap.entrySet());
        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
