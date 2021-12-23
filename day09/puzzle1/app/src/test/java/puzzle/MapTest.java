package puzzle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapTest {

    @Test
    void isOnMap() {
        var map = new Map(
                List.of(
                        List.of(0, 0, 0),
                        List.of(0, 0, 0),
                        List.of(0, 0, 0)
                )

        );
        assertTrue(map.isOnMap(Point.of(0, 0)));
        assertTrue(map.isOnMap(Point.of(1, 0)));
        assertTrue(map.isOnMap(Point.of(2, 0)));
        assertTrue(map.isOnMap(Point.of(0, 1)));
        assertTrue(map.isOnMap(Point.of(1, 1)));
        assertTrue(map.isOnMap(Point.of(2, 1)));
        assertTrue(map.isOnMap(Point.of(0, 2)));
        assertTrue(map.isOnMap(Point.of(1, 2)));
        assertTrue(map.isOnMap(Point.of(2, 2)));

        // corners
        assertFalse(map.isOnMap(Point.of(-1, -1)));
        assertFalse(map.isOnMap(Point.of( 3, -1)));
        assertFalse(map.isOnMap(Point.of(-1,  3)));
        assertFalse(map.isOnMap(Point.of( 3,  3)));

        // top line
        assertFalse(map.isOnMap(Point.of(0, -1)));
        assertFalse(map.isOnMap(Point.of(1, -1)));
        assertFalse(map.isOnMap(Point.of(2, -1)));

        // left line
        assertFalse(map.isOnMap(Point.of(-1, 0)));
        assertFalse(map.isOnMap(Point.of(-1, 1)));
        assertFalse(map.isOnMap(Point.of(-1, 2)));

        // right line
        assertFalse(map.isOnMap(Point.of(3, 0)));
        assertFalse(map.isOnMap(Point.of(3, 1)));
        assertFalse(map.isOnMap(Point.of(3, 2)));

        // bottom line
        assertFalse(map.isOnMap(Point.of(0, 3)));
        assertFalse(map.isOnMap(Point.of(1, 3)));
        assertFalse(map.isOnMap(Point.of(2, 3)));
    }

    @Test
    void testFindLowPoints() {
        var map = new Map(
                List.of(
                        List.of(2, 1, 9, 9, 9, 4, 3, 2, 1, 0),
                        List.of(3, 9, 8, 7, 8, 9, 4, 9, 2, 1),
                        List.of(9, 8, 5, 6, 7, 8, 9, 8, 9, 2),
                        List.of(8, 7, 6, 7, 8, 9, 6, 7, 8, 9),
                        List.of(9, 8, 9, 9, 9, 6, 5, 6, 7, 8)
                )
        );

        var lowPoints = map.findLowPoints();
        Assertions.assertEquals(4, lowPoints.size());
        Assertions.assertEquals(Location.of(1, 0, 1), lowPoints.get(0));
        Assertions.assertEquals(Location.of(9, 0, 0), lowPoints.get(1));
        Assertions.assertEquals(Location.of(2, 2, 5), lowPoints.get(2));
        Assertions.assertEquals(Location.of(6, 4, 5), lowPoints.get(3));
    }

}