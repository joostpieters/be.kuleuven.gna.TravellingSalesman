package gna;

import java.util.ArrayList;
import java.util.List;

/**
 * A tour constructed using the nearest neighbor heuristic.
 */
public class NearestNeighborTour extends IncrementallyConstructedTour {

    public NearestNeighborTour(World world) {
        super(world);
        constructTour();
    }

    @Override
    public void insert(Point point) {
        if (points == null) {
            points = new ArrayList<Point>();
        }
        points.add(point);
    }

    @Override
    public double getTotalDistance() {
        double totalDistance = 0.0;
        for (int i = 0; i < tour.size() - 1; i++) {
            totalDistance += tour.get(i).distanceTo(tour.get(i + 1));
        }
        totalDistance += tour.get(0).distanceTo(tour.get(tour.size()-1));
        return totalDistance;
    }

    @Override
    public List<Point> getVisitSequence() {
        return tour;
    }

    private void constructTour() {
        tour = new ArrayList<Point>();
        if (getWorld().getNbPoints() != 0) {
            List<Point> unvisitedPoints = new ArrayList<Point>(points);
            Point initialPoint = unvisitedPoints.remove(0);
            for (int i = 0; i < points.size(); i++) {
                tour.add(initialPoint);
                initialPoint = findClosest(initialPoint, unvisitedPoints);
                unvisitedPoints.remove(initialPoint);
            }
        }
    }

    private Point findClosest(Point initialPoint, List<Point> unvisitedPoints) {;
        Point closest;
        if (unvisitedPoints.size() != 0) {
            closest = unvisitedPoints.get(0);
            for (Point next : unvisitedPoints) {
                if (next.distanceTo(initialPoint) < closest.distanceTo(initialPoint)
                        && next != initialPoint) {
                    closest = next;
                }
            }
        } else {
            closest = points.get(0);
        }
        return closest;
    }

    private List<Point> tour, points;
}
