package string_search;

public class RollingHashSearch {

    private static final int ALPHABET_BASE = 256;
    private static final int PRIME_MODULUS = 1000000007;

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

        long patternHash = 0;
        long slidingWindowHash = 0;
        long leadingCharacterMultiplier = 1;

        // Precompute the multiplier for the outgoing character: (ALPHABET_BASE ^ (patternLength - 1)) % PRIME_MODULUS
        for (int i = 0; i < patternLength - 1; i++) {
            leadingCharacterMultiplier = (leadingCharacterMultiplier * ALPHABET_BASE) % PRIME_MODULUS;
        }

        // Initialize the foundational hashes for the target and the first memory window
        for (int i = 0; i < patternLength; i++) {
            patternHash = (ALPHABET_BASE * patternHash + targetPattern.charAt(i)) % PRIME_MODULUS;
            slidingWindowHash = (ALPHABET_BASE * slidingWindowHash + textStream.charAt(i)) % PRIME_MODULUS;
        }

        for (int extractionIndex = 0; extractionIndex <= streamLength - patternLength; extractionIndex++) {
            
            // Hash convergence detected: Execute strict physical boundary validation to bypass collision anomalies
            if (patternHash == slidingWindowHash) {
                if (executeStrictVerification(targetPattern, textStream, extractionIndex)) {
                    return extractionIndex;
                }
            }

            // Roll the mathematical window forward in strict O(1) time
            if (extractionIndex < streamLength - patternLength) {
                char outgoingCharacter = textStream.charAt(extractionIndex);
                char incomingCharacter = textStream.charAt(extractionIndex + patternLength);

                slidingWindowHash = (slidingWindowHash - (outgoingCharacter * leadingCharacterMultiplier) % PRIME_MODULUS);
                slidingWindowHash = (slidingWindowHash + PRIME_MODULUS) % PRIME_MODULUS; 
                slidingWindowHash = (slidingWindowHash * ALPHABET_BASE + incomingCharacter) % PRIME_MODULUS;
            }
        }

        return -1;
    }

    private static boolean executeStrictVerification(String targetPattern, String textStream, int startIndex) {
        int patternLength = targetPattern.length();
        for (int i = 0; i < patternLength; i++) {
            if (targetPattern.charAt(i) != textStream.charAt(startIndex + i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String diagnosticLog = "server_node_alpha_connection_timeout_established";
        
        System.out.println("Pattern 'alpha' index: " + locatePatternIndex("alpha", diagnosticLog)); 
        // Expected: 12

        System.out.println("Pattern 'timeout' index: " + locatePatternIndex("timeout", diagnosticLog)); 
        // Expected: 29

        System.out.println("Pattern 'critical' index: " + locatePatternIndex("critical", diagnosticLog)); 
        // Expected: -1
        
        System.out.println("Pattern 'established' index: " + locatePatternIndex("established", diagnosticLog)); 
        // Expected: 37
    }
}