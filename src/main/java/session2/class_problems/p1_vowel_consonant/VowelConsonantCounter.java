package session2.class_problems.p1_vowel_consonant;

public class VowelConsonantCounter {
    public void countVowelsAndConsonants(String text) {
        if (text == null) return;
        int vowels = 0;
        int consonants = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = Character.toLowerCase(text.charAt(i));
            if (c == ' ') continue;
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowels++;
            } else if (c >= 'a' && c <= 'z') {
                consonants++;
            }
        }
        System.out.printf("Vowels: %d | Consonants: %d%n", vowels, consonants);
    }
}
