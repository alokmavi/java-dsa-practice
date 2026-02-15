package arrays;

public class SpaceComplexityDemo {

    // O(1) space
    static int sum(int a, int b) {
        return a + b;
    }

    // O(n) space (recursion stack)
    static int recursiveSum(int n) {
        if (n == 0) return 0;
        return n + recursiveSum(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(sum(2, 3));
        System.out.println(recursiveSum(5));
    }
}