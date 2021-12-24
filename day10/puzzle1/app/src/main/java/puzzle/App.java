package puzzle;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class App {

    private final List<Line> lines;

    public static void main(String[] args) {
        var app = new App("input.txt");
        System.out.printf("Total error score for corrupt lines: %d%n", app.findTotalErrorScore());
        System.out.printf("Middle completion score for incomplete lines: %d%n", app.findMiddleCompletionScore());
    }

    public long findMiddleCompletionScore() {
        lines
                .stream()
                .filter(Line::isIncomplete)
                .filter(l -> l.completionScore() < 0)
                .forEach(
                        l -> System.out.printf("Debug: %s%n", l)
                );

        var completionScores = lines
                .stream()
                .filter(Line::isIncomplete)
                .map(Line::parseResult)
                .map(ParseResult::completionScore)
                .sorted()
                .collect(Collectors.toList());
        int middleIndex = completionScores.size() / 2;
        completionScores.stream().forEachOrdered(score -> System.out.printf("Score: %d%n", score));

        System.out.printf("Incomplete lines: %d%n", completionScores.size());
        System.out.printf("Middle point: %d%n", middleIndex);
        return completionScores.get(middleIndex);
    }

    public App(String resourceName) {
        lines = AppUtil.linesFromResource(resourceName)
                .stream()
                .map(String::trim)
                .filter(Predicate.not(String::isEmpty))
                .map(Line::new)
                .collect(Collectors.toList());

        System.out.printf("Found %d lines%n", lines.size());
        System.out.printf("Found %d corrupt lines%n", lines.stream().filter(Line::isCorrupted).count());
        System.out.printf("Found %d incomplete lines%n", lines.stream().filter(Line::isIncomplete).count());
        System.out.printf("Found %d valid lines%n", lines.stream().filter(Line::isValid).count());
    }

    public int findTotalErrorScore() {
        // Skip incomplete and valid lines for now.
        return lines
                .stream()
                .filter(Line::isCorrupted)
                .mapToInt(l -> l.parseResult().errorPoints())
                .sum();
    }
}