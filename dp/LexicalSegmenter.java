package dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Arrays;

public class LexicalSegmenter {

    public static boolean verifyCompleteSegmentation(String textStream, List<String> lexicalDictionary) {
        if (textStream == null || textStream.isEmpty() || lexicalDictionary == null || lexicalDictionary.isEmpty()) {
            return false;
        }

        // Convert the list to a HashSet for strictly O(1) lookup times during the historical scan
        Set<String> dictionaryCache = new HashSet<>(lexicalDictionary);
        int streamLength = textStream.length();

        // 1D State Array: validSegmentation[i] represents whether textStream.substring(0, i) is fully valid
        boolean[] validSegmentation = new boolean[streamLength + 1];
        
        // Base Case: An empty prefix requires zero dictionary words and is inherently valid
        validSegmentation[0] = true;

        for (int evaluationIndex = 1; evaluationIndex <= streamLength; evaluationIndex++) {
            
            // Core DP Transition: Perform a historical scan to find a valid partition point
            for (int historicalPartition = 0; historicalPartition < evaluationIndex; historicalPartition++) {
                
                // If the prefix up to historicalPartition is valid, evaluate the remaining suffix
                if (validSegmentation[historicalPartition]) {
                    String suffixToken = textStream.substring(historicalPartition, evaluationIndex);
                    
                    if (dictionaryCache.contains(suffixToken)) {
                        validSegmentation[evaluationIndex] = true;
                        // Optimization: A single valid partition is sufficient. Bypass redundant historical checks.
                        break; 
                    }
                }
            }
        }

        return validSegmentation[streamLength];
    }

    public static void main(String[] args) {
        String logOne = "leetcode";
        List<String> dictOne = Arrays.asList("leet", "code");
        System.out.println("Segmentation for Log 1: " + verifyCompleteSegmentation(logOne, dictOne)); 
        // Expected: true

        String logTwo = "applepenapple";
        List<String> dictTwo = Arrays.asList("apple", "pen");
        System.out.println("Segmentation for Log 2: " + verifyCompleteSegmentation(logTwo, dictTwo)); 
        // Expected: true (Dictionary assets can be reused infinitely)

        String logThree = "catsandog";
        List<String> dictThree = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println("Segmentation for Log 3: " + verifyCompleteSegmentation(logThree, dictThree)); 
        // Expected: false (Notice the missing 'd' in dog/sand)
    }
}