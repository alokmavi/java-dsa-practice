package backtracking;
import java.util.*;

public class Subsets {

    static void generateSubsets(int index, int[] arr, List<Integer> current) {
        // Base case
        if (index == arr.length) {
            System.out.println(current);
            return;
        }

        // Include current element
        current.add(arr[index]);
        generateSubsets(index + 1, arr, current);

        // Backtrack (remove last element)
        current.remove(current.size() - 1);

        // Exclude current element
        generateSubsets(index + 1, arr, current);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        generateSubsets(0, arr, new ArrayList<>());
    }
}