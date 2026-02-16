package queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueBasics {
    public static void main(String[] args) {
        // We use ArrayDeque because it's array-based (fast/cache-friendly)
        // FIFO Behavior
        Queue<Integer> queue = new ArrayDeque<>();

        // 1. Enqueue (Add to rear)
        queue.offer(10);
        queue.offer(20);
        queue.offer(30);

        System.out.println("Current Queue: " + queue); // [10, 20, 30]

        // 2. Peek (Look at front)
        System.out.println("Front element: " + queue.peek()); // 10

        // 3. Dequeue (Remove from front)
        System.out.println("Removed: " + queue.poll()); // 10

        System.out.println("New Front: " + queue.peek()); // 20
        
        // Size check
        System.out.println("Is empty? " + queue.isEmpty());
    }
}