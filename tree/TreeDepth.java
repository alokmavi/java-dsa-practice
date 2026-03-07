package tree;

public class TreeDepth {

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

    public static int calculateMaxDepth(TreeNode currentNode) {
        // Base case: The foundation of the tree. A non-existent node contributes 0 to the depth.
        if (currentNode == null) {
            return 0;
        }

        // 1. Resolve the left branch entirely
        int leftSubtreeDepth = calculateMaxDepth(currentNode.leftReference);
        
        // 2. Resolve the right branch entirely
        int rightSubtreeDepth = calculateMaxDepth(currentNode.rightReference);

        // 3. Process the current node by taking the maximum of the branches and adding itself (+1)
        return Math.max(leftSubtreeDepth, rightSubtreeDepth) + 1;
    }

    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode(10);

        rootNode.leftReference = new TreeNode(20);
        rootNode.rightReference = new TreeNode(30);

        rootNode.leftReference.leftReference = new TreeNode(40);
        rootNode.leftReference.rightReference = new TreeNode(50);
        
        // Creating an imbalance to test the max logic
        rootNode.leftReference.leftReference.leftReference = new TreeNode(80);

        /*
         * Architecture:
         * 10
         * /  \
         * 20    30
         * /  \
         * 40    50
         * /
         * 80
         */

        int maxDepth = calculateMaxDepth(rootNode);
        System.out.println("Maximum Tree Depth: " + maxDepth); // Expected: 4
    }
}