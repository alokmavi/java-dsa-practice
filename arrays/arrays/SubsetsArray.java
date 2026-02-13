package arrays;

public class SubsetsArray {
    public static void printSubsets(int[] arr, int index, String curr) {
        if (index == arr.length) {
            System.out.println(curr);
            return;
        }

        printSubsets(arr, index + 1, curr + arr[index] + " ");
        printSubsets(arr, index + 1, curr);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        printSubsets(arr, 0, "");
    }
}