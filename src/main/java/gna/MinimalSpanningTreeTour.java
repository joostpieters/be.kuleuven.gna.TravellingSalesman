package gna;

import java.util.ArrayList;
import java.util.List;

/**
 * A tour constructed using the minimal spanning tree heuristic.
 */
public class MinimalSpanningTreeTour extends Tour {

    public class MSTEdge {
        public final Point p1, p2;

        public MSTEdge(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    public MinimalSpanningTreeTour(World world) {
        super(world);
        tour = new ArrayList<Point>();
        // compute route here
    }

    @Override
    public double getTotalDistance() {
        double totalDistance = 0.0;
        for (int i = 0; i < tour.size(); i++) {
            totalDistance += tour.get(i).distanceTo(tour.get(i + 1));
        }
        return totalDistance;
    }

    /**
     * Return the root of the MST used to construct the visit sequence.
     * <p/>
     * This method returns null if and only if <code>getWorld().getPoints()</code> is empty.
     */
    public Point getMSTRoot() {
        throw new RuntimeException("not implemented");
    }

    /**
     * Return the edges on the MST used to construct the visit sequence.
     * <p/>
     * The result of this method is never null.
     */
    public List<MSTEdge> getMST() {
        throw new RuntimeException("not implemented");
    }

    @Override
    /**
     * The visit sequence is a PRE-ORDER traversal of the MST
     * starting at a root (e.g. the first point of the world).
     *
     * Traverse the children of each node in increasing order of distance.
     *
     * Return the empty list if world is empty.
     */
    public List<Point> getVisitSequence() {
        throw new RuntimeException("not implemented");
    }

    private List<Point> tour;
}
