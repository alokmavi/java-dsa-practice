package tree;

public class ValidateBST {

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

    public static boolean isStrictlyValid(TreeNode treeRoot) {
        // Enforce boundaries using 64-bit integers to prevent 32-bit overflow boundary collisions
        return validateBoundaries(treeRoot, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validateBoundaries(TreeNode currentNode, long lowerBoundLimit, long upperBoundLimit) {
        if (currentNode == null) {
            return true;
        }

        if (currentNode.nodeValue <= lowerBoundLimit || currentNode.nodeValue >= upperBoundLimit) {
            return false;
        }

        // Left descent: The current node's value restricts the maximum allowed value below it
        boolean isLeftValid = validateBoundaries(currentNode.leftReference, lowerBoundLimit, currentNode.nodeValue);
        
        // Right descent: The current node's value restricts the minimum allowed value below it
        boolean isRightValid = validateBoundaries(currentNode.rightReference, currentNode.nodeValue, upperBoundLimit);

        return isLeftValid && isRightValid;
    }

    public static void main(String[] args) {
        // Constructing a mathematically valid BST
        TreeNode validRoot = new TreeNode(50);
        validRoot.leftReference = new TreeNode(30);
        validRoot.rightReference = new TreeNode(70);
        validRoot.leftReference.leftReference = new TreeNode(20);
        validRoot.leftReference.rightReference = new TreeNode(40);

        System.out.println("Tree 1 is Valid BST: " + isStrictlyValid(validRoot)); // Expected: true

        // Constructing a corrupted BST (Local vs Global Trap)
        TreeNode corruptedRoot = new TreeNode(50);
        corruptedRoot.leftReference = new TreeNode(30);
        corruptedRoot.rightReference = new TreeNode(70);
        corruptedRoot.leftReference.leftReference = new TreeNode(20);
        
        // This node is locally valid (60 > 30), but globally invalid (60 > 50 root)
        corruptedRoot.leftReference.rightReference = new TreeNode(60); 

        /*
         * Architecture:
         * 50
         * /  \
         * 30    70
         * /  \
         * 20   [60] -> VIOLATION: Exceeds absolute upper bound of 50 for the left branch
         */

        System.out.println("Tree 2 is Valid BST: " + isStrictlyValid(corruptedRoot)); // Expected: false
    }
}