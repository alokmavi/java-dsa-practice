package dp;

public class EditDistance {

    public static int calculateMinimumOperations(String sourceSequence, String targetSequence) {
        if (sourceSequence == null || targetSequence == null) {
            throw new IllegalArgumentException("Evaluation sequences cannot be null.");
        }

        int sourceLength = sourceSequence.length();
        int targetLength = targetSequence.length();

        // 1D State Array tracking transformation costs for the current sequence boundaries
        int[] horizontalStateBoundary = new int[targetLength + 1];

        // Base Case Initialization: Target sequence construction from a null-state source
        for (int col = 0; col <= targetLength; col++) {
            horizontalStateBoundary[col] = col;
        }

        for (int row = 1; row <= sourceLength; row++) {
            
            // Tracks the topological diagonal (dp[row-1][col-1]) prior to array mutation
            int previousDiagonalState = horizontalStateBoundary[0];
            
            // Base Case Initialization: Source sequence destruction to a null-state target
            horizontalStateBoundary[0] = row;

            for (int col = 1; col <= targetLength; col++) {
                int stateAbove = horizontalStateBoundary[col];

                if (sourceSequence.charAt(row - 1) == targetSequence.charAt(col - 1)) {
                    horizontalStateBoundary[col] = previousDiagonalState;
                } else {
                    int insertOperation = horizontalStateBoundary[col - 1];
                    int deleteOperation = stateAbove;
                    int replaceOperation = previousDiagonalState;

                    // Core DP Transition: Apply a single mutation step on top of the optimal historical path
                    horizontalStateBoundary[col] = 1 + Math.min(replaceOperation, Math.min(insertOperation, deleteOperation));
                }

                // Shift the sliding window forward
                previousDiagonalState = stateAbove;
            }
        }

        return horizontalStateBoundary[targetLength];
    }

    public static void main(String[] args) {
        String inputWordAlpha = "horse";
        String targetWordAlpha = "ros";
        System.out.println("Mutation cost for 'horse' -> 'ros': " + calculateMinimumOperations(inputWordAlpha, targetWordAlpha)); 
        // Expected: 3 
        // (horse -> rorse (replace 'h' with 'r') -> rose (delete 'r') -> ros (delete 'e'))

        String inputWordBeta = "intention";
        String targetWordBeta = "execution";
        System.out.println("Mutation cost for 'intention' -> 'execution': " + calculateMinimumOperations(inputWordBeta, targetWordBeta)); 
        // Expected: 5

        String identicalWord = "system";
        System.out.println("Mutation cost for 'system' -> 'system': " + calculateMinimumOperations(identicalWord, identicalWord)); 
        // Expected: 0
    }
}