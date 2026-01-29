package strings;

public class CharFrequency {

    public static void frequency(String s) {
        s = s.toLowerCase();
        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                freq[ch - 'a']++;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                System.out.println((char) (i + 'a') + " : " + freq[i]);
            }
        }
    }

    public static void main(String[] args) {
        frequency("hello");
    }
}
