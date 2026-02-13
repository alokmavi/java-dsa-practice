package arrays;

public class AllOccurrences {
    public static void printAll(int[] arr, int index, int target) {
        if (index == arr.length) {
            return;
        }

        if (arr[index] == target) {
            System.out.print(index + " ");
        }

        printAll(arr, index + 1, target);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 4, 2};
        printAll(arr, 0, 2);
    }
}