package puzzle;

import java.util.stream.IntStream;

public class Digit {

    public static final int UNINITIALISED_INDEX = -1;
    private final int[] segmentIndexes;

    private final char[] segmentCodes;

    public Digit(final String segmentCodeString) {
        if (segmentCodeString == null || segmentCodeString.trim().length() == 0) {
            throw new IllegalArgumentException("Invalid segment code string " + segmentCodeString);
        }
        this.segmentCodes = segmentCodeString.trim().toCharArray();
        this.segmentIndexes = new int[this.segmentCodes.length];
        IntStream.range(0, this.segmentCodes.length)
                .forEach(i -> this.segmentIndexes[i] = UNINITIALISED_INDEX);
    }

    public int segmentCount() { return segmentCodes.length; }

    public void identifySegment(char segmentCode, int index) {
        for (int i = 0; i < segmentCodes.length; i++) {
            if (segmentCodes[i] == segmentCode) {
                segmentIndexes[i] = index;
            }
        }
    }

    public boolean isFullyIdentified() {
        for (var index : segmentIndexes) {
            if (index == UNINITIALISED_INDEX) {
                return false;
            }
        }
        return true;
    }

}
