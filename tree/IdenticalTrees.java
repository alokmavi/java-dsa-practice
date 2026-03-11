package tree;

public class IdenticalTrees {

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

    public static boolean checkEquivalence(TreeNode treeAlpha, TreeNode treeBeta) {
        // Structural boundary check: both paths terminated simultaneously
        if (treeAlpha == null && treeBeta == null) {
            return true;
        }

        // Structural mismatch check: one tree has a node where the other has null
        if (treeAlpha == null || treeBeta == null) {
            return false;
        }

        // State mismatch check
        if (treeAlpha.nodeValue != treeBeta.nodeValue) {
            return false;
        }

        // Synchronized descent commanding both pointers to move in parallel
        boolean isLeftEquivalent = checkEquivalence(treeAlpha.leftReference, treeBeta.leftReference);
        boolean isRightEquivalent = checkEquivalence(treeAlpha.rightReference, treeBeta.rightReference);

        return isLeftEquivalent && isRightEquivalent;
    }

    public static void main(String[] args) {
        // Constructing Tree Alpha
        TreeNode alphaRoot = new TreeNode(1);
        alphaRoot.leftReference = new TreeNode(2);
        alphaRoot.rightReference = new TreeNode(3);
        alphaRoot.leftReference.leftReference = new TreeNode(4);

        // Constructing Tree Beta (Identical to Alpha)
        TreeNode betaRoot = new TreeNode(1);
        betaRoot.leftReference = new TreeNode(2);
        betaRoot.rightReference = new TreeNode(3);
        betaRoot.leftReference.leftReference = new TreeNode(4);

        // Constructing Tree Gamma (Structural mismatch)
        TreeNode gammaRoot = new TreeNode(1);
        gammaRoot.leftReference = new TreeNode(2);
        gammaRoot.rightReference = new TreeNode(3);
        gammaRoot.rightReference.rightReference = new TreeNode(4); 

        // Constructing Tree Delta (Value mismatch)
        TreeNode deltaRoot = new TreeNode(1);
        deltaRoot.leftReference = new TreeNode(99); 
        deltaRoot.rightReference = new TreeNode(3);
        deltaRoot.leftReference.leftReference = new TreeNode(4);

        System.out.println("Alpha vs Beta (Identical): " + checkEquivalence(alphaRoot, betaRoot));   // Expected: true
        System.out.println("Alpha vs Gamma (Structure Diff): " + checkEquivalence(alphaRoot, gammaRoot)); // Expected: false
        System.out.println("Alpha vs Delta (Value Diff): " + checkEquivalence(alphaRoot, deltaRoot));     // Expected: false
    }
}