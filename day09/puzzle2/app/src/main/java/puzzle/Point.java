package puzzle;

public record Point(int x, int y) {

    public static Point of(int x, int y) {
        return new Point(x, y);
    }

    public Point up() {
        return Point.of(x, y-1);
    }

    public Point left() {
        return Point.of(x-1, y);
    }

    public Point right() {
        return Point.of(x+1, y);
    }

    public Point down() {
        return Point.of(x, y+1);
    }
}
