package tree;

public class SymmetricTree {

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

    public static boolean isSymmetric(TreeNode treeRoot) {
        // An empty tree is inherently symmetric
        if (treeRoot == null) {
            return true;
        }
        
        // Initiate the divergent parallel traversal from the root's immediate children
        return checkMirror(treeRoot.leftReference, treeRoot.rightReference);
    }

    private static boolean checkMirror(TreeNode leftBranch, TreeNode rightBranch) {
        // Boundary check: Both branches terminated at the exact same depth
        if (leftBranch == null && rightBranch == null) {
            return true;
        }

        // Structural mismatch: One branch extends deeper than its mirror counterpart
        if (leftBranch == null || rightBranch == null) {
            return false;
        }

        // State mismatch: The mirrored nodes exist but hold different values
        if (leftBranch.nodeValue != rightBranch.nodeValue) {
            return false;
        }

        // Divergent descent: Outer edges must match, inner edges must match
        boolean isOuterMirror = checkMirror(leftBranch.leftReference, rightBranch.rightReference);
        boolean isInnerMirror = checkMirror(leftBranch.rightReference, rightBranch.leftReference);

        return isOuterMirror && isInnerMirror;
    }

    public static void main(String[] args) {
        // Constructing a Symmetric Tree
        TreeNode symmetricRoot = new TreeNode(1);
        
        symmetricRoot.leftReference = new TreeNode(2);
        symmetricRoot.rightReference = new TreeNode(2);
        
        symmetricRoot.leftReference.leftReference = new TreeNode(3);
        symmetricRoot.leftReference.rightReference = new TreeNode(4);
        
        symmetricRoot.rightReference.leftReference = new TreeNode(4);
        symmetricRoot.rightReference.rightReference = new TreeNode(3);

        /*
         * Architecture:
         * 1
         * /   \
         * 2     2
         * / \   / \
         * 3   4 4   3
         */

        System.out.println("Tree 1 is Symmetric: " + isSymmetric(symmetricRoot)); // Expected: true

        // Constructing an Asymmetric Tree
        TreeNode asymmetricRoot = new TreeNode(1);
        
        asymmetricRoot.leftReference = new TreeNode(2);
        asymmetricRoot.rightReference = new TreeNode(2);
        
        asymmetricRoot.leftReference.rightReference = new TreeNode(3);
        asymmetricRoot.rightReference.rightReference = new TreeNode(3);

        /*
         * Architecture:
         * 1
         * /   \
         * 2     2
         * \     \
         * 3     3
         * (Notice both 3s are right-children. This is a directional shift, not a mirror.)
         */

        System.out.println("Tree 2 is Symmetric: " + isSymmetric(asymmetricRoot)); // Expected: false
    }
}