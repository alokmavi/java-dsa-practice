package dp;

public class SequenceAlignment {

    public static int computeLCS(String sequenceAlpha, String sequenceBeta) {
        if (sequenceAlpha == null || sequenceBeta == null || sequenceAlpha.isEmpty() || sequenceBeta.isEmpty()) {
            return 0;
        }

        // Structural constraint: Bind the auxiliary memory array to the shorter sequence
        if (sequenceAlpha.length() < sequenceBeta.length()) {
            return computeLCS(sequenceBeta, sequenceAlpha);
        }

        int colLimit = sequenceBeta.length();
        int[] horizontalStateBoundary = new int[colLimit + 1];

        for (int row = 1; row <= sequenceAlpha.length(); row++) {
            
            // Tracks the topological diagonal (dp[row-1][col-1]) prior to array mutation
            int previousDiagonalState = 0; 
            
            for (int col = 1; col <= colLimit; col++) {
                // Cache the cell directly above before we overwrite it, as it becomes the diagonal for the NEXT column
                int stateAbove = horizontalStateBoundary[col]; 

                // Reality 1: Characters align. Inherit from the diagonal and increment.
                if (sequenceAlpha.charAt(row - 1) == sequenceBeta.charAt(col - 1)) {
                    horizontalStateBoundary[col] = previousDiagonalState + 1;
                } 
                // Reality 2: Character mismatch. Inherit the maximum established subsequence length.
                else {
                    horizontalStateBoundary[col] = Math.max(horizontalStateBoundary[col], horizontalStateBoundary[col - 1]);
                }

                // Shift the sliding window forward
                previousDiagonalState = stateAbove;
            }
        }

        return horizontalStateBoundary[colLimit];
    }

    public static void main(String[] args) {
        String baseStrand = "abcde";
        String comparisonStrand = "ace";
        System.out.println("LCS for 'abcde' and 'ace': " + computeLCS(baseStrand, comparisonStrand)); 
        // Expected: 3 ("ace")

        String diffStrandA = "abc";
        String diffStrandB = "abc";
        System.out.println("LCS for identical sequences: " + computeLCS(diffStrandA, diffStrandB)); 
        // Expected: 3

        String orphanStrandA = "abc";
        String orphanStrandB = "def";
        System.out.println("LCS for disjoint sequences: " + computeLCS(orphanStrandA, orphanStrandB)); 
        // Expected: 0
    }
}