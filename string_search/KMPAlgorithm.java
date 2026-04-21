package string_search;

public class KMPAlgorithm {

    public static int locatePatternIndex(String targetPattern, String textStream) {
        if (targetPattern == null || textStream == null) {
            throw new IllegalArgumentException("System requires strictly non-null evaluation streams.");
        }

        int patternLength = targetPattern.length();
        int streamLength = textStream.length();

        if (patternLength == 0) {
            return 0;
        }
        if (streamLength < patternLength) {
            return -1;
        }

        // Pre-compute the deterministic fallback states
        int[] prefixSuffixTracker = buildLPSArray(targetPattern);
        
        int streamPointer = 0;
        int patternPointer = 0;

        while (streamPointer < streamLength) {
            // State: Characters align. Advance both operational pointers.
            if (targetPattern.charAt(patternPointer) == textStream.charAt(streamPointer)) {
                streamPointer++;
                patternPointer++;
            }

            // Terminal State: The entire pattern has been successfully matched.
            if (patternPointer == patternLength) {
                // Calculate the absolute starting index of the match within the text stream
                return streamPointer - patternPointer;
            } 
            // State: Structural mismatch detected after partial progress.
            else if (streamPointer < streamLength && targetPattern.charAt(patternPointer) != textStream.charAt(streamPointer)) {
                
                if (patternPointer != 0) {
                    // Bypass redundant evaluations: Shift the pattern pointer using the LPS fallback state
                    patternPointer = prefixSuffixTracker[patternPointer - 1];
                } else {
                    // Zero progress made: The stream character is entirely invalid. Advance the stream pointer.
                    streamPointer++;
                }
            }
        }

        return -1;
    }

    private static int[] buildLPSArray(String pattern) {
        int patternLength = pattern.length();
        int[] lpsArray = new int[patternLength];
        
        int previousLongestPrefix = 0;
        int evaluationPointer = 1;

        // Base Case: The first character inherently has no prefix/suffix overlap
        lpsArray[0] = 0;

        while (evaluationPointer < patternLength) {
            if (pattern.charAt(evaluationPointer) == pattern.charAt(previousLongestPrefix)) {
                previousLongestPrefix++;
                lpsArray[evaluationPointer] = previousLongestPrefix;
                evaluationPointer++;
            } else {
                if (previousLongestPrefix != 0) {
                    // Fallback to the previous valid prefix state to re-evaluate
                    previousLongestPrefix = lpsArray[previousLongestPrefix - 1];
                } else {
                    lpsArray[evaluationPointer] = 0;
                    evaluationPointer++;
                }
            }
        }

        return lpsArray;
    }

    public static void main(String[] args) {
        String diagnosticLog = "abxabcabcaby";
        String targetAlpha = "abcaby";
        
        System.out.println("Pattern 'abcaby' index: " + locatePatternIndex(targetAlpha, diagnosticLog)); 
        // Expected: 6

        String redundantLog = "AAAAABAAABA";
        String targetBeta = "AAAA";
        
        System.out.println("Pattern 'AAAA' index: " + locatePatternIndex(targetBeta, redundantLog)); 
        // Expected: 0 (Finds the first occurring overlapping instance)

        String isolatedLog = "system_failure";
        String targetGamma = "critical";
        
        System.out.println("Pattern 'critical' index: " + locatePatternIndex(targetGamma, isolatedLog)); 
        // Expected: -1
    }
}