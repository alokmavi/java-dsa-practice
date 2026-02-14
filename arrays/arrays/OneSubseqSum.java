package arrays;

import java.util.ArrayList;

public class OneSubseqSum {
    public static boolean printOne(int[] arr, int index, int sum, int target, ArrayList<Integer> list) {
        if (index == arr.length) {
            if (sum == target) {
                System.out.println(list);
                return true;
            }
            return false;
        }

        list.add(arr[index]);
        if (printOne(arr, index + 1, sum + arr[index], target, list)) {
            return true;
        }

        list.remove(list.size() - 1);
        return printOne(arr, index + 1, sum, target, list);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        printOne(arr, 0, 0, 2, new ArrayList<>());
    }
}