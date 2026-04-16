package dp;

import java.util.Arrays;

public class TargetSum {

    public static int calculateExpressionCombinations(int[] numericStream, int requiredTarget) {
        if (numericStream == null || numericStream.length == 0) {
            return 0;
        }

        int systemicSum = 0;
        for (int value : numericStream) {
            systemicSum += value;
        }

        // Structural impossibility checks: 
        // 1. The target exceeds the absolute maximum possible summation.
        // 2. The derived subset target requires a fractional integer (odd sum).
        if (Math.abs(requiredTarget) > systemicSum || (systemicSum + requiredTarget) % 2 != 0) {
            return 0;
        }

        int positiveSubsetTarget = (systemicSum + requiredTarget) / 2;

        // 1D State Array: Tracks the distinct combinatorial paths to reach any given capacity.
        int[] subsetCombinationState = new int[positiveSubsetTarget + 1];
        
        // Base Case: There is exactly 1 way to reach a sum of 0 (by selecting an empty subset).
        subsetCombinationState[0] = 1;

        for (int currentValue : numericStream) {
            // Reverse capacity traversal strictly enforces the 0/1 single-use constraint.
            for (int capacity = positiveSubsetTarget; capacity >= currentValue; capacity--) {
                // State Transition: The combinations to reach `capacity` is the sum of 
                // prior combinations ignoring this value, PLUS the combinations that required it.
                subsetCombinationState[capacity] = subsetCombinationState[capacity] + subsetCombinationState[capacity - currentValue];
            }
        }

        return subsetCombinationState[positiveSubsetTarget];
    }

    public static void main(String[] args) {
        int[] serverLoads = {1, 1, 1, 1, 1};
        int balanceTargetOne = 3;
        
        System.out.println("Distinct assignments to reach Target 3: " + calculateExpressionCombinations(serverLoads, balanceTargetOne)); 
        // Expected: 5
        // (-1+1+1+1+1, 1-1+1+1+1, 1+1-1+1+1, 1+1+1-1+1, 1+1+1+1-1)

        int[] transactionStream = {1};
        int balanceTargetTwo = 1;
        
        System.out.println("Distinct assignments to reach Target 1: " + calculateExpressionCombinations(transactionStream, balanceTargetTwo)); 
        // Expected: 1

        int[] rigidSystem = {100};
        int impossibleTarget = -200;
        
        System.out.println("Distinct assignments to reach Target -200: " + calculateExpressionCombinations(rigidSystem, impossibleTarget)); 
        // Expected: 0 (Aborts instantly via boundary validation)
    }
}