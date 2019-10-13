package pl.stqa.pft.sandbox;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PointTests {

    @Test
    public void testDistancePositiveValues() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(4, 5);
        assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testDistancePositiveAndNegativeValues() {
        Point p1 = new Point(17, -10);
        Point p2 = new Point(-6, 89);
        assertTrue(p1.distance(p2) == 101.6366075781753);
    }

    @Test
    public void testDistanceNoNIntegerValues() {
        Point p1 = new Point(5.5,7.1);
        Point p2 = new Point(6.8,-13.0);
        assertEquals(p2.distance(p1), 20.141995928904365);
    }
}