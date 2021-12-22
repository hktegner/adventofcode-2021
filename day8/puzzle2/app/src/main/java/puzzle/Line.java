package puzzle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Line {

    private static final Set<Integer> KNOWN_DIGIT_SEGMENT_COUNTS = Set.of(2, 4, 3, 7);
    private final List<Digit> uniqueDigits;
    private final List<String> displayDigits;
    private final Map<String, Digit> decodedDigits;

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
                        .map(String::trim)
                        .collect(Collectors.toList());

        decodedDigits = decodeDigits(uniqueDigits);
    }

    public Digit decodedDigit(String segmentCodes) {
        return decodedDigits.get(sortedSegments(segmentCodes));
    }

    private static Map<String, Digit> decodeDigits(List<Digit> uniqueDigits) {
        var result = new HashMap<String, Digit>();
        // step 0 - identify known digits (with unique segment counts)
        var digit1 = findDigitWithSegmentCount(uniqueDigits, 2);
        var digit4 = findDigitWithSegmentCount(uniqueDigits, 4);
        var digit7 = findDigitWithSegmentCount(uniqueDigits, 3);
        var digit8 = findDigitWithSegmentCount(uniqueDigits, 7);
        identifyDigit(result, digit1, 1);
        identifyDigit(result, digit4, 4);
        identifyDigit(result, digit7, 7);
        identifyDigit(result, digit8, 8);

        // step 1 - find top SEGMENT (diff 7 segments with 1 segments)
        var topSegment = SetUtil.diff(digit7.segmentCodes(), digit1.segmentCodes()).stream().findFirst().orElseThrow();

        // step 2 - find top left and middle (diff 4 and 1 segments)
        var topLeftAndMiddle = SetUtil.diff(digit4.segmentCodes(), digit1.segmentCodes());

        // step 3 - find bottom left and bottom (diff 8 segments with union of (4 and top segment))
        var bottomLeftAndBottom =
                SetUtil.diff(digit8.segmentCodes(), SetUtil.union(digit4.segmentCodes(), Set.of(topSegment)));

        // step 4 - identify digit 6 (only digit with 6 segments which include top left and middle, and bottom left and bottom)
        var digit6 = uniqueDigits.stream().filter(d -> d.segmentCount() == 6)
                .filter(d -> d.segmentCodes().containsAll(SetUtil.union(topLeftAndMiddle, bottomLeftAndBottom)))
                .findFirst().orElseThrow();
        identifyDigit(result, digit6, 6);

        // step 5 - identify digit 0 (only 6 segment digit with bottom left and bottom (apart from the 6)
        var digit0 = uniqueDigits.stream().filter(d -> d.segmentCount() == 6)
                .filter(Predicate.not(Digit::isIdentified)).filter(d -> d.segmentCodes().containsAll(bottomLeftAndBottom))
                .findFirst().orElseThrow();
        identifyDigit(result, digit0, 0);

        // step 6 - find middle SEGMENT (diff between 8 and 0)
        var middleSegment = SetUtil.diff(digit8.segmentCodes(), digit0.segmentCodes()).stream()
                .findFirst().orElseThrow();

        // step 7 - find top left SEGMENT (diff top left + middle and middle)
        var topLeftSegment = SetUtil.diff(topLeftAndMiddle, Set.of(middleSegment)).stream().findFirst().orElseThrow();

        // step 8 - identify digit 5 (the only 5 segment digit with top left and middle)
        var digit5 = uniqueDigits.stream().filter(d -> d.segmentCount() == 5)
                .filter(d -> d.segmentCodes().containsAll(topLeftAndMiddle)).findFirst().orElseThrow();
        identifyDigit(result, digit5, 5);

        // step 9 - find bottom left SEGMENT (diff 6 and 5)
        var bottomLeft = SetUtil.diff(digit6.segmentCodes(), digit5.segmentCodes()).stream().findFirst().orElseThrow();

        // step 10 - find bottom SEGMENT (diff bottom left + bottom and bottom left)
        var bottomSegment = SetUtil.diff(bottomLeftAndBottom, Set.of(bottomLeft)).stream().findFirst().orElseThrow();

        // step 11 - find top right SEGMENT (diff 8 and 6)
        var topRightSegment = SetUtil.diff(digit8.segmentCodes(), digit6.segmentCodes()).stream().findFirst().orElseThrow();

        // step 12 - find bottom right SEGMENT (diff topRight and 1)
        var bottomRightSegment =
                SetUtil.diff(digit1.segmentCodes(), Set.of(topRightSegment)).stream().findFirst().orElseThrow();

        // Identified so far: 1, 4, 7, 8, 6, 0, 5
        // Remaining to identify: 2, 3, 9

        // step 13 - identify digit 2
        var digit2Segments = Set.of(topSegment, topRightSegment, middleSegment, bottomLeft, bottomSegment);
        var digit2 = uniqueDigits.stream().filter(Predicate.not(Digit::isIdentified))
                .filter(d -> d.segmentCodes().equals(digit2Segments)).findFirst().orElseThrow();
        identifyDigit(result, digit2, 2);

        // step 14 - identify digit 3
        var digit3Segments = Set.of(topSegment, topRightSegment, middleSegment, bottomRightSegment, bottomSegment);
        var digit3 = uniqueDigits.stream().filter(Predicate.not(Digit::isIdentified))
                .filter(d -> d.segmentCodes().equals(digit3Segments)).findFirst().orElseThrow();
        identifyDigit(result, digit3, 3);

        // step 15 - identify 9 (last digit)
        var digit9 = uniqueDigits.stream().filter(Predicate.not(Digit::isIdentified)).findFirst().orElseThrow();
        identifyDigit(result, digit9, 9);

        // step 16 - check all digits identified
        if (uniqueDigits.stream().anyMatch(Predicate.not(Digit::isIdentified))) {
            throw new IllegalStateException("Failed to identify all digits (" + result + ")");
        }

        return result;
    }

    private static void identifyDigit(Map<String, Digit> identifiedDigits, Digit digit, int identifyAs) {
        digit.identifyAs(identifyAs);
        identifiedDigits.put(sortedSegments(digit.digitCode()), digit);
    }

    private static Digit findDigitWithSegmentCount(List<Digit> uniqueDigits, int targetSegmentCount) {
        return uniqueDigits.stream().filter(d -> d.segmentCount() == targetSegmentCount).findFirst().orElseThrow();
    }

    public int knownDisplayDigits() {
        return displayDigits
                .stream()
                .map(String::length)
                .filter(KNOWN_DIGIT_SEGMENT_COUNTS::contains)
                .mapToInt(count -> 1)
                .sum();
    }

    /**
     * The value in the four digit 7-segment display.
     */
    public int displayValue() {
        var decodedDisplay = displayDigits
                .stream()
                .map(this::decodedDigit)
                .map(Digit::identifiedAs)
                .map(num -> Integer.toString(num))
                .reduce((a, b) -> a + b)
                .orElse("0");
        return Integer.parseInt(decodedDisplay);
    }

    private static String sortedSegments(String segments) {
        var sortedSegments = new TreeSet<Character>();
        for (Character ch : segments.toCharArray()) {
            sortedSegments.add(ch);
        }
        return sortedSegments.stream().map(c -> "" + c).reduce((a, b) -> a + b).orElseThrow();
    }
}
