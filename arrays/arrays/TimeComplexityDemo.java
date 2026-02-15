package arrays;

public class TimeComplexityDemo {
    public static void main(String[] args) {
        int n = 5;

        // O(n)
        for (int i = 0; i < n; i++) {
            System.out.println("Single loop");
        }

        // O(n^2)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Nested loop");
            }
        }
    }
}