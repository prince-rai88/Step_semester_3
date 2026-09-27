package session1.assignment_problems.p3_signal_streak;

public class StreakAnalyzer {
    public void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) return;
        char longestChar = signalLog.charAt(0);
        int longestLen = 1;
        
        char currentChar = signalLog.charAt(0);
        int currentLen = 1;
        
        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == currentChar) {
                currentLen++;
            } else {
                if (currentLen > longestLen) {
                    longestLen = currentLen;
                    longestChar = currentChar;
                }
                currentChar = signalLog.charAt(i);
                currentLen = 1;
            }
        }
        
        if (currentLen > longestLen) {
            longestLen = currentLen;
            longestChar = currentChar;
        }
        
        System.out.printf("Longest Streak: '%c' repeated %d times%n", longestChar, longestLen);
    }
}
