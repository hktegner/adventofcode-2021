package puzzle;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BasinTest {

    @Test
    void smallTest() {
        var map = new Map(
                List.of(
                        List.of(0, 1, 2),
                        List.of(1, 9, 9),
                        List.of(0, 3, 2)
                )
        );

        var expectedLowPoints = List.of(
                Location.of(0, 0, 0),
                Location.of(0, 2, 0),
                Location.of(2, 2, 2)
        );
        assertEquals(expectedLowPoints, map.findLowPoints());

        var visitedPoints = new HashSet<Point>();
        var basin1Points = Set.of(Point.of(0, 0), Point.of(1, 0), Point.of(2, 0), Point.of(0, 1));
        assertEquals(basin1Points, new Basin(expectedLowPoints.get(0).point(), map, visitedPoints).points());
        assertTrue(visitedPoints.containsAll(basin1Points));

        var basin2Points = Set.of(Point.of(0, 2), Point.of(1, 2));
        assertEquals(basin2Points, new Basin(expectedLowPoints.get(1).point(), map, visitedPoints).points());
        assertTrue(visitedPoints.containsAll(basin2Points));

        var basin3Points = Set.of(Point.of(2, 2));
        assertEquals(basin3Points, new Basin(expectedLowPoints.get(2).point(), map, visitedPoints).points());
        assertTrue(visitedPoints.containsAll(basin3Points));
    }

    @Test
    void discoverBasin() {
        var map = new Map(
                List.of(
                        List.of(2, 1, 9, 9, 9, 4, 3, 2, 1, 0),
                        List.of(3, 9, 8, 7, 8, 9, 4, 9, 2, 1),
                        List.of(9, 8, 5, 6, 7, 8, 9, 8, 9, 2),
                        List.of(8, 7, 6, 7, 8, 9, 6, 7, 8, 9),
                        List.of(9, 8, 9, 9, 9, 6, 5, 6, 7, 8)
                )
        );

        var lowPoints = map.findLowPoints().stream().map(Location::point).collect(Collectors.toList());
        assertEquals(4, lowPoints.size());
        assertEquals(List.of(Point.of(1, 0), Point.of(9, 0), Point.of(2, 2), Point.of(6, 4)), lowPoints);


        var visitedPoints = new HashSet<Point>();
        var basin1 = new Basin(lowPoints.get(0), map, visitedPoints);
        var basin1Points = Set.of(Point.of(0, 0), Point.of(0, 1), Point.of(1, 0));
        assertTrue(visitedPoints.containsAll(basin1Points));
        assertEquals(basin1Points, basin1.points());

        var basin2 = new Basin(lowPoints.get(1), map, visitedPoints);
        var basin2Points = Set.of(
                Point.of(5, 0),
                Point.of(6, 0),
                Point.of(7, 0),
                Point.of(8, 0),
                Point.of(9, 0),
                Point.of(6, 1),
                Point.of(8, 1),
                Point.of(9, 1),
                Point.of(9, 2)
        );
        assertTrue(visitedPoints.containsAll(basin2Points));
        assertEquals(basin2Points, basin2.points());

        var basin3 = new Basin(lowPoints.get(2), map, visitedPoints);
        var basin3Points = Set.of(
                Point.of(2, 1),
                Point.of(3, 1),
                Point.of(4, 1),
                Point.of(1, 2),
                Point.of(2, 2),
                Point.of(3, 2),
                Point.of(4, 2),
                Point.of(5, 2),
                Point.of(0, 3),
                Point.of(1, 3),
                Point.of(2, 3),
                Point.of(3, 3),
                Point.of(4, 3),
                Point.of(1, 4)
        );
        assertTrue(visitedPoints.containsAll(basin3Points));
        assertEquals(basin3Points, basin3.points());

        var basin4 = new Basin(lowPoints.get(3), map, visitedPoints);
        var basin4Points = Set.of(
                Point.of(7, 2),
                Point.of(6, 3),
                Point.of(7, 3),
                Point.of(8, 3),
                Point.of(5, 4),
                Point.of(6, 4),
                Point.of(7, 4),
                Point.of(8, 4),
                Point.of(9, 4)
        );
        assertTrue(visitedPoints.containsAll(basin4Points));
        assertEquals(basin4Points, basin4.points());
    }

}