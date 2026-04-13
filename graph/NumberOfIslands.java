package graph;

public class NumberOfIslands {

    public static int countArchipelago(char[][] oceanGrid) {
        if (oceanGrid == null || oceanGrid.length == 0 || oceanGrid[0].length == 0) {
            return 0;
        }

        int totalIslands = 0;
        int rowLimit = oceanGrid.length;
        int colLimit = oceanGrid[0].length;

        for (int row = 0; row < rowLimit; row++) {
            for (int col = 0; col < colLimit; col++) {
                if (oceanGrid[row][col] == '1') {
                    totalIslands++;
                    sinkIslandGeometry(oceanGrid, row, col, rowLimit, colLimit);
                }
            }
        }

        return totalIslands;
    }

    private static void sinkIslandGeometry(char[][] grid, int currentRow, int currentCol, int rowLimit, int colLimit) {
        // Enforce strict geographical boundaries and terminate on water ('0')
        if (currentRow < 0 || currentCol < 0 || currentRow >= rowLimit || currentCol >= colLimit || grid[currentRow][currentCol] == '0') {
            return;
        }

        // In-place mutation serves as our structural visited registry, 
        // bypassing the need for an O(M * N) auxiliary boolean[][] matrix.
        grid[currentRow][currentCol] = '0';

        // Command orthogonal recursive expansion
        sinkIslandGeometry(grid, currentRow + 1, currentCol, rowLimit, colLimit);
        sinkIslandGeometry(grid, currentRow - 1, currentCol, rowLimit, colLimit);
        sinkIslandGeometry(grid, currentRow, currentCol + 1, rowLimit, colLimit);
        sinkIslandGeometry(grid, currentRow, currentCol - 1, rowLimit, colLimit);
    }

    public static void main(String[] args) {
        char[][] regionalMapOne = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };

        System.out.println("Region 1 Archipelago Count: " + countArchipelago(regionalMapOne)); // Expected: 1

        char[][] regionalMapTwo = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };

        System.out.println("Region 2 Archipelago Count: " + countArchipelago(regionalMapTwo)); // Expected: 3
    }
}