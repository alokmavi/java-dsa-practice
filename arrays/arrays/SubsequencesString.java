package arrays;

public class SubsequencesString {
    public static void printSubseq(String s, int index, String curr) {
        if (index == s.length()) {
            System.out.println(curr);
            return;
        }

        // include current character
        printSubseq(s, index + 1, curr + s.charAt(index));

        // exclude current character
        printSubseq(s, index + 1, curr);
    }

    public static void main(String[] args) {
        printSubseq("abc", 0, "");
    }
}