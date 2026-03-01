package queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class StackUsingQueue {
    private Queue<Integer> primaryQueue;

    public StackUsingQueue() {
        this.primaryQueue = new ArrayDeque<>();
    }

    public void push(int element) {
        primaryQueue.offer(element);
        int elementsToRotate = primaryQueue.size() - 1;
        
        // Rotate the previous elements behind the newly added element
        // This forces the newest element to the front of the queue
        for (int i = 0; i < elementsToRotate; i++) {
            primaryQueue.offer(primaryQueue.poll());
        }
    }

    public int pop() {
        if (primaryQueue.isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot pop.");
        }
        return primaryQueue.poll();
    }

    public int top() {
        if (primaryQueue.isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot peek.");
        }
        return primaryQueue.peek();
    }

    public boolean isEmpty() {
        return primaryQueue.isEmpty();
    }

    public static void main(String[] args) {
        StackUsingQueue customStack = new StackUsingQueue();
        customStack.push(10);
        customStack.push(20);
        customStack.push(30);

        System.out.println("Top element: " + customStack.top()); // Expected 30
        System.out.println("Popped: " + customStack.pop());      // Expected 30
        System.out.println("Top element: " + customStack.top()); // Expected 20
    }
}