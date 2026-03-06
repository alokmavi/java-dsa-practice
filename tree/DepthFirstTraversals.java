package tree;

public class DepthFirstTraversals {

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
        System.out.print(currentNode.nodeValue + " ");
        traversePreorder(currentNode.leftReference);
        traversePreorder(currentNode.rightReference);
    }

    public static void traverseInorder(TreeNode currentNode) {
        if (currentNode == null) {
            return;
        }
        traverseInorder(currentNode.leftReference);
        System.out.print(currentNode.nodeValue + " ");
        traverseInorder(currentNode.rightReference);
    }

    public static void traversePostorder(TreeNode currentNode) {
        if (currentNode == null) {
            return;
        }
        traversePostorder(currentNode.leftReference);
        traversePostorder(currentNode.rightReference);
        System.out.print(currentNode.nodeValue + " ");
    }

    public static void main(String[] args) {
        TreeNode treeRoot = new TreeNode(10);

        treeRoot.leftReference = new TreeNode(20);
        treeRoot.rightReference = new TreeNode(30);

        treeRoot.leftReference.leftReference = new TreeNode(40);
        treeRoot.leftReference.rightReference = new TreeNode(50);
        treeRoot.rightReference.rightReference = new TreeNode(60);

        /*
         * Architecture:
         * 10
         * /  \
         * 20    30
         * /  \     \
         * 40   50    60
         */

        System.out.print("Preorder (Root, L, R):  ");
        traversePreorder(treeRoot); // Expected: 10 20 40 50 30 60
        System.out.println();

        System.out.print("Inorder (L, Root, R):   ");
        traverseInorder(treeRoot);  // Expected: 40 20 50 10 30 60
        System.out.println();

        System.out.print("Postorder (L, R, Root): ");
        traversePostorder(treeRoot); // Expected: 40 50 20 60 30 10
        System.out.println();
    }
}
