package arrays;

public class CountSubseqSum {
    public static int countSubseq(int[] arr, int index, int sum, int target) {
        if (index == arr.length) {
            return sum == target ? 1 : 0;
        }

        int include = countSubseq(arr, index + 1, sum + arr[index], target);
        int exclude = countSubseq(arr, index + 1, sum, target);

        return include + exclude;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        int target = 2;
        System.out.println(countSubseq(arr, 0, 0, target));
    }
}