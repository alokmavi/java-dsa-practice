package queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class FirstNegative {
    public static void solve(int[] arr, int k) {
        Queue<Integer> q = new ArrayDeque<>();
        int n = arr.length;

        System.out.print("Result: ");
        
        for (int i = 0; i < n; i++) {
            // 1. Add current element index if it is negative
            if (arr[i] < 0) {
                q.offer(i);
            }

            // 2. Remove elements that slid out of the window
            // Window range is [i - k + 1 ... i]
            if (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }

            // 3. Print answer for current window
            // Only start printing when we have processed at least k elements
            if (i >= k - 1) {
                if (q.isEmpty()) {
                    System.out.print("0 "); // No negative number in window
                } else {
                    System.out.print(arr[q.peek()] + " ");
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {12, -1, -7, 8, -15, 30, 16, 28};
        int k = 3;
        solve(arr, k);
    }
}