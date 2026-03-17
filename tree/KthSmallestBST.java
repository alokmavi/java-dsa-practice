package tree;

public class KthSmallestBST {

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

    public static int locateKthSmallest(TreeNode bstRoot, int targetRank) {
        if (bstRoot == null || targetRank <= 0) {
            throw new IllegalArgumentException("Invalid tree root or target rank.");
        }

        // State encapsulation: index 0 tracks remaining rank, index 1 stores the located value
        int[] traversalState = new int[]{targetRank, -1};
        
        executeInorderSearch(bstRoot, traversalState);
        
        return traversalState[1];
    }

    private static void executeInorderSearch(TreeNode currentNode, int[] traversalState) {
        // Short-circuit execution if the target has already been located in a previous recursive frame
        if (currentNode == null || traversalState[0] <= 0) {
            return;
        }

        executeInorderSearch(currentNode.leftReference, traversalState);

        traversalState[0]--;
        
        if (traversalState[0] == 0) {
            traversalState[1] = currentNode.nodeValue;
            return; 
        }

        executeInorderSearch(currentNode.rightReference, traversalState);
    }

    public static void main(String[] args) {
        TreeNode bstRoot = new TreeNode(50);
        bstRoot.leftReference = new TreeNode(30);
        bstRoot.rightReference = new TreeNode(70);
        bstRoot.leftReference.leftReference = new TreeNode(20);
        bstRoot.leftReference.rightReference = new TreeNode(40);
        bstRoot.rightReference.leftReference = new TreeNode(60);
        bstRoot.rightReference.rightReference = new TreeNode(80);

        /*
         * Architecture:
         * 50
         * /    \
         * 30      70
         * /  \    /  \
         * 20   40  60   80
         * * Sorted Order: 20, 30, 40, 50, 60, 70, 80
         */

        int rankOne = 1;
        System.out.println("1st Smallest: " + locateKthSmallest(bstRoot, rankOne)); // Expected: 20

        int rankThree = 3;
        System.out.println("3rd Smallest: " + locateKthSmallest(bstRoot, rankThree)); // Expected: 40

        int rankSix = 6;
        System.out.println("6th Smallest: " + locateKthSmallest(bstRoot, rankSix)); // Expected: 70
    }
}