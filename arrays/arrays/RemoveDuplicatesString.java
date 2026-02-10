package arrays;

import java.util.LinkedHashSet;

public class RemoveDuplicatesString {
    public static void main(String[] args) {
        String s = "programming";

        LinkedHashSet<Character> set = new LinkedHashSet<>();

        for (char ch : s.toCharArray()) {
            set.add(ch);
        }

        StringBuilder result = new StringBuilder();
        for (char ch : set) {
            result.append(ch);
        }

        System.out.println(result.toString());
    }
}
