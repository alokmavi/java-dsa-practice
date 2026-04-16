package dp;

import java.util.Arrays;

public class CurrencyExchange {

    public static int calculateMinimumTender(int[] coinDenominations, int targetAmount) {
        if (coinDenominations == null || coinDenominations.length == 0 || targetAmount < 0) {
            return -1;
        }
        if (targetAmount == 0) {
            return 0;
        }

        // Initialize with a structural impossibility to allow Math.min to correctly overwrite valid paths
        int impossibleBound = targetAmount + 1;
        int[] minimumCoinsRequired = new int[targetAmount + 1];
        Arrays.fill(minimumCoinsRequired, impossibleBound);
        
        // Base Case: 0 currency requires 0 physical coins
        minimumCoinsRequired[0] = 0;

        for (int coinValue : coinDenominations) {
            // Core DP Transition: Forward traversal natively permits infinite asset reuse (Unbounded Knapsack)
            for (int currentAmount = coinValue; currentAmount <= targetAmount; currentAmount++) {
                
                minimumCoinsRequired[currentAmount] = Math.min(
                    minimumCoinsRequired[currentAmount],
                    minimumCoinsRequired[currentAmount - coinValue] + 1
                );
            }
        }

        // If the terminal index retains the impossible bound, no valid mathematical combination exists
        return minimumCoinsRequired[targetAmount] == impossibleBound ? -1 : minimumCoinsRequired[targetAmount];
    }

    public static void main(String[] args) {
        int[] standardTender = {1, 2, 5};
        int targetAlpha = 11;
        
        System.out.println("Minimum coins for amount 11: " + calculateMinimumTender(standardTender, targetAlpha)); 
        // Expected: 3 (5 + 5 + 1)

        int[] rigidTender = {2};
        int targetBeta = 3;
        
        System.out.println("Minimum coins for amount 3: " + calculateMinimumTender(rigidTender, targetBeta)); 
        // Expected: -1 (Mathematically impossible)

        int[] primeTender = {2, 3, 5};
        int targetGamma = 7;
        
        System.out.println("Minimum coins for amount 7: " + calculateMinimumTender(primeTender, targetGamma)); 
        // Expected: 2 (5 + 2)
    }
}
