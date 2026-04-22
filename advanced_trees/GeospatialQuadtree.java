package advanced_trees;

import java.util.ArrayList;
import java.util.List;

public class GeospatialQuadtree {

    public static class CoordinatePoint {
        public final double xAxis;
        public final double yAxis;
        public final String entityId;

        public CoordinatePoint(double xAxis, double yAxis, String entityId) {
            this.xAxis = xAxis;
            this.yAxis = yAxis;
            this.entityId = entityId;
        }
    }

    public static class SpatialBoundary {
        public final double xCenter;
        public final double yCenter;
        public final double halfDimension; // The distance from the center to any edge

        public SpatialBoundary(double xCenter, double yCenter, double halfDimension) {
            this.xCenter = xCenter;
            this.yCenter = yCenter;
            this.halfDimension = halfDimension;
        }

        public boolean encapsulates(CoordinatePoint point) {
            return (point.xAxis >= xCenter - halfDimension &&
                    point.xAxis <= xCenter + halfDimension &&
                    point.yAxis >= yCenter - halfDimension &&
                    point.yAxis <= yCenter + halfDimension);
        }

        public boolean intersects(SpatialBoundary queryBoundary) {
            // Evaluates mathematical non-intersection, then negates for strict boolean logic
            return !(queryBoundary.xCenter - queryBoundary.halfDimension > this.xCenter + this.halfDimension ||
                     queryBoundary.xCenter + queryBoundary.halfDimension < this.xCenter - this.halfDimension ||
                     queryBoundary.yCenter - queryBoundary.halfDimension > this.yCenter + this.halfDimension ||
                     queryBoundary.yCenter + queryBoundary.halfDimension < this.yCenter - this.halfDimension);
        }
    }

    private final SpatialBoundary designatedBoundary;
    private final int nodeCapacity;
    private final List<CoordinatePoint> activeCoordinates;

    // Structural sub-sectors
    private GeospatialQuadtree northWestSector;
    private GeospatialQuadtree northEastSector;
    private GeospatialQuadtree southWestSector;
    private GeospatialQuadtree southEastSector;
    private boolean isFractured;

    public GeospatialQuadtree(SpatialBoundary designatedBoundary, int nodeCapacity) {
        if (designatedBoundary == null || nodeCapacity <= 0) {
            throw new IllegalArgumentException("System requires valid spatial boundaries and positive capacity limits.");
        }
        this.designatedBoundary = designatedBoundary;
        this.nodeCapacity = nodeCapacity;
        this.activeCoordinates = new ArrayList<>(nodeCapacity);
        this.isFractured = false;
    }

    public boolean insertCoordinate(CoordinatePoint targetPoint) {
        if (targetPoint == null || !designatedBoundary.encapsulates(targetPoint)) {
            return false;
        }

        if (activeCoordinates.size() < nodeCapacity && !isFractured) {
            activeCoordinates.add(targetPoint);
            return true;
        }

        if (!isFractured) {
            executeSectorSubdivision();
        }

        // Delegate routing dynamically; only one sector will successfully encapsulate the point
        return (northWestSector.insertCoordinate(targetPoint) ||
                northEastSector.insertCoordinate(targetPoint) ||
                southWestSector.insertCoordinate(targetPoint) ||
                southEastSector.insertCoordinate(targetPoint));
    }

    private void executeSectorSubdivision() {
        double x = designatedBoundary.xCenter;
        double y = designatedBoundary.yCenter;
        double subHalfDimension = designatedBoundary.halfDimension / 2;

        northWestSector = new GeospatialQuadtree(new SpatialBoundary(x - subHalfDimension, y + subHalfDimension, subHalfDimension), nodeCapacity);
        northEastSector = new GeospatialQuadtree(new SpatialBoundary(x + subHalfDimension, y + subHalfDimension, subHalfDimension), nodeCapacity);
        southWestSector = new GeospatialQuadtree(new SpatialBoundary(x - subHalfDimension, y - subHalfDimension, subHalfDimension), nodeCapacity);
        southEastSector = new GeospatialQuadtree(new SpatialBoundary(x + subHalfDimension, y - subHalfDimension, subHalfDimension), nodeCapacity);

        this.isFractured = true;

        // Migrate existing points to the newly allocated child sectors
        for (CoordinatePoint point : activeCoordinates) {
            northWestSector.insertCoordinate(point) || northEastSector.insertCoordinate(point) ||
            southWestSector.insertCoordinate(point) || southEastSector.insertCoordinate(point);
        }
        activeCoordinates.clear(); // Free parent memory post-migration
    }

    public List<CoordinatePoint> queryRegionalData(SpatialBoundary queryRegion) {
        List<CoordinatePoint> detectedEntities = new ArrayList<>();
        executeRegionalScan(queryRegion, detectedEntities);
        return detectedEntities;
    }

    private void executeRegionalScan(SpatialBoundary queryRegion, List<CoordinatePoint> resultsBuffer) {
        // Systemic optimization: Terminate branch evaluation instantly if spatial bounds do not overlap
        if (!designatedBoundary.intersects(queryRegion)) {
            return;
        }

        if (isFractured) {
            northWestSector.executeRegionalScan(queryRegion, resultsBuffer);
            northEastSector.executeRegionalScan(queryRegion, resultsBuffer);
            southWestSector.executeRegionalScan(queryRegion, resultsBuffer);
            southEastSector.executeRegionalScan(queryRegion, resultsBuffer);
        } else {
            for (CoordinatePoint point : activeCoordinates) {
                if (queryRegion.encapsulates(point)) {
                    resultsBuffer.add(point);
                }
            }
        }
    }

    public static void main(String[] args) {
        // Initialize a 100x100 geographical sector centered at (50, 50) with a node capacity of 2
        SpatialBoundary cityLimits = new SpatialBoundary(50, 50, 50);
        GeospatialQuadtree cityGrid = new GeospatialQuadtree(cityLimits, 2);

        cityGrid.insertCoordinate(new CoordinatePoint(20, 20, "Vehicle_Alpha"));
        cityGrid.insertCoordinate(new CoordinatePoint(25, 25, "Vehicle_Beta"));
        
        // This 3rd insertion fractures the tree into 4 sub-sectors
        cityGrid.insertCoordinate(new CoordinatePoint(80, 80, "Vehicle_Gamma"));
        cityGrid.insertCoordinate(new CoordinatePoint(85, 85, "Vehicle_Delta"));

        // Query a 20x20 region centered at (20, 20)
        SpatialBoundary searchRadar = new SpatialBoundary(20, 20, 10);
        List<CoordinatePoint> activeVehicles = cityGrid.queryRegionalData(searchRadar);

        System.out.println("Vehicles detected in localized radar scan:");
        for (CoordinatePoint vehicle : activeVehicles) {
            System.out.println("- ID: " + vehicle.entityId + " at [" + vehicle.xAxis + ", " + vehicle.yAxis + "]");
        }
        // Expected: Vehicle_Alpha, Vehicle_Beta
    }
}