package gna.tests;

import gna.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;

public class TSPTest {

    private World world;

    @Before
    public void setUp() {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(110,225));
        points.add(new Point(161,280));
        points.add(new Point(325,554));
        points.add(new Point(490,285));
        points.add(new Point(157,443));
        points.add(new Point(283,379));
        points.add(new Point(397,566));
        points.add(new Point(306,360));
        points.add(new Point(343,110));
        points.add(new Point(552,199));
        world = new World(600, 600, points);
    }

    @Test
    public void testNearestNeighbor() {
        Tour tour = new NearestNeighborTour(world);
        assertFalse(tour.getVisitSequence().isEmpty());
        System.out.println("Nearest neighbor:");
        System.out.println(tour.getTotalDistance());
        System.out.println(tour);
    }

    @Test
    public void testSmallestIncrease() {
        Tour tour = new SmallestIncreaseTour(world);
        assertFalse(tour.getVisitSequence().isEmpty());
        System.out.println("Smallest Increase: ");
        System.out.println(tour.getTotalDistance());
        System.out.println(tour);
    }

    /* @Test
    public void testMinimalSpanningTree() {
        Tour tour = new MinimalSpanningTreeTour(world);
        assertFalse(tour.getVisitSequence().isEmpty());
        System.out.println("MST: ");
        System.out.println(tour.getTotalDistance());
        System.out.println(tour);
    }*/
}
