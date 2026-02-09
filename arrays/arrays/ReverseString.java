package arrays;

public class ReverseString {
    public static void main(String[] args) {
        String s = "hello";

        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;

        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        String reversed = new String(chars);
        System.out.println(reversed);
    }
}
