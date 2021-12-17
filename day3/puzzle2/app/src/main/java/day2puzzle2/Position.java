package day2puzzle2;

import java.util.Objects;

public class Position {

    private final int horizontalPosition;

    private final int depth;

    private final int aim;

    public Position() {
        this(0, 0, 0);
    }
    public Position(int horizontalPosition, int depth, int aim) {
        this.horizontalPosition = horizontalPosition;
        this.depth = depth;
        this.aim = aim;
    }

    public Position move(Move move) {
        return switch (move.direction()) {
            case forward -> new Position(
                    horizontalPosition + move.amount(),
                    depth + (aim * move.amount()),
                    aim);
            case up -> new Position(horizontalPosition, depth, aim - move.amount());
            case down -> new Position(horizontalPosition, depth, aim + move.amount());
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
        return new Position(horizontalPosition, depth, aim);
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
