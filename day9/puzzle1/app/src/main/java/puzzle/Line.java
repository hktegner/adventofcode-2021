package puzzle;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Line {

    private static final Set<Integer> KNOWN_DIGIT_SEGMENT_COUNTS = Set.of(2, 4, 3, 7);
    private final List<Digit> uniqueDigits;
    private final List<Digit> displayDigits;

    public Line(String input) {
        String[] digitGroups = input.split("\\|");
        String[] uniqueDigitsGroups = digitGroups[0].trim().split("\\s+");
        String[] displayDigitGroups = digitGroups[1].trim().split("\\s+");
        uniqueDigits =
                Arrays.stream(uniqueDigitsGroups)
                        .map(Digit::new)
                        .collect(Collectors.toList());
        displayDigits =
                Arrays.stream(displayDigitGroups)
                        .map(Digit::new)
                        .collect(Collectors.toList());
    }

    public int knownDisplayDigits() {
        return displayDigits
                .stream()
                .map(Digit::segmentCount)
                .filter(KNOWN_DIGIT_SEGMENT_COUNTS::contains)
                .mapToInt(count -> 1)
                .sum();
    }

    public <K, V> Map<K, V> decodedDigitsMap() {
        return null;
    }

    public void decodeDigits() {

    }
}
