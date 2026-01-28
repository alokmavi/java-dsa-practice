package strings;

public class StringBasics {

    public static void main(String[] args) {
        String s = "Hello World";

        System.out.println("Length: " + s.length());
        System.out.println("Character at index 1: " + s.charAt(1));
        System.out.println("Substring: " + s.substring(0, 5));
        System.out.println("Uppercase: " + s.toUpperCase());
        System.out.println("Lowercase: " + s.toLowerCase());
    }
}
