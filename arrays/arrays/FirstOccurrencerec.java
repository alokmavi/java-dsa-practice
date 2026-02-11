package arrays;

public class FirstOccurrencerec {
    public static int findFirst(int[] arr, int index, int target) {
        if (index == arr.length) {
            return -1;
        }

        if (arr[index] == target) {
            return index;
        }

        return findFirst(arr, index + 1, target);
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 2, 6};
        System.out.println(findFirst(arr, 0, 2));
    }
}
