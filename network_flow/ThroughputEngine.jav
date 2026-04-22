package network_flow;

import java.util.LinkedList;
import java.util.Queue;

public class ThroughputEngine {

    public static int calculateMaximumFlow(int[][] capacityMatrix, int sourceNode, int sinkNode) {
        if (capacityMatrix == null || capacityMatrix.length == 0) {
            return 0;
        }

        int totalNodes = capacityMatrix.length;

        if (sourceNode < 0 || sinkNode >= totalNodes || sourceNode == sinkNode) {
            throw new IllegalArgumentException("System requires strictly independent and valid source/sink designations.");
        }

        // Isolate state mutation: Copy immutable capacities into a volatile residual tracking matrix
        int[][] residualGraph = new int[totalNodes][totalNodes];
        for (int i = 0; i < totalNodes; i++) {
            System.arraycopy(capacityMatrix[i], 0, residualGraph[i], 0, totalNodes);
        }

        int systemMaximumFlow = 0;
        int[] parentTrace = new int[totalNodes];

        while (executeAugmentingPathSearch(residualGraph, sourceNode, sinkNode, parentTrace)) {
            int pathBottleneck = Integer.MAX_VALUE;

            // Traverse the discovered path backward to identify the strict capacity constraint
            int backtrackPointer = sinkNode;
            while (backtrackPointer != sourceNode) {
                int predecessor = parentTrace[backtrackPointer];
                pathBottleneck = Math.min(pathBottleneck, residualGraph[predecessor][backtrackPointer]);
                backtrackPointer = predecessor;
            }

            // Execute the flow mutation across the residual graph
            backtrackPointer = sinkNode;
            while (backtrackPointer != sourceNode) {
                int predecessor = parentTrace[backtrackPointer];
                
                residualGraph[predecessor][backtrackPointer] -= pathBottleneck;
                
                // Reverse edge allocation prevents dead-end structural locks by allowing future paths to undo sub-optimal routing
                residualGraph[backtrackPointer][predecessor] += pathBottleneck; 
                
                backtrackPointer = predecessor;
            }

            systemMaximumFlow += pathBottleneck;
        }

        return systemMaximumFlow;
    }

    private static boolean executeAugmentingPathSearch(int[][] residualGraph, int sourceNode, int sinkNode, int[] parentTrace) {
        int totalNodes = residualGraph.length;
        boolean[] visitedState = new boolean[totalNodes];
        Queue<Integer> traversalQueue = new LinkedList<>();

        traversalQueue.add(sourceNode);
        visitedState[sourceNode] = true;
        parentTrace[sourceNode] = -1;

        while (!traversalQueue.isEmpty()) {
            int currentNode = traversalQueue.poll();

            for (int targetNode = 0; targetNode < totalNodes; targetNode++) {
                // Structural requirement: The path must be unvisited and contain available positive capacity
                if (!visitedState[targetNode] && residualGraph[currentNode][targetNode] > 0) {
                    
                    if (targetNode == sinkNode) {
                        parentTrace[targetNode] = currentNode;
                        return true;
                    }
                    
                    traversalQueue.add(targetNode);
                    visitedState[targetNode] = true;
                    parentTrace[targetNode] = currentNode;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        /*
         * Network Topology Definition (6 Nodes: 0 to 5)
         * Source: Node 0
         * Sink: Node 5
         */
        int[][] networkTopography = new int[][] {
            {0, 16, 13, 0, 0, 0}, 
            {0, 0, 10, 12, 0, 0}, 
            {0, 4, 0, 0, 14, 0},  
            {0, 0, 9, 0, 0, 20},  
            {0, 0, 0, 7, 0, 4},   
            {0, 0, 0, 0, 0, 0}    
        };

        int sourceIndex = 0;
        int sinkIndex = 5;

        System.out.println("Maximum absolute throughput for the defined topography: " 
            + calculateMaximumFlow(networkTopography, sourceIndex, sinkIndex));
        // Expected: 23
    }
}