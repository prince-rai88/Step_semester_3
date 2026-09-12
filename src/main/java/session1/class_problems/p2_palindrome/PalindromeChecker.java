package session1.class_problems.p2_palindrome;

public class PalindromeChecker {
    public boolean isPalindromeIterative(String text) {
        if (text == null) return false;
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
    
    public boolean isPalindromeRecursive(String text) {
        if (text == null) return false;
        if (text.length() <= 1) return true;
        if (text.charAt(0) != text.charAt(text.length() - 1)) return false;
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }
    
    public boolean isPalindromeArrayReversal(String text) {
        if (text == null) return false;
        char[] chars = text.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return text.equals(new String(chars));
    }
    
    public void printResults(String text) {
        String iter = isPalindromeIterative(text) ? "Palindrome" : "Not Palindrome";
        String rec = isPalindromeRecursive(text) ? "Palindrome" : "Not Palindrome";
        String arr = isPalindromeArrayReversal(text) ? "Palindrome" : "Not Palindrome";
        System.out.printf("Iterative: %s | Recursive: %s | Array Reversal: %s%n", iter, rec, arr);
    }
}
