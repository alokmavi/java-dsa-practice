package dp;

public class UniquePaths {

    public static int calculateTotalPaths(int totalRows, int totalCols) {
        if (totalRows <= 0 || totalCols <= 0) {
            return 0;
        }

        // 1D State Array: We collapse the O(M * N) memory footprint to O(N).
        // This tracks the "previous row" state, which morphs into the "current row" state iteratively.
        int[] horizontalStateBoundary = new int[totalCols];

        // Base Case Initialization: The absolute top boundary only permits continuous rightward movement
        for (int col = 0; col < totalCols; col++) {
            horizontalStateBoundary[col] = 1;
        }

        // Execute top-down row traversal
        for (int row = 1; row < totalRows; row++) {
            
            // We initiate the column loop at index 1 because index 0 (the absolute left boundary)
            // only permits continuous downward movement and is permanently locked to a value of 1.
            for (int col = 1; col < totalCols; col++) {
                
                // State Transition: 
                // horizontalStateBoundary[col] holds the state from the cell DIRECTLY ABOVE.
                // horizontalStateBoundary[col - 1] holds the newly calculated state from the cell DIRECTLY LEFT.
                horizontalStateBoundary[col] = horizontalStateBoundary[col] + horizontalStateBoundary[col - 1];
            }
        }

        // The terminal index holds the culmination of all converging structural routes
        return horizontalStateBoundary[totalCols - 1];
    }

    public static void main(String[] args) {
        int gridRowsAlpha = 3;
        int gridColsAlpha = 2;
        System.out.println("Paths for 3x2 Grid: " + calculateTotalPaths(gridRowsAlpha, gridColsAlpha)); 
        // Expected: 3 
        // (Right->Down->Down, Down->Right->Down, Down->Down->Right)

        int gridRowsBeta = 3;
        int gridColsBeta = 7;
        System.out.println("Paths for 3x7 Grid: " + calculateTotalPaths(gridRowsBeta, gridColsBeta)); 
        // Expected: 28

        int gridRowsGamma = 18;
        int gridColsGamma = 18;
        System.out.println("Paths for 18x18 Grid: " + calculateTotalPaths(gridRowsGamma, gridColsGamma)); 
        // Expected: 2333606220 (Executes instantly via DP tabulation. Standard DFS would timeout completely.)
    }
}