package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GraphArchitecture {

    private final Map<Integer, List<Integer>> adjacencyMap;

    public GraphArchitecture() {
        this.adjacencyMap = new HashMap<>();
    }

    public void registerVertex(int vertexId) {
        adjacencyMap.putIfAbsent(vertexId, new ArrayList<>());
    }

    public void establishUndirectedEdge(int originVertex, int destinationVertex) {
        if (!adjacencyMap.containsKey(originVertex) || !adjacencyMap.containsKey(destinationVertex)) {
            throw new IllegalArgumentException("Target vertices must be registered prior to edge allocation.");
        }
        adjacencyMap.get(originVertex).add(destinationVertex);
        adjacencyMap.get(destinationVertex).add(originVertex);
    }

    public void executeBFS(int startingVertex) {
        if (!adjacencyMap.containsKey(startingVertex)) {
            throw new IllegalArgumentException("Starting vertex does not exist in the graph topology.");
        }

        Set<Integer> visitedRegistry = new HashSet<>();
        Queue<Integer> discoveryQueue = new ArrayDeque<>();

        visitedRegistry.add(startingVertex);
        discoveryQueue.offer(startingVertex);

        System.out.print("BFS Traversal Stream: ");

        while (!discoveryQueue.isEmpty()) {
            int currentVertex = discoveryQueue.poll();
            System.out.print(currentVertex + " ");

            for (int adjacentVertex : adjacencyMap.get(currentVertex)) {
                // Defensive cycle prevention: Bypass memory nodes that have already been discovered
                if (!visitedRegistry.contains(adjacentVertex)) {
                    visitedRegistry.add(adjacentVertex);
                    discoveryQueue.offer(adjacentVertex);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        GraphArchitecture networkTopology = new GraphArchitecture();

        // 1. Initialize memory allocation for all required vertices
        int[] networkNodes = {0, 1, 2, 3, 4, 5};
        for (int node : networkNodes) {
            networkTopology.registerVertex(node);
        }

        // 2. Establish bi-directional routing paths
        networkTopology.establishUndirectedEdge(0, 1);
        networkTopology.establishUndirectedEdge(0, 2);
        networkTopology.establishUndirectedEdge(1, 3);
        networkTopology.establishUndirectedEdge(2, 4);
        networkTopology.establishUndirectedEdge(3, 4); // This edge creates a structural cycle (0-1-3-4-2-0)
        networkTopology.establishUndirectedEdge(3, 5);

        System.out.println("Initiating Breadth-First Search from Vertex 0...");
        
        
        
        networkTopology.executeBFS(0); 
        // Expected topology discovery order: 0 1 2 3 4 5
    }
}