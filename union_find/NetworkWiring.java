package union_find;

import java.util.Arrays;
import java.util.Comparator;

public class NetworkWiring {

    static class DisjointSet {
        private final int[] parentRegistry;
        private final int[] clusterSize;

        public DisjointSet(int totalNodes) {
            this.parentRegistry = new int[totalNodes + 1];
            this.clusterSize = new int[totalNodes + 1];
            for (int i = 0; i <= totalNodes; i++) {
                parentRegistry[i] = i;
                clusterSize[i] = 1;
            }
        }

        public int findAbsoluteRoot(int nodeId) {
            if (parentRegistry[nodeId] != nodeId) {
                parentRegistry[nodeId] = findAbsoluteRoot(parentRegistry[nodeId]);
            }
            return parentRegistry[nodeId];
        }

        public boolean establishConnection(int nodeAlpha, int nodeBeta) {
            int rootAlpha = findAbsoluteRoot(nodeAlpha);
            int rootBeta = findAbsoluteRoot(nodeBeta);

            if (rootAlpha == rootBeta) {
                return false; 
            }

            if (clusterSize[rootAlpha] >= clusterSize[rootBeta]) {
                parentRegistry[rootBeta] = rootAlpha;
                clusterSize[rootAlpha] += clusterSize[rootBeta];
            } else {
                parentRegistry[rootAlpha] = rootBeta;
                clusterSize[rootBeta] += clusterSize[rootAlpha];
            }

            return true;
        }
    }

    public static int calculateMinimumWiringCost(int serverCount, int[][] availableCables) {
        if (serverCount <= 1) {
            return 0;
        }
        if (availableCables == null || availableCables.length == 0) {
            throw new IllegalArgumentException("Cable inventory cannot be null or empty.");
        }

        // Greedy Phase: Sort all available connections strictly by installation cost
        Arrays.sort(availableCables, Comparator.comparingInt(cable -> cable[2]));

        DisjointSet networkState = new DisjointSet(serverCount);
        int totalInstallationCost = 0;
        int successfulConnections = 0;

        for (int[] cable : availableCables) {
            int serverAlpha = cable[0];
            int serverBeta = cable[1];
            int installationCost = cable[2];

            // If the connection does not form a cyclical loop, commit the installation
            if (networkState.establishConnection(serverAlpha, serverBeta)) {
                totalInstallationCost += installationCost;
                successfulConnections++;

                // Optimization: A valid Minimum Spanning Tree strictly requires exactly (V - 1) edges
                if (successfulConnections == serverCount - 1) {
                    return totalInstallationCost;
                }
            }
        }

        throw new IllegalStateException("Available cables are insufficient to connect all servers into a unified network.");
    }

    public static void main(String[] args) {
        int targetServersAlpha = 4;
        /*
         * Cable Layout: [Server A, Server B, Cost]
         * 1-2 (Cost 1)
         * 1-3 (Cost 4)
         * 1-4 (Cost 3)
         * 2-4 (Cost 2)
         * 3-4 (Cost 5)
         */
        int[][] cableInventoryAlpha = {
            {1, 2, 1}, {1, 3, 4}, {1, 4, 3}, {2, 4, 2}, {3, 4, 5}
        };
        
        System.out.println("Minimum cost for Data Center Alpha: " + calculateMinimumWiringCost(targetServersAlpha, cableInventoryAlpha)); 
        // Expected: 7 
        // (Connect 1-2 for 1, connect 2-4 for 2, connect 1-4? CYCLE REJECTED, connect 1-3 for 4. Total: 1+2+4=7)
    }
}