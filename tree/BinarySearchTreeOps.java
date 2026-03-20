package tree;
public class BinarySearchTreeOps {

    static class TreeNode {
        int nodeValue;
        TreeNode leftReference;
        TreeNode rightReference;

        TreeNode(int nodeValue) {
            this.nodeValue = nodeValue;
            this.leftReference = null;
            this.rightReference = null;
        }
    }

    public static TreeNode insertNode(TreeNode currentNode, int newKey) {
        if (currentNode == null) {
            return new TreeNode(newKey);
        }

        if (newKey < currentNode.nodeValue) {
            currentNode.leftReference = insertNode(currentNode.leftReference, newKey);
        } else if (newKey > currentNode.nodeValue) {
            currentNode.rightReference = insertNode(currentNode.rightReference, newKey);
        }

        // Returns the unchanged node pointer to re-link the call stack
        return currentNode;
    }

    public static boolean containsTarget(TreeNode treeRoot, int targetKey) {
        TreeNode traversalNode = treeRoot;

        // Iterative traversal bypasses call stack memory allocation for pure read operations
        while (traversalNode != null) {
            if (targetKey == traversalNode.nodeValue) {
                return true;
            } else if (targetKey < traversalNode.nodeValue) {
                traversalNode = traversalNode.leftReference;
            } else {
                traversalNode = traversalNode.rightReference;
            }
        }

        return false;
    }

    public static void traverseInorder(TreeNode currentNode) {
        if (currentNode == null) return;
        traverseInorder(currentNode.leftReference);
        System.out.print(currentNode.nodeValue + " ");
        traverseInorder(currentNode.rightReference);
    }

    public static void main(String[] args) {
        TreeNode bstRoot = null;
        
        // Constructing the BST
        int[] insertionStream = {50, 30, 70, 20, 40, 60, 80};
        for (int initialValue : insertionStream) {
            bstRoot = insertNode(bstRoot, initialValue);
        }

        /*
         * Architecture:
         * 50
         * /    \
         * 30      70
         * /  \    /  \
         * 20   40  60   80
         */

        System.out.print("Inorder Traversal (Should be sorted): ");
        traverseInorder(bstRoot);
        System.out.println();

        int searchTargetOne = 60;
        System.out.println("Contains " + searchTargetOne + ": " + containsTarget(bstRoot, searchTargetOne)); // Expected: true

        int searchTargetTwo = 99;
        System.out.println("Contains " + searchTargetTwo + ": " + containsTarget(bstRoot, searchTargetTwo)); // Expected: false
    }
}