package tree;

public class BSTDeletion {

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

    public static TreeNode removeTarget(TreeNode currentNode, int targetKey) {
        if (currentNode == null) {
            return null;
        }

        // 1. Traverse to locate the target
        if (targetKey < currentNode.nodeValue) {
            currentNode.leftReference = removeTarget(currentNode.leftReference, targetKey);
        } else if (targetKey > currentNode.nodeValue) {
            currentNode.rightReference = removeTarget(currentNode.rightReference, targetKey);
        } else {
            // 2. Target located. Handle structural realities.

            // Case 1 & 2: Node has zero or one child
            if (currentNode.leftReference == null) {
                return currentNode.rightReference;
            } else if (currentNode.rightReference == null) {
                return currentNode.leftReference;
            }

            // Case 3: Node has two children.
            // Retrieve the smallest value from the right subtree (Inorder Successor)
            TreeNode inorderSuccessor = locateMinimumNode(currentNode.rightReference);

            // Structural value replacement to preserve complex subtree routing
            currentNode.nodeValue = inorderSuccessor.nodeValue;

            // Recursively prune the duplicated successor node from the right subtree
            currentNode.rightReference = removeTarget(currentNode.rightReference, inorderSuccessor.nodeValue);
        }

        return currentNode;
    }

    private static TreeNode locateMinimumNode(TreeNode subtreeRoot) {
        TreeNode traversalNode = subtreeRoot;
        while (traversalNode.leftReference != null) {
            traversalNode = traversalNode.leftReference;
        }
        return traversalNode;
    }

    public static void traverseInorder(TreeNode currentNode) {
        if (currentNode == null) return;
        traverseInorder(currentNode.leftReference);
        System.out.print(currentNode.nodeValue + " ");
        traverseInorder(currentNode.rightReference);
    }

    public static TreeNode insertNode(TreeNode currentNode, int newKey) {
        if (currentNode == null) return new TreeNode(newKey);
        if (newKey < currentNode.nodeValue) currentNode.leftReference = insertNode(currentNode.leftReference, newKey);
        else if (newKey > currentNode.nodeValue) currentNode.rightReference = insertNode(currentNode.rightReference, newKey);
        return currentNode;
    }

    public static void main(String[] args) {
        TreeNode bstRoot = null;
        int[] insertionStream = {50, 30, 70, 20, 40, 60, 80};
        for (int initialValue : insertionStream) {
            bstRoot = insertNode(bstRoot, initialValue);
        }

        System.out.print("Initial Architecture (Inorder): ");
        traverseInorder(bstRoot); // Expected: 20 30 40 50 60 70 80
        System.out.println();

        // Testing Case 1: Leaf Deletion
        bstRoot = removeTarget(bstRoot, 20);
        System.out.print("After deleting 20 (Leaf):       ");
        traverseInorder(bstRoot); // Expected: 30 40 50 60 70 80
        System.out.println();

        // Testing Case 2: One Child Deletion
        // (Force 30 to have only one child by removing 20 first, which we just did)
        bstRoot = removeTarget(bstRoot, 30);
        System.out.print("After deleting 30 (One Child):  ");
        traverseInorder(bstRoot); // Expected: 40 50 60 70 80
        System.out.println();

        // Testing Case 3: Two Children Deletion (The Root)
        bstRoot = removeTarget(bstRoot, 50);
        System.out.print("After deleting 50 (Root):       ");
        traverseInorder(bstRoot); // Expected: 40 60 70 80
        System.out.println();
    }
}