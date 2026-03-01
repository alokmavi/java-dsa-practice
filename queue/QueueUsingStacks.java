package queue;
import java.util.ArrayDeque;
import java.util.Deque;

public class QueueUsingStacks {
    // We use Deque as the modern, safer implementation for Stacks in Java
    private Deque<Integer> inputStack;
    private Deque<Integer> outputStack;

    public QueueUsingStacks() {
        this.inputStack = new ArrayDeque<>();
        this.outputStack = new ArrayDeque<>();
    }

    public void enqueue(int element) {
        inputStack.push(element);
    }

    public int dequeue() {
        shiftStacksIfNeeded();
        if (outputStack.isEmpty()) {
            throw new IllegalStateException("Queue is empty. Cannot dequeue.");
        }
        return outputStack.pop();
    }

    public int peekFront() {
        shiftStacksIfNeeded();
        if (outputStack.isEmpty()) {
            throw new IllegalStateException("Queue is empty. Cannot peek.");
        }
        return outputStack.peek();
    }

    public boolean isEmpty() {
        return inputStack.isEmpty() && outputStack.isEmpty();
    }

    // Pours elements from input to output only when output is empty
    // This achieves Amortized O(1) time complexity for dequeue/peek
    private void shiftStacksIfNeeded() {
        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
    }

    public static void main(String[] args) {
        QueueUsingStacks customQueue = new QueueUsingStacks();
        customQueue.enqueue(1);
        customQueue.enqueue(2);
        customQueue.enqueue(3);

        System.out.println("Front element: " + customQueue.peekFront()); // Expected 1
        System.out.println("Dequeued: " + customQueue.dequeue());        // Expected 1
        
        customQueue.enqueue(4);
        System.out.println("Dequeued: " + customQueue.dequeue());        // Expected 2
    }
}