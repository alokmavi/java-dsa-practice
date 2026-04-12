package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DirectedCycleDetection {

    private final Map<Integer, List<Integer>> adjacencyMap;

    public DirectedCycleDetection() {
        this.adjacencyMap = new HashMap<>();
    }

    public void registerVertex(int vertexId) {
        adjacencyMap.putIfAbsent(vertexId, new ArrayList<>());
    }

    public void establishDirectedEdge(int originVertex, int destinationVertex) {
        if (!adjacencyMap.containsKey(originVertex) || !adjacencyMap.containsKey(destinationVertex)) {
            throw new IllegalArgumentException("Target vertices must be registered prior to edge allocation.");
        }
        // One-way route assignment
        adjacencyMap.get(originVertex).add(destinationVertex);
    }

    public boolean containsCircularDependency() {
        Set<Integer> globalVisitedRegistry = new HashSet<>();
        Set<Integer> activePathRegistry = new HashSet<>();

        // Graphs can be disconnected. We must attempt to initiate DFS from every unvisited vertex.
        for (int currentVertex : adjacencyMap.keySet()) {
            if (!globalVisitedRegistry.contains(currentVertex)) {
                if (detectCycleDFS(currentVertex, globalVisitedRegistry, activePathRegistry)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean detectCycleDFS(int currentVertex, Set<Integer> globalVisitedRegistry, Set<Integer> activePathRegistry) {
        // 1. Cycle confirmed: The vertex is currently part of the active descent chain
        if (activePathRegistry.contains(currentVertex)) {
            return true;
        }

        // 2. Optimization: The vertex was fully evaluated in a previous path and proved safe
        if (globalVisitedRegistry.contains(currentVertex)) {
            return false;
        }

        // 3. Register vertex to both memory states
        globalVisitedRegistry.add(currentVertex);
        activePathRegistry.add(currentVertex);

        // 4. Command descent into directed dependencies
        for (int dependentVertex : adjacencyMap.get(currentVertex)) {
            if (detectCycleDFS(dependentVertex, globalVisitedRegistry, activePathRegistry)) {
                return true;
            }
        }

        // 5. Backtrack: Remove vertex from the active path as the descent is complete
        activePathRegistry.remove(currentVertex);
        
        return false;
    }

    public static void main(String[] args) {
        DirectedCycleDetection safeSystem = new DirectedCycleDetection();
        int[] safeNodes = {1, 2, 3, 4};
        for (int node : safeNodes) safeSystem.registerVertex(node);
        
        // 1 -> 2, 1 -> 3, 2 -> 4, 3 -> 4
        safeSystem.establishDirectedEdge(1, 2);
        safeSystem.establishDirectedEdge(1, 3);
        safeSystem.establishDirectedEdge(2, 4);
        safeSystem.establishDirectedEdge(3, 4);
        
        System.out.println("System 1 Contains Circular Dependency: " + safeSystem.containsCircularDependency()); // Expected: false

        DirectedCycleDetection corruptedSystem = new DirectedCycleDetection();
        int[] corruptedNodes = {1, 2, 3, 4};
        for (int node : corruptedNodes) corruptedSystem.registerVertex(node);
        
        // 1 -> 2, 2 -> 3, 3 -> 4, 4 -> 2 (Cycle: 2-3-4-2)
        corruptedSystem.establishDirectedEdge(1, 2);
        corruptedSystem.establishDirectedEdge(2, 3);
        corruptedSystem.establishDirectedEdge(3, 4);
        corruptedSystem.establishDirectedEdge(4, 2);

        System.out.println("System 2 Contains Circular Dependency: " + corruptedSystem.containsCircularDependency()); // Expected: true
    }
}