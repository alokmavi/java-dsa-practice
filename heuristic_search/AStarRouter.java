package heuristic_search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarRouter {

    public static class GridCoordinate {
        public final int rowAxis;
        public final int colAxis;
        public int absoluteCostFromStart; // g(n)
        public int heuristicCostToTarget; // h(n)
        public GridCoordinate historicalParent;

        public GridCoordinate(int rowAxis, int colAxis) {
            this.rowAxis = rowAxis;
            this.colAxis = colAxis;
            this.absoluteCostFromStart = Integer.MAX_VALUE;
            this.heuristicCostToTarget = 0;
            this.historicalParent = null;
        }

        public int getTotalEstimatedCost() { // f(n)
            return absoluteCostFromStart + heuristicCostToTarget;
        }

        @Override
        public boolean equals(Object evaluationObject) {
            if (this == evaluationObject) return true;
            if (evaluationObject == null || getClass() != evaluationObject.getClass()) return false;
            GridCoordinate targetCoordinate = (GridCoordinate) evaluationObject;
            return rowAxis == targetCoordinate.rowAxis && colAxis == targetCoordinate.colAxis;
        }

        @Override
        public int hashCode() {
            return 31 * rowAxis + colAxis;
        }
    }

    // Mathematical representation of Up, Down, Left, Right topographical movements
    private static final int[][] DIRECTIONAL_VECTORS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<GridCoordinate> computeOptimalRoute(int[][] terrainGrid, int[] startPoint, int[] targetPoint) {
        if (terrainGrid == null || terrainGrid.length == 0 || terrainGrid[0].length == 0) {
            throw new IllegalArgumentException("System requires a valid topological grid.");
        }

        int rowBoundary = terrainGrid.length;
        int colBoundary = terrainGrid[0].length;

        if (isOutOfBounds(startPoint[0], startPoint[1], rowBoundary, colBoundary) || 
            isOutOfBounds(targetPoint[0], targetPoint[1], rowBoundary, colBoundary)) {
            throw new IndexOutOfBoundsException("Routing endpoints exceed topographical boundaries.");
        }

        // Structural constraint: 1 represents an impassable physical obstacle
        if (terrainGrid[startPoint[0]][startPoint[1]] == 1 || terrainGrid[targetPoint[0]][targetPoint[1]] == 1) {
            return Collections.emptyList();
        }

        PriorityQueue<GridCoordinate> explorationFrontier = new PriorityQueue<>(
            (coordAlpha, coordBeta) -> Integer.compare(coordAlpha.getTotalEstimatedCost(), coordBeta.getTotalEstimatedCost())
        );
        Set<GridCoordinate> exhaustedSectors = new HashSet<>();
        
        // 2D Array matrix tracks instantiated coordinate objects to prevent duplicate memory allocation
        GridCoordinate[][] coordinateRegistry = new GridCoordinate[rowBoundary][colBoundary];
        for (int r = 0; r < rowBoundary; r++) {
            for (int c = 0; c < colBoundary; c++) {
                coordinateRegistry[r][c] = new GridCoordinate(r, c);
            }
        }

        GridCoordinate originCoordinate = coordinateRegistry[startPoint[0]][startPoint[1]];
        GridCoordinate destinationCoordinate = coordinateRegistry[targetPoint[0]][targetPoint[1]];

        originCoordinate.absoluteCostFromStart = 0;
        originCoordinate.heuristicCostToTarget = computeManhattanDistance(originCoordinate, destinationCoordinate);
        explorationFrontier.add(originCoordinate);

        while (!explorationFrontier.isEmpty()) {
            GridCoordinate activeSector = explorationFrontier.poll();

            if (activeSector.equals(destinationCoordinate)) {
                return reconstructHistoricalPath(activeSector);
            }

            exhaustedSectors.add(activeSector);

            for (int[] vector : DIRECTIONAL_VECTORS) {
                int adjacentRow = activeSector.rowAxis + vector[0];
                int adjacentCol = activeSector.colAxis + vector[1];

                if (isOutOfBounds(adjacentRow, adjacentCol, rowBoundary, colBoundary) || 
                    terrainGrid[adjacentRow][adjacentCol] == 1) {
                    continue;
                }

                GridCoordinate adjacentSector = coordinateRegistry[adjacentRow][adjacentCol];

                if (exhaustedSectors.contains(adjacentSector)) {
                    continue;
                }

                // Grid movement cost is strictly 1 per sector transfer
                int tentativeAbsoluteCost = activeSector.absoluteCostFromStart + 1;

                // State mutation: Only update the coordinate if a strictly more efficient routing path is discovered
                if (tentativeAbsoluteCost < adjacentSector.absoluteCostFromStart) {
                    adjacentSector.historicalParent = activeSector;
                    adjacentSector.absoluteCostFromStart = tentativeAbsoluteCost;
                    adjacentSector.heuristicCostToTarget = computeManhattanDistance(adjacentSector, destinationCoordinate);

                    if (!explorationFrontier.contains(adjacentSector)) {
                        explorationFrontier.add(adjacentSector);
                    } else {
                        // Force PriorityQueue to re-evaluate structural placement following internal cost mutation
                        explorationFrontier.remove(adjacentSector);
                        explorationFrontier.add(adjacentSector);
                    }
                }
            }
        }

        return Collections.emptyList();
    }

    private boolean isOutOfBounds(int rowAxis, int colAxis, int rowBoundary, int colBoundary) {
        return rowAxis < 0 || rowAxis >= rowBoundary || colAxis < 0 || colAxis >= colBoundary;
    }

    private int computeManhattanDistance(GridCoordinate current, GridCoordinate target) {
        // Mathematical absolute distance assuming strictly orthogonal movement capabilities
        return Math.abs(current.rowAxis - target.rowAxis) + Math.abs(current.colAxis - target.colAxis);
    }

    private List<GridCoordinate> reconstructHistoricalPath(GridCoordinate terminalCoordinate) {
        List<GridCoordinate> optimalRoute = new ArrayList<>();
        GridCoordinate tracePointer = terminalCoordinate;
        
        while (tracePointer != null) {
            optimalRoute.add(tracePointer);
            tracePointer = tracePointer.historicalParent;
        }
        
        Collections.reverse(optimalRoute);
        return optimalRoute;
    }

    public static void main(String[] args) {
        AStarRouter navigationEngine = new AStarRouter();

        /*
         * Topographical Map Definition:
         * 0 = Passable Terrain
         * 1 = Structural Obstacle
         */
        int[][] sectorMap = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0}
        };

        int[] deploymentPoint = {0, 0};
        int[] extractionPoint = {4, 4};

        List<GridCoordinate> resolvedRoute = navigationEngine.computeOptimalRoute(sectorMap, deploymentPoint, extractionPoint);

        if (resolvedRoute.isEmpty()) {
            System.out.println("System Failure: No viable topographical routing path exists.");
        } else {
            System.out.println("Optimal Routing Established. Navigational sequence:");
            for (GridCoordinate waypoint : resolvedRoute) {
                System.out.println("-> Sector [" + waypoint.rowAxis + ", " + waypoint.colAxis + "]");
            }
        }
    }
}