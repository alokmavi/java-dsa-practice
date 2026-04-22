package advanced_trees;

public class SegmentTree {

    private final int[] memoryTree;
    private final int[] rawData;
    private final int datasetSize;

    public SegmentTree(int[] inputData) {
        if (inputData == null || inputData.length == 0) {
            throw new IllegalArgumentException("System requires a valid, non-empty dataset.");
        }
        
        this.rawData = inputData;
        this.datasetSize = inputData.length;
        
        // Structural constraint: A flat-array segment tree requires exactly 4 * N memory slots
        this.memoryTree = new int[4 * datasetSize];
        
        // Initialize the hierarchical pre-computations
        buildTree(0, 0, datasetSize - 1);
    }

    private void buildTree(int treeIndex, int leftBoundary, int rightBoundary) {
        // Base Case: Leaf node represents a single, atomic element from the raw data
        if (leftBoundary == rightBoundary) {
            memoryTree[treeIndex] = rawData[leftBoundary];
            return;
        }

        int midPoint = leftBoundary + (rightBoundary - leftBoundary) / 2;
        int leftChildIndex = 2 * treeIndex + 1;
        int rightChildIndex = 2 * treeIndex + 2;

        // Recursively build the structural children
        buildTree(leftChildIndex, leftBoundary, midPoint);
        buildTree(rightChildIndex, midPoint + 1, rightBoundary);

        // Core State Transition: The parent's state is the exact sum of its divided components
        memoryTree[treeIndex] = memoryTree[leftChildIndex] + memoryTree[rightChildIndex];
    }

    public void updateRecord(int targetIndex, int newValue) {
        if (targetIndex < 0 || targetIndex >= datasetSize) {
            throw new IndexOutOfBoundsException("Target index is outside system boundaries.");
        }
        executePointUpdate(0, 0, datasetSize - 1, targetIndex, newValue);
    }

    private void executePointUpdate(int treeIndex, int leftBoundary, int rightBoundary, int targetIndex, int newValue) {
        if (leftBoundary == rightBoundary) {
            // State mutation: Update the leaf node and the underlying raw data
            memoryTree[treeIndex] = newValue;
            rawData[targetIndex] = newValue;
            return;
        }

        int midPoint = leftBoundary + (rightBoundary - leftBoundary) / 2;
        int leftChildIndex = 2 * treeIndex + 1;
        int rightChildIndex = 2 * treeIndex + 2;

        // Binary routing: Only traverse the specific sub-tree containing the target index
        if (targetIndex <= midPoint) {
            executePointUpdate(leftChildIndex, leftBoundary, midPoint, targetIndex, newValue);
        } else {
            executePointUpdate(rightChildIndex, midPoint + 1, rightBoundary, targetIndex, newValue);
        }

        // Post-mutation recalculation: Propagate the new sum back up the call stack
        memoryTree[treeIndex] = memoryTree[leftChildIndex] + memoryTree[rightChildIndex];
    }

    public int queryRangeSum(int queryLeft, int queryRight) {
        if (queryLeft < 0 || queryRight >= datasetSize || queryLeft > queryRight) {
            throw new IllegalArgumentException("Invalid query boundary parameters.");
        }
        return executeRangeQuery(0, 0, datasetSize - 1, queryLeft, queryRight);
    }

    private int executeRangeQuery(int treeIndex, int leftBoundary, int rightBoundary, int queryLeft, int queryRight) {
        // State 1: Total Overlap. The current node is perfectly enclosed by the query boundaries.
        if (queryLeft <= leftBoundary && queryRight >= rightBoundary) {
            return memoryTree[treeIndex];
        }

        // State 2: No Overlap. The current node is entirely outside the query boundaries.
        if (queryLeft > rightBoundary || queryRight < leftBoundary) {
            // Return the mathematical identity for summation (0) to neutralize the branch
            return 0; 
        }

        // State 3: Partial Overlap. The query fractures the current boundaries. Diverge and conquer.
        int midPoint = leftBoundary + (rightBoundary - leftBoundary) / 2;
        int leftChildIndex = 2 * treeIndex + 1;
        int rightChildIndex = 2 * treeIndex + 2;

        int leftSubTreeResult = executeRangeQuery(leftChildIndex, leftBoundary, midPoint, queryLeft, queryRight);
        int rightSubTreeResult = executeRangeQuery(rightChildIndex, midPoint + 1, rightBoundary, queryLeft, queryRight);

        return leftSubTreeResult + rightSubTreeResult;
    }

    public static void main(String[] args) {
        int[] financialLedger = {1, 3, 5, 7, 9, 11};
        SegmentTree analyticalEngine = new SegmentTree(financialLedger);

        System.out.println("Sum of indices [1 to 3]: " + analyticalEngine.queryRangeSum(1, 3)); 
        // Expected: 15 (3 + 5 + 7)

        System.out.println("Executing system correction: Index 2 -> Update to 10");
        analyticalEngine.updateRecord(2, 10); 
        // Array transitions from [1, 3, 5, 7, 9, 11] to [1, 3, 10, 7, 9, 11]

        System.out.println("Re-querying sum of indices [1 to 3]: " + analyticalEngine.queryRangeSum(1, 3)); 
        // Expected: 20 (3 + 10 + 7)
    }
}