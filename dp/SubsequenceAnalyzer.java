package dp;

import java.util.Arrays;

public class SubsequenceAnalyzer {

    public static int calculateLIS(int[] numericSequence) {
        if (numericSequence == null || numericSequence.length == 0) {
            return 0;
        }

        int sequenceLength = numericSequence.length;
        
        // 1D State Array: Tracks the maximum valid subsequence length terminating at each specific index.
        int[] subsequenceState = new int[sequenceLength];
        
        // Base Case: Every discrete element constitutes a valid subsequence of length 1.
        Arrays.fill(subsequenceState, 1);

        int globalMaximumLength = 1;

        // Traverse the sequence to build the optimal state at each index
        for (int evaluationIndex = 1; evaluationIndex < sequenceLength; evaluationIndex++) {
            
            // Core DP Transition: Perform a complete historical scan to identify valid upstream connections
            for (int historicalIndex = 0; historicalIndex < evaluationIndex; historicalIndex++) {
                
                // Structural constraint: The sequence must be strictly increasing
                if (numericSequence[evaluationIndex] > numericSequence[historicalIndex]) {
                    subsequenceState[evaluationIndex] = Math.max(
                        subsequenceState[evaluationIndex], 
                        subsequenceState[historicalIndex] + 1
                    );
                }
            }
            
            // The global maximum might not terminate at the final index of the array
            globalMaximumLength = Math.max(globalMaximumLength, subsequenceState[evaluationIndex]);
        }

        return globalMaximumLength;
    }

    public static void main(String[] args) {
        int[] volatileMarket = {10, 9, 2, 5, 3, 7, 101, 18};
        
        System.out.println("Longest trend in Market Alpha: " + calculateLIS(volatileMarket)); 
        // Expected: 4 (The optimal sequence is [2, 3, 7, 101] or [2, 5, 7, 18])

        int[] stagnantMarket = {7, 7, 7, 7, 7, 7, 7};
        
        System.out.println("Longest trend in Market Beta: " + calculateLIS(stagnantMarket)); 
        // Expected: 1 (Strictly increasing constraint invalidates flat sequences)

        int[] ascendingMarket = {0, 1, 0, 3, 2, 3};
        
        System.out.println("Longest trend in Market Gamma: " + calculateLIS(ascendingMarket)); 
        // Expected: 4 ([0, 1, 2, 3])
    }
}