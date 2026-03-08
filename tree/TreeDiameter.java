package tree;

public class TreeDiameter {

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

    public static int computeDiameter(TreeNode treeRoot) {
        if (treeRoot == null) return 0;
        
        // Array used to simulate pass-by-reference for global state tracking across recursive frames
        int[] maxDiameterTracker = new int[1]; 
        
        computeHeightAndTrackDiameter(treeRoot, maxDiameterTracker);
        
        return maxDiameterTracker[0];
    }

    private static int computeHeightAndTrackDiameter(TreeNode currentNode, int[] diameterTracker) {
        if (currentNode == null) {
            return 0;
        }

        int leftSubtreeHeight = computeHeightAndTrackDiameter(currentNode.leftReference, diameterTracker);
        int rightSubtreeHeight = computeHeightAndTrackDiameter(currentNode.rightReference, diameterTracker);

        int localDiameter = leftSubtreeHeight + rightSubtreeHeight;

        // Update global maximum if the path routing through currentNode is the longest found so far
        diameterTracker[0] = Math.max(diameterTracker[0], localDiameter);

        // Return the structural height to the parent caller
        return Math.max(leftSubtreeHeight, rightSubtreeHeight) + 1;
    }

    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode(10);
        
        rootNode.leftReference = new TreeNode(20);
        rootNode.rightReference = new TreeNode(30);
        
        rootNode.leftReference.leftReference = new TreeNode(40);
        rootNode.leftReference.rightReference = new TreeNode(50);
        
        // Extending the left subtree to force the diameter to NOT pass through the root (10)
        rootNode.leftReference.leftReference.leftReference = new TreeNode(80);
        rootNode.leftReference.rightReference.rightReference = new TreeNode(90);

        /*
         * Architecture:
         * 10
         * /  \
         * 20    30
         * /  \
         * 40    50
         * /      \
         * 80      90
         * * Longest path is 80 -> 40 -> 20 -> 50 -> 90. (4 edges)
         * It never touches 10 or 30.
         */

        int treeDiameter = computeDiameter(rootNode);
        System.out.println("Maximum Tree Diameter: " + treeDiameter); // Expected: 4
    }
}