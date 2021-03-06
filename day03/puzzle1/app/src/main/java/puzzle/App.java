/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package puzzle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {

    private List<Bits> readNumbers(Path file) {
        try {
            return Files.lines(file)
                    .filter(s -> s != null && !s.isEmpty())
                    .map(Bits::valueOf)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Couldn't read the file", e);
        }
    }

    public LifeSupportRating findLifeSupportRating(String resourceName) {
        var tmpDir = AppUtil.createTempDirectory();
        try {
            var inputFile = AppUtil.writeResourceToFile(tmpDir, resourceName);
            var reportLines = readNumbers(inputFile);
            var o2GenRating = findO2GenRating(reportLines);
            var co2ScrubRating = findCO2ScrubRating(reportLines);
            return new LifeSupportRating(o2GenRating, co2ScrubRating);
        } finally {
            AppUtil.deleteDirectoryAndChildren(tmpDir);
        }
    }

    /**
     * Find the most frequently occurring bit in each position and
     * return those bits.
     */
    private Bits findO2GenRating(List<Bits> reportLines) {
        int numberOfBits = reportLines.get(0).length();
        for (var index = 0; index < numberOfBits; index++) {
            char mostCommonDigit = mostCommonChar(index, reportLines);
            final int finalIndex = index;
            reportLines =
                    reportLines
                            .stream()
                            .filter(bits -> bits.charAt(finalIndex) == mostCommonDigit)
                            .collect(Collectors.toList());
        }
        return reportLines.get(0);
    }

    private char mostCommonChar(int index, List<Bits> reportLines) {
        var counts = countCharsAt(index, reportLines);
        var ones = counts.get('1');
        var zeroes = counts.get('0');
        if (ones == null) {
            return '0';
        }
        if (zeroes == null) {
            return '1';
        }
        return ones >= zeroes ? '1' : '0';
    }

    private char mostUncommonChar(int index, List<Bits> reportLines) {
        var counts = countCharsAt(index, reportLines);
        var ones = counts.get('1');
        var zeroes = counts.get('0');
        if (ones == null) {
            return '0';
        }
        if (zeroes == null) {
            return '1';
        }
        return zeroes <= ones ? '0' : '1';
    }

    private Map<Character, Long> countCharsAt(int index, List<Bits> reportLines) {
        return reportLines
                .stream()
                .map(b -> b.charAt(index))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    /**
     * Find the least frequently occurring bit in each position and
     * return those bits.
     */
    private Bits findCO2ScrubRating(List<Bits> reportLines) {
        int numberOfBits = reportLines.get(0).length();
        for (var index = 0; index < numberOfBits; index++) {
            char mostUncommonDigit = mostUncommonChar(index, reportLines);
            final int finalIndex = index;
            reportLines =
                    reportLines
                            .stream()
                            .filter(bits -> bits.charAt(finalIndex) == mostUncommonDigit)
                            .collect(Collectors.toList());
        }
        return reportLines.get(0);
    }

    public static void main(String[] args) {
        LifeSupportRating consumption = new App().findLifeSupportRating("/input.txt");
        System.out.printf("Power consumption elements = %s%n", consumption);
        System.out.printf("Power consumption = %d%n", consumption.value());
    }

}
