package tree;

public class LowestCommonAncestorBST {

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

    public static TreeNode findConvergenceNode(TreeNode treeRoot, TreeNode targetAlpha, TreeNode targetBeta) {
        if (treeRoot == null || targetAlpha == null || targetBeta == null) {
            return null; 
        }

        TreeNode traversalNode = treeRoot;

        while (traversalNode != null) {
            // Both targets reside in the right hierarchical memory space
            if (targetAlpha.nodeValue > traversalNode.nodeValue && targetBeta.nodeValue > traversalNode.nodeValue) {
                traversalNode = traversalNode.rightReference;
            } 
            // Both targets reside in the left hierarchical memory space
            else if (targetAlpha.nodeValue < traversalNode.nodeValue && targetBeta.nodeValue < traversalNode.nodeValue) {
                traversalNode = traversalNode.leftReference;
            } 
            // The routing paths diverge, or the traversal pointer has landed directly on a target
            else {
                return traversalNode;
            }
        }

        return null; 
    }

    public static void main(String[] args) {
        TreeNode bstRoot = new TreeNode(20);
        
        TreeNode node8 = new TreeNode(8);
        TreeNode node22 = new TreeNode(22);
        bstRoot.leftReference = node8;
        bstRoot.rightReference = node22;
        
        TreeNode node4 = new TreeNode(4);
        TreeNode node12 = new TreeNode(12);
        node8.leftReference = node4;
        node8.rightReference = node12;
        
        TreeNode node10 = new TreeNode(10);
        TreeNode node14 = new TreeNode(14);
        node12.leftReference = node10;
        node12.rightReference = node14;

        /*
         * Architecture:
         * 20
         * /    \
         * 8      22
         * /  \    
         * 4   12  
         * /  \
         * 10  14
         */

        TreeNode ancestorOne = findConvergenceNode(bstRoot, node10, node14);
        System.out.println("LCA of 10 and 14: " + (ancestorOne != null ? ancestorOne.nodeValue : "null")); // Expected: 12

        TreeNode ancestorTwo = findConvergenceNode(bstRoot, node14, node8);
        System.out.println("LCA of 14 and 8: " + (ancestorTwo != null ? ancestorTwo.nodeValue : "null")); // Expected: 8

        TreeNode ancestorThree = findConvergenceNode(bstRoot, node10, node22);
        System.out.println("LCA of 10 and 22: " + (ancestorThree != null ? ancestorThree.nodeValue : "null")); // Expected: 20
    }
}