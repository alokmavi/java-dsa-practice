package backtracking;

import java.util.*;

public class CombinationSum {

    static void findCombinations(
            int index,
            int[] arr,
            int target,
            List<Integer> current
    ) {
        if (target == 0) {
            System.out.println(current);
            return;
        }

        if (index == arr.length || target < 0) {
            return;
        }

        // Pick current element
        current.add(arr[index]);
        findCombinations(index, arr, target - arr[index], current);

        // Backtrack
        current.remove(current.size() - 1);

        // Skip current element
        findCombinations(index + 1, arr, target, current);
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 7};
        int target = 7;
        findCombinations(0, arr, target, new ArrayList<>());
    }
}