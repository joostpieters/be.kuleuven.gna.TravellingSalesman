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
        for (int i = 0; i < tour.size() - 1; i++) {
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
        tour.add(unvisitedPoints.remove(0));
        tour.add(unvisitedPoints.remove(1));
        while (!unvisitedPoints.isEmpty()) {
            Point next = unvisitedPoints.remove(0);
            int nextInsertion = calculateInsertionPoint(next);
            tour.add(nextInsertion, next);
        }
    }

    private int calculateInsertionPoint(Point next) {
        double smallestIncrease = Double.POSITIVE_INFINITY;
        int nextInsertion = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            double initialDistance = tour.get(i).distanceTo(tour.get(i + 1));
            double distanceToNext = tour.get(i).distanceTo(next) + next.distanceTo(tour.get(i + 1));
            double diff = distanceToNext - initialDistance;
            if (diff < smallestIncrease) {
                smallestIncrease = diff;
                nextInsertion = i + 1;
            }
        }
        double distanceToFirst = next.distanceTo(tour.get(0));
        if (distanceToFirst < smallestIncrease) {
            smallestIncrease = distanceToFirst;
            nextInsertion = 0;
        }
        double distanceToLast = next.distanceTo(tour.get(tour.size() - 1));
        if (distanceToLast < smallestIncrease) {
            smallestIncrease = distanceToLast;
            nextInsertion = tour.size() - 1;
        }
        return nextInsertion;
    }

    private List<Point> tour, points;
}
