package tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class BreadthFirstTraversal {

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

    public static void traverseLevelOrder(TreeNode treeRoot) {
        if (treeRoot == null) {
            return;
        }

        Queue<TreeNode> discoveryQueue = new ArrayDeque<>();
        discoveryQueue.offer(treeRoot);

        int currentLevelDepth = 0;

        while (!discoveryQueue.isEmpty()) {
            // Isolate the exact number of nodes present on the current structural level
            int nodesInCurrentLevel = discoveryQueue.size();
            System.out.print("Level " + currentLevelDepth + ": ");

            for (int i = 0; i < nodesInCurrentLevel; i++) {
                TreeNode activeNode = discoveryQueue.poll();
                System.out.print(activeNode.nodeValue + " ");

                if (activeNode.leftReference != null) {
                    discoveryQueue.offer(activeNode.leftReference);
                }
                
                if (activeNode.rightReference != null) {
                    discoveryQueue.offer(activeNode.rightReference);
                }
            }
            
            System.out.println();
            currentLevelDepth++;
        }
    }

    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode(10);

        rootNode.leftReference = new TreeNode(20);
        rootNode.rightReference = new TreeNode(30);

        rootNode.leftReference.leftReference = new TreeNode(40);
        rootNode.leftReference.rightReference = new TreeNode(50);
        rootNode.rightReference.rightReference = new TreeNode(60);

        /*
         * Architecture:
         * 10
         * /    \
         * 20      30
         * /  \       \
         * 40    50      60
         */

        System.out.println("Breadth-First Search Execution:");
        traverseLevelOrder(rootNode);
        /*
         * Expected Output:
         * Level 0: 10 
         * Level 1: 20 30 
         * Level 2: 40 50 60 
         */
    }
}