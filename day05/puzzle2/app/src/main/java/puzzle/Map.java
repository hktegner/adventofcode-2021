package puzzle;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Map {

    public static final String EMPTY_SPOT = ".";
    private final byte[][] lineMatrix;

    private final int maxWidth;

    private final int maxHeight;

    public Map(int maxWidth, int maxHeight) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.lineMatrix = new byte[maxWidth][maxHeight];
    }

    public void mark(int x, int y) {
        lineMatrix[x][y] = (byte) (lineMatrix[x][y] + 1);
    }

    public boolean isMarked(int x, int y) {
        return linesAtPosition(x, y) > 0;
    }

    public int linesAtPosition(int x, int y) {
        return lineMatrix[x][y];
    }

    public String draw() {
        var builder = new StringBuilder();
        for (int y = 0; y < maxHeight; y++) {
            for (int x = 0; x < maxWidth; x++) {
                var lineCount = lineMatrix[x][y];
                if (lineCount == 0) {
                    builder.append(EMPTY_SPOT);
                } else {
                    builder.append(lineCount);
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public int count(Predicate<Integer> visitor) {
        int count = 0;
        for (int y = 0; y < maxHeight; y++) {
            for (int x = 0; x < maxWidth; x++) {
                var lineCount = lineMatrix[x][y];
                if (visitor.test((int)lineCount)) {
                    count++;
                }
            }
        }
        return count;
    }
}
