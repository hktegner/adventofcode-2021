package puzzle;

public record Location(Point point, int height) {

    public static Location of(int x, int y, int height) {
        return new Location(Point.of(x, y), height);
    }

    public static Location of(Point p, int height) {
        return new Location(p, height);
    }

    public int risk() {
        return height + 1;
    }

}
