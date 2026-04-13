package graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class GridContamination {

    public static int calculateContaminationTime(int[][] inventoryGrid) {
        if (inventoryGrid == null || inventoryGrid.length == 0 || inventoryGrid[0].length == 0) {
            return 0;
        }

        int rowLimit = inventoryGrid.length;
        int colLimit = inventoryGrid[0].length;
        
        Queue<int[]> contaminationQueue = new ArrayDeque<>();
        int uncontaminatedCount = 0;

        // 1. Memory Initialization: Catalog initial state and seed the multi-source queue
        for (int row = 0; row < rowLimit; row++) {
            for (int col = 0; col < colLimit; col++) {
                if (inventoryGrid[row][col] == 2) {
                    contaminationQueue.offer(new int[]{row, col});
                } else if (inventoryGrid[row][col] == 1) {
                    uncontaminatedCount++;
                }
            }
        }

        if (uncontaminatedCount == 0) {
            return 0;
        }

        int minutesElapsed = 0;
        int[][] directionalVectors = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 2. Multi-Source Radial Expansion
        while (!contaminationQueue.isEmpty() && uncontaminatedCount > 0) {
            int nodesInCurrentLevel = contaminationQueue.size();

            for (int i = 0; i < nodesInCurrentLevel; i++) {
                int[] activeCoordinates = contaminationQueue.poll();
                int currentRow = activeCoordinates[0];
                int currentCol = activeCoordinates[1];

                for (int[] vector : directionalVectors) {
                    int adjacentRow = currentRow + vector[0];
                    int adjacentCol = currentCol + vector[1];

                    // Boundary and state verification
                    if (adjacentRow >= 0 && adjacentRow < rowLimit && 
                        adjacentCol >= 0 && adjacentCol < colLimit && 
                        inventoryGrid[adjacentRow][adjacentCol] == 1) {
                        
                        // Mutate state to represent contamination
                        inventoryGrid[adjacentRow][adjacentCol] = 2;
                        uncontaminatedCount--;
                        contaminationQueue.offer(new int[]{adjacentRow, adjacentCol});
                    }
                }
            }
            minutesElapsed++;
        }

        return uncontaminatedCount == 0 ? minutesElapsed : -1;
    }

    public static void main(String[] args) {
        int[][] activeCrate = {
            {2, 1, 1},
            {1, 1, 0},
            {0, 1, 1}
        };

        System.out.println("Time to full contamination (Crate 1): " + calculateContaminationTime(activeCrate)); // Expected: 4

        int[][] isolatedCrate = {
            {2, 1, 1},
            {0, 1, 1},
            {1, 0, 1}
        };

        System.out.println("Time to full contamination (Crate 2): " + calculateContaminationTime(isolatedCrate)); // Expected: -1
    }
}