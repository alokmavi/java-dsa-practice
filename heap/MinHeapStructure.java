package heap;
public class MinHeapStructure {

    private final int[] heapMemory;
    private int activeElementCount;
    private final int maximumCapacity;

    public MinHeapStructure(int maximumCapacity) {
        if (maximumCapacity <= 0) {
            throw new IllegalArgumentException("Heap capacity must be strictly positive.");
        }
        this.maximumCapacity = maximumCapacity;
        this.activeElementCount = 0;
        this.heapMemory = new int[maximumCapacity];
    }

    private int fetchParentIndex(int currentIndex) {
        return (currentIndex - 1) / 2;
    }

    private void swapElements(int indexAlpha, int indexBeta) {
        int temporaryStorage = heapMemory[indexAlpha];
        heapMemory[indexAlpha] = heapMemory[indexBeta];
        heapMemory[indexBeta] = temporaryStorage;
    }

    public void insertValue(int priorityValue) {
        if (activeElementCount >= maximumCapacity) {
            throw new IllegalStateException("Heap memory overflow. Cannot insert new value.");
        }

        // 1. Append the new value to the first available contiguous memory block
        heapMemory[activeElementCount] = priorityValue;
        
        // 2. Restore the systemic Min-Heap constraint
        bubbleUp(activeElementCount);
        
        activeElementCount++;
    }

    private void bubbleUp(int insertionIndex) {
        int currentIndex = insertionIndex;
        
        while (currentIndex > 0 && heapMemory[currentIndex] < heapMemory[fetchParentIndex(currentIndex)]) {
            int parentIndex = fetchParentIndex(currentIndex);
            swapElements(currentIndex, parentIndex);
            currentIndex = parentIndex;
        }
    }

    public void printHeapMemory() {
        System.out.print("Contiguous Heap Memory: [");
        for (int i = 0; i < activeElementCount; i++) {
            System.out.print(heapMemory[i] + (i < activeElementCount - 1 ? ", " : ""));
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        MinHeapStructure taskQueue = new MinHeapStructure(10);

        // Simulating incoming priority tasks
        taskQueue.insertValue(50);
        taskQueue.insertValue(30);
        taskQueue.insertValue(20);
        taskQueue.insertValue(15);
        taskQueue.insertValue(10);

        /*
         * Trace the bubble-up for '10':
         * Inserted at index 4. Parent is index 1 (value 20). 10 < 20, swap.
         * Now at index 1. Parent is index 0 (value 15). 10 < 15, swap.
         * Now at index 0. Loop terminates.
         */

        taskQueue.printHeapMemory(); 
        // Expected Array: [10, 15, 30, 50, 20]
        
        try {
            MinHeapStructure overflowTest = new MinHeapStructure(1);
            overflowTest.insertValue(1);
            overflowTest.insertValue(2); // Should trigger IllegalStateException
        } catch (IllegalStateException e) {
            System.out.println("Defensive bounds check verified: " + e.getMessage());
        }
    }
}