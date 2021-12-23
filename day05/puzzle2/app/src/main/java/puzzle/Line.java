package puzzle;

import java.util.regex.Pattern;

public record Line(int x1, int y1, int x2, int y2) {

    private static final Pattern PATTERN = Pattern.compile("(\\d+),(\\d+) \\-> (\\d+),(\\d+)");

    public static Line from(String descriptor) {
        var matcher = PATTERN.matcher(descriptor.trim());
        if (matcher.groupCount() != 4 || !matcher.matches()) {
            throw new IllegalStateException("Invalid descriptor " + descriptor);
        }
        return new Line(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(3)),
                Integer.parseInt(matcher.group(4))
        );
    }

    public void markOnMap(Map map) {
        if (x1 == x2) {
            // diagonal (x constant)
            int miny = Math.min(y1, y2);
            int maxy = Math.max(y1, y2);
            for (int y = miny; y <= maxy; y++) {
                map.mark(x1, y);
            }
        } else if (y1 == y2) {
            // horizontal (y constant)
            int minx = Math.min(x1, x2);
            int maxx = Math.max(x1, x2);
            for (int x = minx; x <= maxx; x++) {
                map.mark(x, y1);
            }
        } else {
            int deltax = 1;
            if (x1 > x2) {
                deltax = -1;
            }
            int deltay = 1;
            if (y1 > y2) {
                deltay = -1;
            }
            int x, y;
            for (x=x1, y=y1 ; x!=x2 ; x+=deltax, y+=deltay) {
                map.mark(x, y);
            }
            // mark end position
            map.mark(x, y);
        }
    }

    public boolean isStraight() {
        return x1 == x2 || y1 == y2;
    }

}
