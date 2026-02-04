package arrays;

public class FrequencyCount {
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 2, 5, 2, 3};
        int target = 2;
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                count++;
            }
        }

        System.out.println("Frequency of " + target + " = " + count);
    }
}
