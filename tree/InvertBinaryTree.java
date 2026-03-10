package tree;

public class InvertBinaryTree {

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

    public static TreeNode invertStructure(TreeNode currentNode) {
        if (currentNode == null) {
            return null;
        }

        // Isolate the original memory references before structural mutation
        TreeNode originalLeft = currentNode.leftReference;
        TreeNode originalRight = currentNode.rightReference;

        // Recursively command the subtrees to invert themselves, 
        // then assign them to the opposite reference pointers
        currentNode.leftReference = invertStructure(originalRight);
        currentNode.rightReference = invertStructure(originalLeft);

        return currentNode;
    }

    public static void printLevelOrder(TreeNode treeRoot) {
        if (treeRoot == null) return;
        
        java.util.Queue<TreeNode> discoveryQueue = new java.util.ArrayDeque<>();
        discoveryQueue.offer(treeRoot);

        while (!discoveryQueue.isEmpty()) {
            int nodesInCurrentLevel = discoveryQueue.size();
            for (int i = 0; i < nodesInCurrentLevel; i++) {
                TreeNode activeNode = discoveryQueue.poll();
                System.out.print(activeNode.nodeValue + " ");

                if (activeNode.leftReference != null) discoveryQueue.offer(activeNode.leftReference);
                if (activeNode.rightReference != null) discoveryQueue.offer(activeNode.rightReference);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode(4);
        
        rootNode.leftReference = new TreeNode(2);
        rootNode.rightReference = new TreeNode(7);
        
        rootNode.leftReference.leftReference = new TreeNode(1);
        rootNode.leftReference.rightReference = new TreeNode(3);
        
        rootNode.rightReference.leftReference = new TreeNode(6);
        rootNode.rightReference.rightReference = new TreeNode(9);

        /*
         * Original Architecture:
         * 4
         * /   \
         * 2     7
         * / \   / \
         * 1   3 6   9
         */

        System.out.println("Original Structure (Level Order):");
        printLevelOrder(rootNode);

        TreeNode invertedRoot = invertStructure(rootNode);

        System.out.println("\nInverted Structure (Level Order):");
        printLevelOrder(invertedRoot); 
        
        /*
         * Expected Architecture:
         * 4
         * /   \
         * 7     2
         * / \   / \
         * 9   6 3   1
         */
    }
}