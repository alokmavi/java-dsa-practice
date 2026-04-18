package trie;

public class LexicalWildcardDictionary {

    static class LexicalNode {
        final LexicalNode[] characterLinks;
        boolean isTerminalNode;

        LexicalNode() {
            this.characterLinks = new LexicalNode[26];
            this.isTerminalNode = false;
        }
    }

    private final LexicalNode structuralRoot;

    public LexicalWildcardDictionary() {
        this.structuralRoot = new LexicalNode();
    }

    public void insertVocabulary(String newWord) {
        if (newWord == null || newWord.isEmpty()) {
            return;
        }

        LexicalNode traversalPointer = structuralRoot;
        
        for (char currentCharacter : newWord.toLowerCase().toCharArray()) {
            int linkIndex = currentCharacter - 'a';
            
            if (linkIndex < 0 || linkIndex >= 26) {
                throw new IllegalArgumentException("System only supports lowercase alphabetical strings.");
            }

            if (traversalPointer.characterLinks[linkIndex] == null) {
                traversalPointer.characterLinks[linkIndex] = new LexicalNode();
            }
            
            traversalPointer = traversalPointer.characterLinks[linkIndex];
        }
        
        traversalPointer.isTerminalNode = true;
    }

    public boolean evaluatePattern(String searchPattern) {
        if (searchPattern == null || searchPattern.isEmpty()) {
            return false;
        }
        return executeRecursiveSearch(searchPattern.toLowerCase(), 0, structuralRoot);
    }

    private boolean executeRecursiveSearch(String pattern, int evaluationIndex, LexicalNode activeNode) {
        // Base Case: The entire pattern stream has been consumed. Verify terminal state.
        if (evaluationIndex == pattern.length()) {
            return activeNode.isTerminalNode;
        }

        char evaluationCharacter = pattern.charAt(evaluationIndex);

        if (evaluationCharacter == '.') {
            // Wildcard execution: Perform an exhaustive DFS across all allocated memory paths
            for (LexicalNode allocatedPath : activeNode.characterLinks) {
                if (allocatedPath != null) {
                    if (executeRecursiveSearch(pattern, evaluationIndex + 1, allocatedPath)) {
                        return true; 
                    }
                }
            }
            return false; 
        } else {
            // Standard execution: Proceed down the deterministic memory path
            int linkIndex = evaluationCharacter - 'a';
            
            if (linkIndex < 0 || linkIndex >= 26 || activeNode.characterLinks[linkIndex] == null) {
                return false;
            }
            
            return executeRecursiveSearch(pattern, evaluationIndex + 1, activeNode.characterLinks[linkIndex]);
        }
    }

    public static void main(String[] args) {
        LexicalWildcardDictionary queryEngine = new LexicalWildcardDictionary();

        queryEngine.insertVocabulary("bad");
        queryEngine.insertVocabulary("dad");
        queryEngine.insertVocabulary("mad");

        System.out.println("Pattern 'pad' exists: " + queryEngine.evaluatePattern("pad")); // Expected: false
        System.out.println("Pattern 'bad' exists: " + queryEngine.evaluatePattern("bad")); // Expected: true
        
        // Testing wildcard resolution
        System.out.println("Pattern '.ad' exists: " + queryEngine.evaluatePattern(".ad")); // Expected: true
        System.out.println("Pattern 'b..' exists: " + queryEngine.evaluatePattern("b..")); // Expected: true
        System.out.println("Pattern 'm.x' exists: " + queryEngine.evaluatePattern("m.x")); // Expected: false
    }
}