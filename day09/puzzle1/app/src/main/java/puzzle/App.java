package puzzle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    private final Map map;

    public static void main(String[] args) {
        var app = new App("input.txt");
        System.out.printf("Total risk of low locations %d%n", app.totalRiskOfLowPoints());
        System.out.printf("Product of the size of the three biggest basins: %d%n", app.productOfThreeBiggestBasins());
    }

    public App(String resourceName) {
        map = new Map(
                AppUtil.linesFromResource(resourceName)
                        .stream()
                        .map(String::trim)
                        .map(App::toIntList)
                        .collect(Collectors.toList())
        );
    }

    private static List<Integer> toIntList(String s) {
        var result = new ArrayList<Integer>();
        for (int i = 0; i < s.length(); i++) {
            result.add(Integer.parseInt("" + s.charAt(i)));
        }
        return result;
    }

    public int totalRiskOfLowPoints() {
        return map.findLowPoints()
                .stream()
                .mapToInt(Location::risk)
                .sum();
    }

    public int productOfThreeBiggestBasins() {
        var visitedPoints = new HashSet<Point>();
        var basins = map
                .findLowPoints()
                .stream()
                .map(l -> new Basin(l.point(), map, visitedPoints))
                .sorted()
                .collect(Collectors.toList());

        return basins
                .subList(0, 3)
                .stream()
                .mapToInt(Basin::size)
                .reduce((a, b) -> a * b)
                .orElse(0);
    }
}
