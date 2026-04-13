package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraShortestPath {

    static class NetworkEdge {
        final int destinationVertex;
        final int travelCost;

        NetworkEdge(int destinationVertex, int travelCost) {
            this.destinationVertex = destinationVertex;
            this.travelCost = travelCost;
        }
    }

    static class RouteCost {
        final int vertexId;
        final int cumulativeCost;

        RouteCost(int vertexId, int cumulativeCost) {
            this.vertexId = vertexId;
            this.cumulativeCost = cumulativeCost;
        }
    }

    private final Map<Integer, List<NetworkEdge>> routingTopology;

    public DijkstraShortestPath() {
        this.routingTopology = new HashMap<>();
    }

    public void registerVertex(int vertexId) {
        routingTopology.putIfAbsent(vertexId, new ArrayList<>());
    }

    public void establishDirectedRoute(int originVertex, int destinationVertex, int travelCost) {
        if (!routingTopology.containsKey(originVertex) || !routingTopology.containsKey(destinationVertex)) {
            throw new IllegalArgumentException("Target vertices must be registered prior to edge allocation.");
        }
        if (travelCost < 0) {
            throw new IllegalArgumentException("Dijkstra's algorithm cannot process negative edge weights.");
        }
        
        routingTopology.get(originVertex).add(new NetworkEdge(destinationVertex, travelCost));
    }

    public Map<Integer, Integer> calculateShortestPaths(int startingVertex) {
        if (!routingTopology.containsKey(startingVertex)) {
            throw new IllegalArgumentException("Starting vertex does not exist in the routing topology.");
        }

        Map<Integer, Integer> shortestPathRegistry = new HashMap<>();
        for (int vertex : routingTopology.keySet()) {
            // Using Integer.MAX_VALUE simulates the mathematical concept of infinity
            shortestPathRegistry.put(vertex, Integer.MAX_VALUE);
        }
        shortestPathRegistry.put(startingVertex, 0);

        PriorityQueue<RouteCost> activeRoutingQueue = new PriorityQueue<>(
            Comparator.comparingInt(route -> route.cumulativeCost)
        );
        activeRoutingQueue.offer(new RouteCost(startingVertex, 0));

        while (!activeRoutingQueue.isEmpty()) {
            RouteCost currentRoute = activeRoutingQueue.poll();
            int currentVertex = currentRoute.vertexId;
            int accumulatedCost = currentRoute.cumulativeCost;

            // Stale Routing Optimization: Discard heap entries if a faster route was already processed
            if (accumulatedCost > shortestPathRegistry.get(currentVertex)) {
                continue;
            }

            for (NetworkEdge outboundEdge : routingTopology.get(currentVertex)) {
                int targetVertex = outboundEdge.destinationVertex;
                int potentialNewCost = accumulatedCost + outboundEdge.travelCost;

                // Path Relaxation: Overwrite state if a strictly superior route is discovered
                if (potentialNewCost < shortestPathRegistry.get(targetVertex)) {
                    shortestPathRegistry.put(targetVertex, potentialNewCost);
                    activeRoutingQueue.offer(new RouteCost(targetVertex, potentialNewCost));
                }
            }
        }

        return shortestPathRegistry;
    }

    public static void main(String[] args) {
        DijkstraShortestPath networkRouter = new DijkstraShortestPath();

        int[] serverNodes = {0, 1, 2, 3, 4};
        for (int node : serverNodes) {
            networkRouter.registerVertex(node);
        }

        /*
         * Network Topology (Directed, Weighted):
         * 0 -> 1 (Cost: 4)
         * 0 -> 2 (Cost: 1)
         * 2 -> 1 (Cost: 2)  -- Notice: 0->2->1 costs 3, which is faster than direct 0->1
         * 1 -> 3 (Cost: 1)
         * 2 -> 3 (Cost: 5)
         * 3 -> 4 (Cost: 3)
         */
        networkRouter.establishDirectedRoute(0, 1, 4);
        networkRouter.establishDirectedRoute(0, 2, 1);
        networkRouter.establishDirectedRoute(2, 1, 2);
        networkRouter.establishDirectedRoute(1, 3, 1);
        networkRouter.establishDirectedRoute(2, 3, 5);
        networkRouter.establishDirectedRoute(3, 4, 3);

        System.out.println("Calculating optimal routing tables from Server 0...");
        Map<Integer, Integer> routingTable = networkRouter.calculateShortestPaths(0);

        for (Map.Entry<Integer, Integer> route : routingTable.entrySet()) {
            System.out.println("Cost to reach Server " + route.getKey() + ": " + route.getValue());
        }
        
        /*
         * Expected Output:
         * Cost to reach Server 0: 0
         * Cost to reach Server 1: 3  (Path: 0 -> 2 -> 1)
         * Cost to reach Server 2: 1  (Path: 0 -> 2)
         * Cost to reach Server 3: 4  (Path: 0 -> 2 -> 1 -> 3)
         * Cost to reach Server 4: 7  (Path: 0 -> 2 -> 1 -> 3 -> 4)
         */
    }
}