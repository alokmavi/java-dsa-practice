package dp;

public class ResourceAllocator {

    public static int calculateOptimalLoad(int[] assetWeights, int[] assetValues, int maximumCapacity) {
        if (assetWeights == null || assetValues == null || assetWeights.length != assetValues.length) {
            throw new IllegalArgumentException("Asset constraint arrays must be physically symmetric.");
        }
        if (maximumCapacity <= 0) {
            return 0;
        }

        int totalAssets = assetWeights.length;

        // 1D State Array: Tracks the maximum value achievable at every specific capacity threshold.
        // We allocate maximumCapacity + 1 to account for the 0-capacity baseline.
        int[] optimalValueAtCapacity = new int[maximumCapacity + 1];

        for (int assetIndex = 0; assetIndex < totalAssets; assetIndex++) {
            int currentWeight = assetWeights[assetIndex];
            int currentValue = assetValues[assetIndex];

            // Core DP Transition: Reverse traversal prevents systemic double-counting of the current asset.
            // We terminate the loop early when currentCapacity < currentWeight because the asset physically cannot fit.
            for (int currentCapacity = maximumCapacity; currentCapacity >= currentWeight; currentCapacity--) {
                
                int valueIfSkipped = optimalValueAtCapacity[currentCapacity];
                int valueIfTaken = optimalValueAtCapacity[currentCapacity - currentWeight] + currentValue;

                optimalValueAtCapacity[currentCapacity] = Math.max(valueIfSkipped, valueIfTaken);
            }
        }

        return optimalValueAtCapacity[maximumCapacity];
    }

    public static void main(String[] args) {
        int[] weightsAlpha = {1, 2, 3};
        int[] valuesAlpha = {60, 100, 120};
        int capacityAlpha = 5;
        
        System.out.println("Optimal load for Server Alpha (5GB): " + calculateOptimalLoad(weightsAlpha, valuesAlpha, capacityAlpha)); 
        // Expected: 220 (Asset 1 + Asset 2 -> 2GB + 3GB = 5GB. Value: 100 + 120)

        int[] weightsBeta = {1, 3, 4, 5};
        int[] valuesBeta = {1, 4, 5, 7};
        int capacityBeta = 7;
        
        System.out.println("Optimal load for Server Beta (7GB): " + calculateOptimalLoad(weightsBeta, valuesBeta, capacityBeta)); 
        // Expected: 9 (Asset 1 + Asset 2 -> 3GB + 4GB = 7GB. Value: 4 + 5)

        int[] heavyWeights = {10, 20, 30};
        int[] heavyValues = {60, 100, 120};
        int weakCapacity = 5;
        
        System.out.println("Optimal load for Weak Server (5GB): " + calculateOptimalLoad(heavyWeights, heavyValues, weakCapacity)); 
        // Expected: 0 (No assets fit within the memory constraint)
    }
}