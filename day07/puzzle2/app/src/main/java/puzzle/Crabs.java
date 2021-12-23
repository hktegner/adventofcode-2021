package puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Crabs {

    private final List<Integer> crabPositions;
    private final Integer minPosition;
    private final Integer maxPosition;

    public Crabs(List<Integer> crabPositions) {
        this.crabPositions = crabPositions;
        this.minPosition = crabPositions.stream().reduce(Math::min).orElse(0);
        this.maxPosition = crabPositions.stream().reduce(Math::max).orElse(0);
    }

    public int numberOfCrabs() {
        return crabPositions.size();
    }

    public int minPosition() {
        return minPosition;
    }

    public int maxPosition() {
        return maxPosition;
    }

    public int fuelToAlignAt(int alignmentPosition) {
        return crabPositions
                .stream()
                .map(pos -> cost(pos, alignmentPosition))
                .reduce(Integer::sum)
                .orElse(0);
    }

     public static int cost(int p1, int p2) {
        var min = Math.min(p1, p2);
        var max = Math.max(p1, p2);
        var diff = max-min;
        if (diff == 0) {
            return 0;
        }
        return IntStream.rangeClosed(1, diff)
                .sum();
    }

    public List<Integer> getCrabPositions() {
        return new ArrayList<>(crabPositions);
    }
}
