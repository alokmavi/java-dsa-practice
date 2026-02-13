package arrays;

public class LastOccurrencerec {
    public static int findLast(int[] arr, int index, int target) {
        if (index == arr.length) {
            return -1;
        }

        int restIndex = findLast(arr, index + 1, target);

        if (restIndex != -1) {
            return restIndex;
        }

        if (arr[index] == target) {
            return index;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 2, 6};
        System.out.println(findLast(arr, 0, 2));
    }
}