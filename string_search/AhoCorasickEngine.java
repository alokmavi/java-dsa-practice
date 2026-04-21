package string_search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AhoCorasickEngine {

    static class AutomatonNode {
        final AutomatonNode[] characterTransitions;
        AutomatonNode failureFallback;
        final List<String> terminalMatches;

        AutomatonNode() {
            this.characterTransitions = new AutomatonNode[26];
            this.failureFallback = null;
            this.terminalMatches = new ArrayList<>();
        }
    }

    private final AutomatonNode structuralRoot;
    private boolean isCompiled;

    public AhoCorasickEngine() {
        this.structuralRoot = new AutomatonNode();
        this.isCompiled = false;
    }

    public void ingestVocabulary(String targetPattern) {
        if (isCompiled) {
            throw new IllegalStateException("Vocabulary cannot be modified after the automaton has been compiled.");
        }
        if (targetPattern == null || targetPattern.isEmpty()) {
            return;
        }

        AutomatonNode traversalPointer = structuralRoot;
        for (char evaluationChar : targetPattern.toLowerCase().toCharArray()) {
            int transitionIndex = evaluationChar - 'a';
            if (transitionIndex < 0 || transitionIndex >= 26) {
                throw new IllegalArgumentException("System restricted to lowercase alphabetic sequences.");
            }

            if (traversalPointer.characterTransitions[transitionIndex] == null) {
                traversalPointer.characterTransitions[transitionIndex] = new AutomatonNode();
            }
            traversalPointer = traversalPointer.characterTransitions[transitionIndex];
        }
        traversalPointer.terminalMatches.add(targetPattern);
    }

    public void compileAutomaton() {
        if (isCompiled) return;

        Queue<AutomatonNode> resolutionQueue = new ArrayDeque<>();

        // Level 1 Initialization: Depth-1 nodes inherently fallback to the absolute root
        for (AutomatonNode depthOneNode : structuralRoot.characterTransitions) {
            if (depthOneNode != null) {
                depthOneNode.failureFallback = structuralRoot;
                resolutionQueue.offer(depthOneNode);
            }
        }

        // BFS traversal to establish the failure link network
        while (!resolutionQueue.isEmpty()) {
            AutomatonNode currentNode = resolutionQueue.poll();

            for (int i = 0; i < 26; i++) {
                AutomatonNode childNode = currentNode.characterTransitions[i];
                if (childNode != null) {
                    
                    AutomatonNode fallbackPointer = currentNode.failureFallback;
                    
                    // Trace back through the failure chain until a valid structural transition is found
                    while (fallbackPointer != null && fallbackPointer.characterTransitions[i] == null) {
                        fallbackPointer = fallbackPointer.failureFallback;
                    }

                    if (fallbackPointer == null) {
                        childNode.failureFallback = structuralRoot;
                    } else {
                        childNode.failureFallback = fallbackPointer.characterTransitions[i];
                        // Merge sub-pattern matches (e.g., if "she" is matched, "he" is implicitly matched)
                        childNode.terminalMatches.addAll(childNode.failureFallback.terminalMatches);
                    }

                    resolutionQueue.offer(childNode);
                }
            }
        }
        this.isCompiled = true;
    }

    public Map<String, List<Integer>> executeStreamAnalysis(String textStream) {
        if (!isCompiled) {
            throw new IllegalStateException("Automaton must be compiled prior to stream analysis.");
        }
        if (textStream == null || textStream.isEmpty()) {
            return new HashMap<>();
        }

        Map<String, List<Integer>> detectedSignatures = new HashMap<>();
        AutomatonNode traversalPointer = structuralRoot;

        for (int extractionIndex = 0; extractionIndex < textStream.length(); extractionIndex++) {
            int transitionIndex = textStream.charAt(extractionIndex) - 'a';
            
            if (transitionIndex < 0 || transitionIndex >= 26) {
                // Invalid character acts as a hard break in continuous sequence evaluation
                traversalPointer = structuralRoot;
                continue;
            }

            // Fallback via the compiled network upon structural mismatch
            while (traversalPointer != null && traversalPointer.characterTransitions[transitionIndex] == null) {
                traversalPointer = traversalPointer.failureFallback;
            }

            if (traversalPointer == null) {
                traversalPointer = structuralRoot;
            } else {
                traversalPointer = traversalPointer.characterTransitions[transitionIndex];
                
                // Record all valid signatures terminating at the current absolute index
                for (String signature : traversalPointer.terminalMatches) {
                    detectedSignatures.putIfAbsent(signature, new ArrayList<>());
                    // Calculate starting index by subtracting the signature length from the current stream index
                    detectedSignatures.get(signature).add(extractionIndex - signature.length() + 1);
                }
            }
        }

        return detectedSignatures;
    }

    public static void main(String[] args) {
        AhoCorasickEngine networkScanner = new AhoCorasickEngine();
        
        networkScanner.ingestVocabulary("he");
        networkScanner.ingestVocabulary("she");
        networkScanner.ingestVocabulary("his");
        networkScanner.ingestVocabulary("hers");
        
        networkScanner.compileAutomaton();

        String interceptedTraffic = "ushers";
        System.out.println("Analyzing Traffic Payload: '" + interceptedTraffic + "'");
        
        Map<String, List<Integer>> threatReport = networkScanner.executeStreamAnalysis(interceptedTraffic);
        
        for (Map.Entry<String, List<Integer>> detection : threatReport.entrySet()) {
            System.out.println("Signature '" + detection.getKey() + "' detected at indices: " + detection.getValue());
        }
        /*
         * Expected Output:
         * Signature 'she' detected at indices: [1]
         * Signature 'he' detected at indices: [2]
         * Signature 'hers' detected at indices: [2]
         */
    }
}