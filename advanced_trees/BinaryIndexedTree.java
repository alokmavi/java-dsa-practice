package advanced_trees;

public class BinaryIndexedTree {

    private final int[] fenwickMemory;
    private final int[] rawDataset;
    private final int structuralCapacity;

    public BinaryIndexedTree(int[] inputDataset) {
        if (inputDataset == null || inputDataset.length == 0) {
            throw new IllegalArgumentException("System requires a valid, non-empty dataset.");
        }

        this.structuralCapacity = inputDataset.length;
        this.rawDataset = new int[structuralCapacity];
        
        // Fenwick architecture strictly mandates 1-based indexing to prevent LSB infinite loops
        this.fenwickMemory = new int[structuralCapacity + 1];

        // Compile the initial state via sequential bitwise integration
        for (int i = 0; i < structuralCapacity; i++) {
            rawDataset[i] = inputDataset[i];
            executeInternalUpdate(i + 1, inputDataset[i]);
        }
    }

    public void updateRecord(int targetIndex, int deltaValue) {
        if (targetIndex < 0 || targetIndex >= structuralCapacity) {
            throw new IndexOutOfBoundsException("Target index is outside system boundaries.");
        }
        
        rawDataset[targetIndex] += deltaValue;
        executeInternalUpdate(targetIndex + 1, deltaValue);
    }

    private void executeInternalUpdate(int fenwickIndex, int deltaValue) {
        // Cascade the delta through all responsible hierarchical nodes
        while (fenwickIndex <= structuralCapacity) {
            fenwickMemory[fenwickIndex] += deltaValue;
            
            // Isolate the Least Significant Bit and jump forward to the parent node
            fenwickIndex += (fenwickIndex & -fenwickIndex);
        }
    }

    public int queryRangeSum(int leftBoundary, int rightBoundary) {
        if (leftBoundary < 0 || rightBoundary >= structuralCapacity || leftBoundary > rightBoundary) {
            throw new IllegalArgumentException("Invalid query boundary parameters.");
        }
        
        // Range sum [L, R] is mathematically equivalent to PrefixSum[R] - PrefixSum[L - 1]
        int rightPrefixSum = computePrefixSum(rightBoundary + 1);
        int leftPrefixSum = computePrefixSum(leftBoundary); // leftBoundary - 1 + 1 (for 1-based shift)
        
        return rightPrefixSum - leftPrefixSum;
    }

    private int computePrefixSum(int fenwickIndex) {
        int accumulatedSum = 0;
        
        // Traverse backwards through the binary hierarchy to aggregate the prefix sum
        while (fenwickIndex > 0) {
            accumulatedSum += fenwickMemory[fenwickIndex];
            
            // Isolate the Least Significant Bit and jump backward to the preceding structural node
            fenwickIndex -= (fenwickIndex & -fenwickIndex);
        }
        
        return accumulatedSum;
    }

    public static void main(String[] args) {
        int[] sensorReadings = {2, 4, 6, 8, 10, 12};
        BinaryIndexedTree analyticsEngine = new BinaryIndexedTree(sensorReadings);

        System.out.println("Initial sum of indices [1 to 4]: " + analyticsEngine.queryRangeSum(1, 4)); 
        // Expected: 28 (4 + 6 + 8 + 10)

        System.out.println("Executing system correction: Index 2 -> Add +5 delta");
        analyticsEngine.updateRecord(2, 5); 
        // Underlying virtual array conceptually updates index 2 from 6 to 11

        System.out.println("Re-querying sum of indices [1 to 4]: " + analyticsEngine.queryRangeSum(1, 4)); 
        // Expected: 33 (4 + 11 + 8 + 10)
    }
}