package gna.tests;

import gna.NearestNeighborTour;
import gna.Point;
import gna.Tour;
import gna.World;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class TSPTest {

    @Test
    public void testNearestNeighbor0() {
        World world = new World(100, 100, new ArrayList<Point>());
        Tour tour = new NearestNeighborTour(world);
        assertTrue(tour.getVisitSequence().isEmpty());
    }
}
