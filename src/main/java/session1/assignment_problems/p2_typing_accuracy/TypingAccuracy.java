package session1.assignment_problems.p2_typing_accuracy;

public class TypingAccuracy {
    public void checkTypingAccuracy(String original, String typed) {
        if (original == null || typed == null || original.length() != typed.length()) return;
        int len = original.length();
        int matched = 0;
        int firstMismatch = -1;
        for (int i = 0; i < len; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i + 1;
            }
        }
        double accuracy = (matched / (double) len) * 100;
        if (firstMismatch == -1) {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | No Mismatches%n", matched, len, accuracy);
        } else {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | First Mismatch at position %d ('%c' vs '%c')%n", 
                matched, len, accuracy, firstMismatch, original.charAt(firstMismatch - 1), typed.charAt(firstMismatch - 1));
        }
    }
}
