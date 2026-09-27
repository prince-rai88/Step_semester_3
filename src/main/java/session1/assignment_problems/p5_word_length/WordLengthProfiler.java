package session1.assignment_problems.p5_word_length;

public class WordLengthProfiler {
    public void classifyWordLengths(String review) {
        if (review == null || review.isEmpty()) return;
        
        String[] words = review.split("\\s+");
        int shortWords = 0;
        int mediumWords = 0;
        int longWords = 0;
        
        for (String word : words) {
            int len = word.replaceAll("[^a-zA-Z]", "").length();
            if (len == 0) continue;
            if (len >= 1 && len <= 4) shortWords++;
            else if (len >= 5 && len <= 8) mediumWords++;
            else longWords++;
        }
        
        System.out.printf("Short: %d | Medium: %d | Long: %d%n", shortWords, mediumWords, longWords);
    }
}
