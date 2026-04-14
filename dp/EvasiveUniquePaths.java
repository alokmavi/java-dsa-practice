package dp;

public class EvasiveUniquePaths {

    public static int calculateEvasivePaths(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        int totalCols = obstacleGrid[0].length;
        
        // Structural Failure: The origin point is physically blocked
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // 1D State Array: Maintains O(N) memory constraints
        int[] horizontalStateBoundary = new int[totalCols];
        
        // Seed the origin point to initiate the mathematical cascade
        horizontalStateBoundary[0] = 1;

        for (int[] currentRow : obstacleGrid) {
            for (int col = 0; col < totalCols; col++) {
                
                // Obstacle Encountered: Neutralize all routing paths through this physical memory block
                if (currentRow[col] == 1) {
                    horizontalStateBoundary[col] = 0;
                } 
                // State Transition: Summate valid inbound routes from the top and the left
                else if (col > 0) {
                    horizontalStateBoundary[col] = horizontalStateBoundary[col] + horizontalStateBoundary[col - 1];
                }
            }
        }

        return horizontalStateBoundary[totalCols - 1];
    }

    public static void main(String[] args) {
        /*
         * Matrix Alpha:
         * [0, 0, 0]
         * [0, 1, 0]
         * [0, 0, 0]
         * (The center is blocked)
         */
        int[][] matrixAlpha = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        System.out.println("Paths for Matrix Alpha: " + calculateEvasivePaths(matrixAlpha)); 
        // Expected: 2 (Right->Right->Down->Down, Down->Down->Right->Right)

        /*
         * Matrix Beta:
         * [0, 1]
         * [0, 0]
         */
        int[][] matrixBeta = {
            {0, 1},
            {0, 0}
        };
        System.out.println("Paths for Matrix Beta: " + calculateEvasivePaths(matrixBeta)); 
        // Expected: 1

        /*
         * Matrix Gamma (Edge Case):
         * [1, 0]
         * [0, 0]
         * (Origin is blocked)
         */
        int[][] matrixGamma = {
            {1, 0},
            {0, 0}
        };
        System.out.println("Paths for Matrix Gamma: " + calculateEvasivePaths(matrixGamma)); 
        // Expected: 0
    }
}