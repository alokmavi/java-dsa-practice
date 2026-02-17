package queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Arrays;

public class SlidingWindowMax {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int ri = 0; // Result index
        
        // Deque stores INDICES, not values. 
        // Why? Because we need indices to know if an element is out of the window.
        Deque<Integer> dq = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            // 1. Remove elements out of the current window from the FRONT
            // The window is [i-k+1, i]. If front index < i-k+1, it's too old.
            while (!dq.isEmpty() && dq.peekFirst() < i - k + 1) {
                dq.pollFirst();
            }
            
            // 2. Maintain Monotonic Decreasing Order from the BACK
            // If current number > last number in deque, the last number is useless.
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }
            
            // 3. Add current index
            dq.offerLast(i);
            
            // 4. Add to result (only once the first window is fully formed)
            if (i >= k - 1) {
                result[ri++] = nums[dq.peekFirst()];
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        
        // Expected: [3, 3, 5, 5, 6, 7]
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
    }
}