package heap;

public class MinHeapExtraction {

    private final int[] heapMemory;
    private int activeElementCount;
    private final int maximumCapacity;

    public MinHeapExtraction(int maximumCapacity) {
        if (maximumCapacity <= 0) {
            throw new IllegalArgumentException("Heap capacity must be strictly positive.");
        }
        this.maximumCapacity = maximumCapacity;
        this.activeElementCount = 0;
        this.heapMemory = new int[maximumCapacity];
    }

    private void swapElements(int indexAlpha, int indexBeta) {
        int temporaryStorage = heapMemory[indexAlpha];
        heapMemory[indexAlpha] = heapMemory[indexBeta];
        heapMemory[indexBeta] = temporaryStorage;
    }

    public void insertValue(int priorityValue) {
        if (activeElementCount >= maximumCapacity) {
            throw new IllegalStateException("Heap memory overflow.");
        }
        heapMemory[activeElementCount] = priorityValue;
        bubbleUp(activeElementCount);
        activeElementCount++;
    }

    private void bubbleUp(int insertionIndex) {
        int currentIndex = insertionIndex;
        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            if (heapMemory[currentIndex] < heapMemory[parentIndex]) {
                swapElements(currentIndex, parentIndex);
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
    }

    public int extractMinimum() {
        if (activeElementCount == 0) {
            throw new IllegalStateException("Heap underflow. No elements to extract.");
        }

        int absoluteMinimum = heapMemory[0];

        // 1. Relocate the final leaf node to the structural root to fill the vacuum
        heapMemory[0] = heapMemory[activeElementCount - 1];
        activeElementCount--;

        // 2. Restore the systemic constraint downwards
        bubbleDown(0);

        return absoluteMinimum;
    }

    private void bubbleDown(int startIndex) {
        int currentIndex = startIndex;

        while (true) {
            int smallestIndex = currentIndex;
            int leftChildIndex = (2 * currentIndex) + 1;
            int rightChildIndex = (2 * currentIndex) + 2;

            // Evaluate left branch constraint
            if (leftChildIndex < activeElementCount && heapMemory[leftChildIndex] < heapMemory[smallestIndex]) {
                smallestIndex = leftChildIndex;
            }

            // Evaluate right branch constraint against the current known minimum
            if (rightChildIndex < activeElementCount && heapMemory[rightChildIndex] < heapMemory[smallestIndex]) {
                smallestIndex = rightChildIndex;
            }

            // If the structural constraint is fully satisfied, terminate the cascade
            if (smallestIndex == currentIndex) {
                break;
            }

            swapElements(currentIndex, smallestIndex);
            currentIndex = smallestIndex;
        }
    }

    public static void main(String[] args) {
        MinHeapExtraction taskQueue = new MinHeapExtraction(10);

        int[] incomingTasks = {50, 30, 20, 15, 10, 8, 16};
        for (int task : incomingTasks) {
            taskQueue.insertValue(task);
        }

        System.out.println("Processing tasks by priority (Min-Heap Extraction):");
        
        // Expected extraction order: 8, 10, 15, 16, 20, 30, 50
        while (taskQueue.activeElementCount > 0) {
            System.out.print(taskQueue.extractMinimum() + " ");
        }
        System.out.println();
        
        // Verifying defensive underflow check
        try {
            taskQueue.extractMinimum();
        } catch (IllegalStateException e) {
            System.out.println("Defensive bounds check verified: " + e.getMessage());
        }
    }
}
