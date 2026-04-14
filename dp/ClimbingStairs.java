package dp;

public class ClimbingStairs {

    public static int computeDistinctPaths(int totalSteps) {
        // Boundary validation: Negative steps are physically impossible
        if (totalSteps <= 0) {
            return 0;
        }
        
        // Base Cases: 
        // 1 step has 1 path (1)
        // 2 steps have 2 paths (1+1, or 2)
        if (totalSteps == 1) {
            return 1;
        }
        if (totalSteps == 2) {
            return 2;
        }

        // State variables to track the (N-2) and (N-1) results
        int twoStepsBack = 1;
        int oneStepBack = 2;
        int currentDistinctPaths = 0;

        // Bottom-Up Tabulation: Iteratively calculate future states based on historical states
        for (int currentStep = 3; currentStep <= totalSteps; currentStep++) {
            currentDistinctPaths = twoStepsBack + oneStepBack;
            
            // Slide the mathematical window forward for the next iteration
            twoStepsBack = oneStepBack;
            oneStepBack = currentDistinctPaths;
        }

        return currentDistinctPaths;
    }

    public static void main(String[] args) {
        int flightOne = 3;
        System.out.println("Distinct paths for " + flightOne + " steps: " + computeDistinctPaths(flightOne)); 
        // Expected: 3 (1+1+1, 1+2, 2+1)

        int flightTwo = 5;
        System.out.println("Distinct paths for " + flightTwo + " steps: " + computeDistinctPaths(flightTwo)); 
        // Expected: 8

        int flightThree = 45;
        System.out.println("Distinct paths for " + flightThree + " steps: " + computeDistinctPaths(flightThree)); 
        // Expected: 1836311903 (Executes instantly. Naive recursion would take billions of cycles.)
    }
}