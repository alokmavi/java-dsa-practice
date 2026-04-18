package trie;

public class LexicalTrie {

    static class LexicalNode {
        // Enforces a strict 26-character lowercase English boundary
        final LexicalNode[] characterLinks;
        boolean isTerminalNode;

        LexicalNode() {
            this.characterLinks = new LexicalNode[26];
            this.isTerminalNode = false;
        }
    }

    private final LexicalNode structuralRoot;

    public LexicalTrie() {
        this.structuralRoot = new LexicalNode();
    }

    public void insertVocabulary(String newWord) {
        if (newWord == null || newWord.isEmpty()) {
            return;
        }

        LexicalNode traversalPointer = structuralRoot;
        
        for (char currentCharacter : newWord.toLowerCase().toCharArray()) {
            int linkIndex = currentCharacter - 'a';
            
            // Boundary validation: Discard non-lowercase alphabetical characters
            if (linkIndex < 0 || linkIndex >= 26) {
                throw new IllegalArgumentException("System only supports lowercase alphabetical strings.");
            }

            // Allocate new memory block if the routing path does not exist
            if (traversalPointer.characterLinks[linkIndex] == null) {
                traversalPointer.characterLinks[linkIndex] = new LexicalNode();
            }
            
            traversalPointer = traversalPointer.characterLinks[linkIndex];
        }
        
        // Lock the terminal state to designate a complete, valid vocabulary entry
        traversalPointer.isTerminalNode = true;
    }

    public boolean containsExactWord(String searchTarget) {
        LexicalNode resolvedNode = traceLexicalPath(searchTarget);
        return resolvedNode != null && resolvedNode.isTerminalNode;
    }

    public boolean containsPrefix(String prefixTarget) {
        LexicalNode resolvedNode = traceLexicalPath(prefixTarget);
        return resolvedNode != null;
    }

    private LexicalNode traceLexicalPath(String sequence) {
        if (sequence == null || sequence.isEmpty()) {
            return null;
        }

        LexicalNode traversalPointer = structuralRoot;

        for (char currentCharacter : sequence.toLowerCase().toCharArray()) {
            int linkIndex = currentCharacter - 'a';
            
            if (linkIndex < 0 || linkIndex >= 26 || traversalPointer.characterLinks[linkIndex] == null) {
                return null; // The physical routing path terminates prematurely
            }
            
            traversalPointer = traversalPointer.characterLinks[linkIndex];
        }

        return traversalPointer;
    }

    public static void main(String[] args) {
        LexicalTrie autocompleteEngine = new LexicalTrie();

        autocompleteEngine.insertVocabulary("apple");
        autocompleteEngine.insertVocabulary("application");
        autocompleteEngine.insertVocabulary("aptitude");
        autocompleteEngine.insertVocabulary("bat");

        System.out.println("Exact Match 'apple': " + autocompleteEngine.containsExactWord("apple")); // Expected: true
        System.out.println("Exact Match 'app': " + autocompleteEngine.containsExactWord("app")); // Expected: false
        
        System.out.println("Prefix Match 'app': " + autocompleteEngine.containsPrefix("app")); // Expected: true
        System.out.println("Prefix Match 'apt': " + autocompleteEngine.containsPrefix("apt")); // Expected: true
        System.out.println("Prefix Match 'cat': " + autocompleteEngine.containsPrefix("cat")); // Expected: false
    }
}