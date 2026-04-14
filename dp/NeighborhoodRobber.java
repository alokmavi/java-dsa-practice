package dp;

public class NeighborhoodRobber {

    public static int calculateMaxExtraction(int[] neighborhoodWealth) {
        if (neighborhoodWealth == null || neighborhoodWealth.length == 0) {
            return 0;
        }

        int maxExtractionTwoBack = 0;
        int maxExtractionPrevious = 0;

        for (int currentHouseLoot : neighborhoodWealth) {
            // Core DP Transition: Evaluate the constraint violation cost against the potential gain
            int optimalExtractionCurrent = Math.max(maxExtractionPrevious, maxExtractionTwoBack + currentHouseLoot);

            maxExtractionTwoBack = maxExtractionPrevious;
            maxExtractionPrevious = optimalExtractionCurrent;
        }

        return maxExtractionPrevious;
    }

    public static void main(String[] args) {
        int[] wealthySuburbs = {1, 2, 3, 1};
        System.out.println("Max extraction for Suburb A: " + calculateMaxExtraction(wealthySuburbs)); 
        // Expected: 4 (Rob house 0 for 1, rob house 2 for 3)

        int[] fortifiedCompound = {2, 7, 9, 3, 1};
        System.out.println("Max extraction for Compound B: " + calculateMaxExtraction(fortifiedCompound)); 
        // Expected: 12 (Rob house 0 for 2, house 2 for 9, house 4 for 1)

        int[] trappedAlley = {2, 1, 1, 2};
        System.out.println("Max extraction for Alley C: " + calculateMaxExtraction(trappedAlley)); 
        // Expected: 4 (Rob house 0 for 2, rob house 3 for 2. Notice the gap of two skipped houses)
    }
}