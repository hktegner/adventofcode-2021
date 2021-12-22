package puzzle;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Digit {

    public static final int UNRECOGNISED_DIGIT = -1;

    private final Set<Character> segmentCodes = new HashSet<>();

    private final String digitCode;
    private int identifiedAs = UNRECOGNISED_DIGIT;

    public Digit(final String segmentCodeString) {
        digitCode = segmentCodeString == null ? "": segmentCodeString.trim();
        if (digitCode.length() == 0) {
            throw new IllegalArgumentException("Invalid segment code string " + digitCode);
        }
        IntStream.range(0, digitCode.length())
                .forEach(i -> segmentCodes.add(digitCode.charAt(i)));
    }

    public String digitCode() {
        return digitCode;
    }

    public Set<Character> segmentCodes() {
        return Collections.unmodifiableSet(segmentCodes);
    }

    public int segmentCount() { return digitCode.length(); }

    public boolean isIdentified() { return identifiedAs != UNRECOGNISED_DIGIT; }

    public int identifiedAs() {
        if (!isIdentified()) {
            throw new IllegalStateException("This digit has not been identified " + digitCode);
        }
        return identifiedAs;
    }

    public void identifyAs(int digit) {
        identifiedAs = digit;
    }

}
