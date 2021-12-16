package day2puzzle1;

import java.util.Objects;

public class Position {

    private final int horizontalPosition;

    private final int depth;

    public Position(int horizontalPosition, int depth) {
        this.horizontalPosition = horizontalPosition;
        this.depth = depth;
    }

    public Position move(Move move) {
        return switch (move.direction()) {
            case forward -> new Position(horizontalPosition + move.amount(), depth);
            case up -> new Position(horizontalPosition, depth - move.amount());
            case down -> new Position(horizontalPosition, depth + move.amount());
        };
    }

    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public String toString() {
        return "Position{" +
                "horizontalPosition=" + horizontalPosition +
                ", depth=" + depth +
                '}';
    }

    public Position copy() {
        return new Position(horizontalPosition, depth);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return horizontalPosition == position.horizontalPosition && depth == position.depth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontalPosition, depth);
    }
}
