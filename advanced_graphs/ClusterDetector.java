package advanced_graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ClusterDetector {

    private final int networkSize;
    private final List<List<Integer>> forwardNetwork;
    private final List<List<Integer>> transposedNetwork;

    public ClusterDetector(int totalNodes) {
        if (totalNodes <= 0) {
            throw new IllegalArgumentException("Network topology requires a strictly positive node count.");
        }
        
        this.networkSize = totalNodes;
        this.forwardNetwork = new ArrayList<>(totalNodes);
        this.transposedNetwork = new ArrayList<>(totalNodes);

        for (int i = 0; i < totalNodes; i++) {
            forwardNetwork.add(new ArrayList<>());
            transposedNetwork.add(new ArrayList<>());
        }
    }

    public void registerDirectedConnection(int sourceNode, int destinationNode) {
        if (sourceNode < 0 || sourceNode >= networkSize || destinationNode < 0 || destinationNode >= networkSize) {
            throw new IndexOutOfBoundsException("Connection parameters exceed valid network topology boundaries.");
        }
        
        // Ingest the architecture bidirectionally to pre-compile Phase 2 (Transposition) requirements
        forwardNetwork.get(sourceNode).add(destinationNode);
        transposedNetwork.get(destinationNode).add(sourceNode);
    }

    public List<List<Integer>> extractIsolatedClusters() {
        boolean[] traversalState = new boolean[networkSize];
        Deque<Integer> executionHierarchy = new ArrayDeque<>();

        // Phase 1: Establish topological finish-time hierarchy via Forward DFS
        for (int nodeId = 0; nodeId < networkSize; nodeId++) {
            if (!traversalState[nodeId]) {
                compileFinishTimes(nodeId, traversalState, executionHierarchy);
            }
        }

        // Reset the memory array for the transposed traversal
        traversalState = new boolean[networkSize];
        List<List<Integer>> isolatedClusters = new ArrayList<>();

        // Phase 3: Execute cluster isolation via Transposed DFS following the hierarchical stack
        while (!executionHierarchy.isEmpty()) {
            int targetNode = executionHierarchy.pop();

            if (!traversalState[targetNode]) {
                List<Integer> distinctCluster = new ArrayList<>();
                isolateComponent(targetNode, traversalState, distinctCluster);
                isolatedClusters.add(distinctCluster);
            }
        }

        return isolatedClusters;
    }

    private void compileFinishTimes(int currentNode, boolean[] traversalState, Deque<Integer> executionHierarchy) {
        traversalState[currentNode] = true;

        for (int adjacentNode : forwardNetwork.get(currentNode)) {
            if (!traversalState[adjacentNode]) {
                compileFinishTimes(adjacentNode, traversalState, executionHierarchy);
            }
        }

        // Structural constraint: Node is pushed only after all downstream dependencies are fully resolved
        executionHierarchy.push(currentNode);
    }

    private void isolateComponent(int currentNode, boolean[] traversalState, List<Integer> activeCluster) {
        traversalState[currentNode] = true;
        activeCluster.add(currentNode);

        // Core transition: Traversing the transposed network traps the search within the component boundaries
        for (int adjacentNode : transposedNetwork.get(currentNode)) {
            if (!traversalState[adjacentNode]) {
                isolateComponent(adjacentNode, traversalState, activeCluster);
            }
        }
    }

    public static void main(String[] args) {
        ClusterDetector serviceAuditor = new ClusterDetector(5);

        // Cluster Alpha: 0 -> 2 -> 1 -> 0
        serviceAuditor.registerDirectedConnection(0, 2);
        serviceAuditor.registerDirectedConnection(2, 1);
        serviceAuditor.registerDirectedConnection(1, 0);

        // Bridge connection to a separate operational domain
        serviceAuditor.registerDirectedConnection(0, 3);

        // Cluster Beta: 3 -> 4 -> 3
        serviceAuditor.registerDirectedConnection(3, 4);
        serviceAuditor.registerDirectedConnection(4, 3);

        List<List<Integer>> detectedClusters = serviceAuditor.extractIsolatedClusters();

        System.out.println("System Audit Complete. Isolated microservice clusters detected:");
        for (int i = 0; i < detectedClusters.size(); i++) {
            System.out.println("Cluster ID " + i + ": " + detectedClusters.get(i));
        }
        /*
         * Expected Structural Output:
         * Cluster ID 0: [0, 1, 2] (Order may vary internally)
         * Cluster ID 1: [3, 4]
         */
    }
}