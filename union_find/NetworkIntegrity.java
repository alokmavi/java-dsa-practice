package union_find;

import java.util.Arrays;

public class NetworkIntegrity {

    static class DisjointSet {
        private final int[] parentRegistry;
        private final int[] networkSize;

        public DisjointSet(int totalNodes) {
            // We allocate totalNodes + 1 to support 1-indexed node IDs naturally
            this.parentRegistry = new int[totalNodes + 1];
            this.networkSize = new int[totalNodes + 1];

            // Initialization: Every node is its own isolated absolute root
            for (int i = 0; i <= totalNodes; i++) {
                parentRegistry[i] = i;
                networkSize[i] = 1;
            }
        }

        public int findAbsoluteRoot(int nodeId) {
            // Path Compression: Recursively flatten the hierarchy by pointing directly to the discovered root
            if (parentRegistry[nodeId] != nodeId) {
                parentRegistry[nodeId] = findAbsoluteRoot(parentRegistry[nodeId]);
            }
            return parentRegistry[nodeId];
        }

        public boolean establishConnection(int nodeAlpha, int nodeBeta) {
            int rootAlpha = findAbsoluteRoot(nodeAlpha);
            int rootBeta = findAbsoluteRoot(nodeBeta);

            // Cycle Confirmed: Both nodes already share an absolute root. The connection is redundant.
            if (rootAlpha == rootBeta) {
                return false; 
            }

            // Union by Size: Maintain shallow tree depth by subsuming the smaller network into the larger one
            if (networkSize[rootAlpha] >= networkSize[rootBeta]) {
                parentRegistry[rootBeta] = rootAlpha;
                networkSize[rootAlpha] += networkSize[rootBeta];
            } else {
                parentRegistry[rootAlpha] = rootBeta;
                networkSize[rootBeta] += networkSize[rootAlpha];
            }

            return true;
        }
    }

    public static int[] detectRedundantCable(int[][] networkTopology) {
        if (networkTopology == null || networkTopology.length == 0) {
            throw new IllegalArgumentException("System requires a valid network topology array.");
        }

        // In a tree with one redundant edge, the number of edges equals the number of nodes
        int totalNodes = networkTopology.length;
        DisjointSet clusterState = new DisjointSet(totalNodes);

        for (int[] connection : networkTopology) {
            int nodeAlpha = connection[0];
            int nodeBeta = connection[1];

            // The moment establishConnection returns false, we have isolated the redundant link
            if (!clusterState.establishConnection(nodeAlpha, nodeBeta)) {
                return connection;
            }
        }

        throw new IllegalStateException("Structural integrity verified. No redundant cables detected.");
    }

    public static void main(String[] args) {
        // Architecture Alpha: 1-2, 1-3, 2-3 (The 2-3 cable closes the loop)
        int[][] topologyAlpha = {{1, 2}, {1, 3}, {2, 3}};
        int[] redundantAlpha = detectRedundantCable(topologyAlpha);
        System.out.println("Redundant connection in Topology Alpha: [" + redundantAlpha[0] + ", " + redundantAlpha[1] + "]"); 
        // Expected: [2, 3]

        // Architecture Beta: 1-2, 2-3, 3-4, 1-4, 1-5 (The 1-4 cable closes the 1-2-3-4 loop)
        int[][] topologyBeta = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        int[] redundantBeta = detectRedundantCable(topologyBeta);
        System.out.println("Redundant connection in Topology Beta: [" + redundantBeta[0] + ", " + redundantBeta[1] + "]"); 
        // Expected: [1, 4]
    }
}