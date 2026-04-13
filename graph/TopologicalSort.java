package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TopologicalSort {

    private final Map<Integer, List<Integer>> adjacencyMap;
    // Tracks the number of unresolved prerequisites for every vertex
    private final Map<Integer, Integer> inDegreeMap;

    public TopologicalSort() {
        this.adjacencyMap = new HashMap<>();
        this.inDegreeMap = new HashMap<>();
    }

    public void registerTask(int taskId) {
        adjacencyMap.putIfAbsent(taskId, new ArrayList<>());
        inDegreeMap.putIfAbsent(taskId, 0);
    }

    public void establishDependency(int prerequisiteTask, int dependentTask) {
        if (!adjacencyMap.containsKey(prerequisiteTask) || !adjacencyMap.containsKey(dependentTask)) {
            throw new IllegalArgumentException("Tasks must be registered prior to establishing dependencies.");
        }
        
        // The prerequisite points to the dependent
        adjacencyMap.get(prerequisiteTask).add(dependentTask);
        
        // The dependent task now has one additional blocker
        inDegreeMap.put(dependentTask, inDegreeMap.get(dependentTask) + 1);
    }

    public List<Integer> generateExecutionSequence() {
        Queue<Integer> readyQueue = new ArrayDeque<>();
        List<Integer> executionOrder = new ArrayList<>();

        // 1. Seed the Queue with all inherently unblocked tasks (In-Degree == 0)
        for (Map.Entry<Integer, Integer> entry : inDegreeMap.entrySet()) {
            if (entry.getValue() == 0) {
                readyQueue.offer(entry.getKey());
            }
        }

        // 2. Process tasks and systematically unlock downstream dependencies
        while (!readyQueue.isEmpty()) {
            int currentTask = readyQueue.poll();
            executionOrder.add(currentTask);

            // Notify all dependent tasks that one of their prerequisites has been resolved
            for (int dependentTask : adjacencyMap.get(currentTask)) {
                int remainingBlockers = inDegreeMap.get(dependentTask) - 1;
                inDegreeMap.put(dependentTask, remainingBlockers);

                // If all blockers are resolved, the dependent task is ready for execution
                if (remainingBlockers == 0) {
                    readyQueue.offer(dependentTask);
                }
            }
        }

        // 3. Structural validation: If the sorted list is smaller than the total graph size, 
        // a cycle prevented some in-degrees from ever reaching zero.
        if (executionOrder.size() != inDegreeMap.size()) {
            throw new IllegalStateException("System compilation failed: Circular dependency detected. Topological Sort impossible.");
        }

        return executionOrder;
    }

    public static void main(String[] args) {
        TopologicalSort compiler = new TopologicalSort();

        int[] systemTasks = {0, 1, 2, 3, 4, 5};
        for (int task : systemTasks) {
            compiler.registerTask(task);
        }

        /*
         * Dependency Architecture:
         * Task 5 must complete before Task 2 and Task 0
         * Task 4 must complete before Task 0 and Task 1
         * Task 2 must complete before Task 3
         * Task 3 must complete before Task 1
         */
        compiler.establishDependency(5, 2);
        compiler.establishDependency(5, 0);
        compiler.establishDependency(4, 0);
        compiler.establishDependency(4, 1);
        compiler.establishDependency(2, 3);
        compiler.establishDependency(3, 1);

        System.out.println("Compiling System Architecture...");
        try {
            List<Integer> bootSequence = compiler.generateExecutionSequence();
            System.out.println("Valid Boot Sequence: " + bootSequence);
            // Expected Output variations (multiple valid sorts exist): [4, 5, 0, 2, 3, 1] or [5, 4, 2, 3, 0, 1]
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
        }
    }
}