package dp;

public class CircularNeighborhoodRobber {

    public static int calculateMaxExtraction(int[] neighborhoodWealth) {
        if (neighborhoodWealth == null || neighborhoodWealth.length == 0) {
            return 0;
        }
        
        // Edge Cases: The circular constraint does not apply to isolated nodes
        if (neighborhoodWealth.length == 1) {
            return neighborhoodWealth[0];
        }
        if (neighborhoodWealth.length == 2) {
            return Math.max(neighborhoodWealth[0], neighborhoodWealth[1]);
        }

        // Scenario Alpha: Target the first house, strictly isolating the final house
        int maxExtractionAlpha = executeLinearHeist(neighborhoodWealth, 0, neighborhoodWealth.length - 2);

        // Scenario Beta: Target the final house, strictly isolating the first house
        int maxExtractionBeta = executeLinearHeist(neighborhoodWealth, 1, neighborhoodWealth.length - 1);

        // The global optimal path is the maximum of the two mutually exclusive scenarios
        return Math.max(maxExtractionAlpha, maxExtractionBeta);
    }

    private static int executeLinearHeist(int[] wealthArray, int startIndex, int endIndex) {
        int maxExtractionTwoBack = 0;
        int maxExtractionPrevious = 0;

        for (int i = startIndex; i <= endIndex; i++) {
            // Core DP Transition identical to the linear architecture
            int optimalExtractionCurrent = Math.max(maxExtractionPrevious, maxExtractionTwoBack + wealthArray[i]);
            
            maxExtractionTwoBack = maxExtractionPrevious;
            maxExtractionPrevious = optimalExtractionCurrent;
        }

        return maxExtractionPrevious;
    }

    public static void main(String[] args) {
        int[] circularCulDeSacOne = {2, 3, 2};
        System.out.println("Max extraction for Cul-De-Sac A: " + calculateMaxExtraction(circularCulDeSacOne)); 
        // Expected: 3 (Cannot rob 2 and 2 because they are adjacent in a circle)

        int[] circularCulDeSacTwo = {1, 2, 3, 1};
        System.out.println("Max extraction for Cul-De-Sac B: " + calculateMaxExtraction(circularCulDeSacTwo)); 
        // Expected: 4 (Rob house 1 for 2, rob house 3 for 1 -> wait, 1 and 3 are 2 and 1. Max is 4.)
        // Breakdown for B:
        // Alpha (1, 2, 3): Max is 4 (1 + 3)
        // Beta (2, 3, 1): Max is 3 (2 + 1) -> Result is Math.max(4, 3) = 4

        int[] fortifiedRoundabout = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        System.out.println("Max extraction for Roundabout C: " + calculateMaxExtraction(fortifiedRoundabout)); 
        // Expected: 16
    }
}