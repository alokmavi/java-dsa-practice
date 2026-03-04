package tree;

public class BinaryTreeBasics {

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

    public static void traversePreorder(TreeNode currentNode) {
        if (currentNode == null) {
            return; 
        }

        // 1. Process the current node (Root)
        System.out.print(currentNode.nodeValue + " ");

        // 2. Recursively traverse the entire left subtree
        traversePreorder(currentNode.leftReference);

        // 3. Recursively traverse the entire right subtree
        traversePreorder(currentNode.rightReference);
    }

    public static void main(String[] args) {
        // Constructing the Root
        TreeNode treeRoot = new TreeNode(10);

        // Constructing Level 1
        treeRoot.leftReference = new TreeNode(20);
        treeRoot.rightReference = new TreeNode(30);

        // Constructing Level 2
        treeRoot.leftReference.leftReference = new TreeNode(40);
        treeRoot.leftReference.rightReference = new TreeNode(50);
        treeRoot.rightReference.rightReference = new TreeNode(60);

        /*
         * Visualized Architecture:
         * 10
         * /    \
         * 20      30
         * /  \       \
         * 40    50      60
         */

        System.out.print("Preorder Traversal: ");
        traversePreorder(treeRoot); // Expected: 10 20 40 50 30 60
        System.out.println();
    }
}