package foundations;

import java.util.Stack;

public class RecursionStackDemo {

    // Normal recursion (system uses hidden stack)
    static void recursiveCountdown(int n) {
        if (n == 0) return;

        System.out.println("Entering: " + n);
        recursiveCountdown(n - 1);
        System.out.println("Exiting: " + n);
    }

    // Manual simulation using explicit stack
    static void manualStackCountdown(int n) {
        Stack<Integer> stack = new Stack<>();

        // pushing phase (like recursive calls going deeper)
        while (n > 0) {
            System.out.println("Entering: " + n);
            stack.push(n);
            n--;
        }

        // popping phase (like returning from recursion)
        while (!stack.isEmpty()) {
            System.out.println("Exiting: " + stack.pop());
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Using Recursion ===");
        recursiveCountdown(3);

        System.out.println("\n=== Using Manual Stack ===");
        manualStackCountdown(3);
    }
}