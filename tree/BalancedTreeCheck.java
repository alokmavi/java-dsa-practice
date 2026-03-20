package tree;

public class BalancedTreeCheck {

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

    public static boolean checkBalance(TreeNode treeRoot) {
        // If the height computation does not return the failure code (-1), the structure is balanced
        return computeHeightSafely(treeRoot) != -1;
    }

    private static int computeHeightSafely(TreeNode currentNode) {
        if (currentNode == null) {
            return 0;
        }

        int leftSubtreeHeight = computeHeightSafely(currentNode.leftReference);
        // Short-circuit: propagate structural failure upwards immediately without processing further
        if (leftSubtreeHeight == -1) {
            return -1;
        }

        int rightSubtreeHeight = computeHeightSafely(currentNode.rightReference);
        if (rightSubtreeHeight == -1) {
            return -1;
        }

        int heightDifference = Math.abs(leftSubtreeHeight - rightSubtreeHeight);
        
        // Trigger failure code if the differential exceeds the balanced threshold
        if (heightDifference > 1) {
            return -1;
        }

        // Return standard structural height to the parent caller
        return Math.max(leftSubtreeHeight, rightSubtreeHeight) + 1;
    }

    public static void main(String[] args) {
        // Constructing a Balanced Tree
        TreeNode balancedRoot = new TreeNode(10);
        
        balancedRoot.leftReference = new TreeNode(20);
        balancedRoot.rightReference = new TreeNode(30);
        
        balancedRoot.leftReference.leftReference = new TreeNode(40);
        balancedRoot.leftReference.rightReference = new TreeNode(50);

        /*
         * Architecture:
         * 10
         * /  \
         * 20    30
         * /  \
         * 40    50
         */

        System.out.println("Tree 1 is Balanced: " + checkBalance(balancedRoot)); // Expected: true

        // Constructing an Unbalanced Tree (Left-heavy)
        TreeNode unbalancedRoot = new TreeNode(10);
        
        unbalancedRoot.leftReference = new TreeNode(20);
        unbalancedRoot.rightReference = new TreeNode(30);
        
        unbalancedRoot.leftReference.leftReference = new TreeNode(40);
        unbalancedRoot.leftReference.leftReference.leftReference = new TreeNode(80);

        /*
         * Architecture:
         * 10
         * /  \
         * 20    30
         * /  
         * 40    
         * /
         * 80
         * (Node 20 has a left height of 2 and right height of 0. Differential is 2.)
         */

        System.out.println("Tree 2 is Balanced: " + checkBalance(unbalancedRoot)); // Expected: false
    }
}