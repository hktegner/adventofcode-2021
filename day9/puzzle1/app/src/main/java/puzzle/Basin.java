package puzzle;

import java.util.HashSet;
import java.util.Set;

public record Basin(Point lowPoint, Set<Point> points) implements Comparable<Basin> {

    public static final int INITIAL_PREVIOUS_HEIGHT = -1;

    public Basin(Point lowPoint, Map map, Set<Point> visitedPoints) {
        this(lowPoint, discoverBasin(lowPoint, map, visitedPoints));
    }

    public int size() { return points.size(); }

    private static Set<Point> discoverBasin(Point lowPoint, Map map, Set<Point> visitedPoints) {
        var foundPoints = new HashSet<Point>();
        explore(map, lowPoint, INITIAL_PREVIOUS_HEIGHT, foundPoints, visitedPoints);
        return foundPoints;
    }

    private static void explore(Map map, Point startPoint, int previousHeight, HashSet<Point> foundPoints, Set<Point> visitedPoints) {
        // Are we still within the map?
        if (!map.isOnMap(startPoint)) {
            return;
        }

        // If we have visited this point before, return without action.
        if (visitedPoints.contains(startPoint)) {
            return;
        }

        // If we are no longer moving up, return without action (some other lowpoint might consume this point).
        int height = map.heightAt(startPoint);
        if (height <= previousHeight) {
            return;
        }

        // If we have reached a 9, return (9s don't belong to any basin).
        if (height == 9) {
            return;
        }

        // Mark this point as visited
        foundPoints.add(startPoint);
        visitedPoints.add(startPoint);

        //  Visit neighbours
        explore(map, startPoint.up(), height, foundPoints, visitedPoints);
        explore(map, startPoint.left(), height, foundPoints, visitedPoints);
        explore(map, startPoint.right(), height, foundPoints, visitedPoints);
        explore(map, startPoint.down(), height, foundPoints, visitedPoints);
    }

    @Override
    public int compareTo(Basin other) {
        int size = size();
        int otherSize = other.size();
        return otherSize - size;
    }
}
