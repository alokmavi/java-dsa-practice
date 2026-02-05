package arrays;

public class BinarySearchRecursive {

    static int binarySearch(int[] arr, int low, int high, int target) {
        if (low > high) return -1;

        int mid = low + (high - low) / 2;

        if (arr[mid] == target) return mid;
        else if (arr[mid] < target)
            return binarySearch(arr, mid + 1, high, target);
        else
            return binarySearch(arr, low, mid - 1, target);
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10};
        int target = 6;

        System.out.println(
            "Found at index: " +
            binarySearch(arr, 0, arr.length - 1, target)
        );
    }
}
