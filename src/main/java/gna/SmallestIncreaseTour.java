package gna;

import java.util.ArrayList;
import java.util.List;

/**
 * A tour constructed using the smallest increase heuristic.
 */
public class SmallestIncreaseTour extends IncrementallyConstructedTour {

    public SmallestIncreaseTour(World world) {
        super(world);
        tour = new ArrayList<Point>();
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
        for (int i = 0; i < tour.size(); i++) {
            totalDistance += tour.get(i).distanceTo(tour.get(i + 1));
        }
        return totalDistance;
    }

    @Override
    public List<Point> getVisitSequence() {
        return tour;
    }

    private void constructTour() {
        tour = new ArrayList<Point>();
        List<Point> unvisitedPoints = new ArrayList<Point>(points);
        Point initialPoint = unvisitedPoints.remove(0);
        for (int i = 0; i < points.size(); i++) {
            tour.add(initialPoint);
            initialPoint = findClosest(initialPoint, unvisitedPoints);
            unvisitedPoints.remove(initialPoint);
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
