package puzzle;

import java.util.ArrayList;
import java.util.List;

public class Map {

    public static final String EMPTY_SPOT = ".";

    private final int maxWidth;

    private final int maxHeight;

    private final List<List<Integer>> heightMatrix;

    /**
     * Create a map from a height matrix where 1st dimension is row, then column.
     */
    public Map(List<List<Integer>> heightMatrix) {
        this.maxWidth = heightMatrix.get(0).size();
        this.maxHeight = heightMatrix.size();
        this.heightMatrix = heightMatrix;
    }

    public int heightAt(Point p) {
        return heightAt(p.x(), p.y());
    }

    public int heightAt(int x, int y) {
        return heightMatrix.get(y).get(x);
    }

    public boolean isLowPoint(int x, int y) {
        return isLowerThanUp(x, y)
                && isLowerThanDown(x, y)
                && isLowerThanLeft(x, y)
                && isLowerThanRight(x, y);
    }

    private boolean isLowerThanRight(int x, int y) {
        if (x == maxWidth - 1) {
            return true;
        }
        return heightAt(x, y) < heightAt(x + 1, y);
    }

    private boolean isLowerThanLeft(int x, int y) {
        if (x == 0) {
            return true;
        }
        return heightAt(x, y) < heightAt(x - 1, y);
    }

    private boolean isLowerThanDown(int x, int y) {
        if (y == maxHeight - 1) {
            return true;
        }
        return heightAt(x, y) < heightAt(x, y + 1);
    }

    private boolean isLowerThanUp(int x, int y) {
        if (y == 0) {
            return true;
        }
        return heightAt(x, y) < heightAt(x, y - 1);
    }

    public int riskAt(int x, int y) {
        return heightAt(x, y) + 1;
    }

    public String draw() {
        var builder = new StringBuilder();
        for (int y = 0; y < maxHeight; y++) {
            for (int x = 0; x < maxWidth; x++) {
                var height = heightMatrix.get(y).get(x);
                builder.append(height);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public List<Location> findLowPoints() {
        var result = new ArrayList<Location>();
        for (int y = 0; y < maxHeight; y++) {
            for (int x = 0; x < maxWidth; x++) {
                if (isLowPoint(x, y)) {
                    result.add(Location.of(x, y, heightAt(x, y)));
                }
            }
        }
        return result;
    }

    public boolean isOnMap(Point point) {
        return point.x() >= 0
                && point.x() < maxWidth
                && point.y() >= 0
                && point.y() < maxHeight;
    }
}
