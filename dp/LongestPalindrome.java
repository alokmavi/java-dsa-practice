package dp;

public class LongestPalindrome {

    public static String extractLongest(String textStream) {
        if (textStream == null || textStream.length() <= 1) {
            return textStream;
        }

        int streamLength = textStream.length();
        
        // 2D State Matrix: Tracks boolean validity of substrings bounded by [startIndex][endIndex]
        boolean[][] palindromicState = new boolean[streamLength][streamLength];
        
        int absoluteMaxLength = 1;
        int optimalStartIndex = 0;

        // Base Case Alpha: Every discrete character is a valid palindrome of length 1
        for (int i = 0; i < streamLength; i++) {
            palindromicState[i][i] = true;
        }

        // Base Case Beta: Evaluate contiguous dual-character sequences to prevent bounds overflow during transition
        for (int i = 0; i < streamLength - 1; i++) {
            if (textStream.charAt(i) == textStream.charAt(i + 1)) {
                palindromicState[i][i + 1] = true;
                absoluteMaxLength = 2;
                optimalStartIndex = i;
            }
        }

        // Core DP Transition: Traverse strictly by interval span (length), not by absolute matrix coordinates
        for (int intervalSpan = 3; intervalSpan <= streamLength; intervalSpan++) {
            
            // Slide the starting window across the string
            for (int startIndex = 0; startIndex <= streamLength - intervalSpan; startIndex++) {
                
                // Derive the terminal index based on the current interval span
                int endIndex = startIndex + intervalSpan - 1;

                // State Check: Outer characters must match AND the enclosed substring must be historically validated
                if (textStream.charAt(startIndex) == textStream.charAt(endIndex) && palindromicState[startIndex + 1][endIndex - 1]) {
                    palindromicState[startIndex][endIndex] = true;
                    
                    // Capture the longest continuously validated state
                    if (intervalSpan > absoluteMaxLength) {
                        absoluteMaxLength = intervalSpan;
                        optimalStartIndex = startIndex;
                    }
                }
            }
        }

        return textStream.substring(optimalStartIndex, optimalStartIndex + absoluteMaxLength);
    }

    public static void main(String[] args) {
        String logAlpha = "babad";
        System.out.println("Longest sequence in Log Alpha: " + extractLongest(logAlpha)); 
        // Expected: "bab" or "aba"

        String logBeta = "cbbd";
        System.out.println("Longest sequence in Log Beta: " + extractLongest(logBeta)); 
        // Expected: "bb"

        String logGamma = "a";
        System.out.println("Longest sequence in Log Gamma: " + extractLongest(logGamma)); 
        // Expected: "a"
        
        String complexLog = "racecar";
        System.out.println("Longest sequence in Complex Log: " + extractLongest(complexLog)); 
        // Expected: "racecar"
    }
}