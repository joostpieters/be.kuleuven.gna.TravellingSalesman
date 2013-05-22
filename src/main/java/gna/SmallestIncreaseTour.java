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
    }

    @Override
    public void insert(Point point) {
        throw new RuntimeException("not implemented");
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

    private List<Point> tour;
}
