/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package puzzle;

import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        var app = new App("input.txt");
        System.out.printf("Sum of all display values: %d%n", app.displayValueTotal());
    }

    private final List<Line> lines;

    public App(String resourceName) {
        lines = AppUtil.linesFromResource(resourceName)
                .stream()
                .map(Line::new)
                .collect(Collectors.toList());
    }

    public int knownDisplayDigits() {
        return lines
                .stream()
                .map(Line::knownDisplayDigits)
                .reduce(Integer::sum).orElse(0);
    }

    public int displayValueTotal() {
        return lines
                .stream()
                .mapToInt(Line::displayValue)
                .sum();
    }
}
