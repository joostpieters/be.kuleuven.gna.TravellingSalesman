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
        if (tour.size() > 1) {
            for (int i = 0; i < tour.size() - 1; i++) {
                totalDistance += tour.get(i).distanceTo(tour.get(i + 1));
            }
            totalDistance += tour.get(0).distanceTo(tour.get(tour.size()-1));
        }
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
            tour.add(unvisitedPoints.remove(0));
            if (tour.size() > 1) {
                tour.add(unvisitedPoints.remove(1));
            }
            while (!unvisitedPoints.isEmpty()) {
                Point next = unvisitedPoints.remove(0);
                int nextInsertion = calculateInsertionPoint(next);
                tour.add(nextInsertion, next);
            }
        }
    }

    private int calculateInsertionPoint(Point next) {
        double smallestIncrease = Double.POSITIVE_INFINITY;
        int nextInsertion = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            double initialDistance = tour.get(i).distanceTo(tour.get(i + 1));
            double distanceToNext = tour.get(i).distanceTo(next) + next.distanceTo(tour.get(i + 1));
            if (distanceToNext - initialDistance < smallestIncrease) {
                smallestIncrease = distanceToNext - initialDistance;
                nextInsertion = i + 1;
            }
        }
        double initialDistance = tour.get(0).distanceTo(tour.get(tour.size() - 1));
        double distanceToNext = tour.get(0).distanceTo(next) + tour.get(tour.size() - 1).distanceTo(next);
        if (distanceToNext - initialDistance < smallestIncrease) {
            nextInsertion = 0;
        }

        return nextInsertion;
    }

    private List<Point> tour, points;
}
