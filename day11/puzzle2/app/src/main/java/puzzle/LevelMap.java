package puzzle;

import java.util.List;

public class LevelMap {

    public static final String EMPTY_SPOT = ".";

    private final int maxWidth;

    private final int maxHeight;

    private final boolean[][] markedMatrix;

    private final int[][] levelMatrix;

    /**
     * Create a map from a height matrix where 1st dimension is row, then column.
     */
    public LevelMap(List<List<Integer>> levelMatrix) {
        this.maxWidth = levelMatrix.get(0).size();
        this.maxHeight = levelMatrix.size();
        this.levelMatrix = new int[maxWidth][maxHeight];
        this.markedMatrix = new boolean[maxWidth][maxHeight];
        for (int x=0 ; x<this.maxWidth ; x++ ) {
            for (int y=0 ; y<this.maxHeight ; y++) {
                this.levelMatrix[x][y] = levelMatrix.get(y).get(x);
            }
        }
    }

    public void resetAll() {
        for (int x=0 ; x<this.maxWidth ; x++ ) {
            for (int y=0 ; y<this.maxHeight ; y++) {
                this.markedMatrix[x][y] = false;
                this.levelMatrix[x][y] = 0;
            }
        }
    }

    public int levelAt(int x, int y) {
        return levelMatrix[x][y];
    }

    public int increaseLevelAt(int x, int y) {
        return ++levelMatrix[x][y];
    }

    public void resetLevelAt(int x, int y) {
        levelMatrix[x][y] = 0;
    }

    public void unmarkAll() {
        for (int x=0 ; x<this.maxWidth ; x++ ) {
            for (int y=0 ; y<this.maxHeight ; y++) {
                this.markedMatrix[x][y] = false;
            }
        }
    }

    public boolean isMarked(int x, int y) {
        return markedMatrix[x][y];
    }

    public void mark(int x, int y) {
        markedMatrix[x][y] = true;
    }

    public void unmark(int x, int y) {
        markedMatrix[x][y] = false;
    }

    public boolean isOnMap(int x, int y) {
        return x >= 0
                && x < maxWidth
                && y >= 0
                && y < maxHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public String toString() {
        var builder = new StringBuilder();
        for (int y = 0; y < maxHeight; y++) {
            for (int x = 0; x < maxWidth; x++) {
                var height = levelMatrix[x][y];
                builder.append(height);
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
