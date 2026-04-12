package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DepthFirstSearch {

    private final Map<Integer, List<Integer>> adjacencyMap;

    public DepthFirstSearch() {
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

    public void executeDFS(int startingVertex) {
        if (!adjacencyMap.containsKey(startingVertex)) {
            throw new IllegalArgumentException("Starting vertex does not exist in the graph topology.");
        }

        Set<Integer> visitedRegistry = new HashSet<>();
        System.out.print("DFS Traversal Stream: ");
        
        // Initiate the recursive descent
        traverseRecursively(startingVertex, visitedRegistry);
        
        System.out.println();
    }

    private void traverseRecursively(int currentVertex, Set<Integer> visitedRegistry) {
        // Base Case equivalent: Halt execution if the node is already processed
        if (visitedRegistry.contains(currentVertex)) {
            return;
        }

        // 1. Process current memory location
        visitedRegistry.add(currentVertex);
        System.out.print(currentVertex + " ");

        // 2. Command parallel descent into all connected sub-routes
        for (int adjacentVertex : adjacencyMap.get(currentVertex)) {
            traverseRecursively(adjacentVertex, visitedRegistry);
        }
    }

    public static void main(String[] args) {
        DepthFirstSearch networkTopology = new DepthFirstSearch();

        int[] networkNodes = {0, 1, 2, 3, 4, 5};
        for (int node : networkNodes) {
            networkTopology.registerVertex(node);
        }

        /*
         * Architecture:
         * 0
         * / \
         * 1   2
         * / \   \
         * 3   4   5
         */
        networkTopology.establishUndirectedEdge(0, 1);
        networkTopology.establishUndirectedEdge(0, 2);
        networkTopology.establishUndirectedEdge(1, 3);
        networkTopology.establishUndirectedEdge(1, 4);
        networkTopology.establishUndirectedEdge(2, 5);

        System.out.println("Initiating Depth-First Search from Vertex 0...");
        
        // Expected DFS order: 0 1 3 4 2 5
        // Note: Actual order may vary slightly based on HashMap iteration order, 
        // but it will strictly follow a depth-first plunge.
        networkTopology.executeDFS(0); 
    }
}