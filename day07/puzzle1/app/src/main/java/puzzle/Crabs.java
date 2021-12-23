package puzzle;

import java.util.ArrayList;
import java.util.List;

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
                .map(pos -> Math.max(pos, alignmentPosition)
                        - Math.min(pos, alignmentPosition))
                .reduce(Integer::sum)
                .orElse(0);
    }

    public List<Integer> getCrabPositions() {
        return new ArrayList<>(crabPositions);
    }
}
