package arrays;

import java.util.HashSet;

public class ContainsDuplicate {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 1};

        HashSet<Integer> set = new HashSet<>();
        boolean hasDuplicate = false;

        for (int num : arr) {
            if (set.contains(num)) {
                hasDuplicate = true;
                break;
            }
            set.add(num);
        }

        System.out.println("Contains duplicate: " + hasDuplicate);
    }
}
