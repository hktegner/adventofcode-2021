/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package puzzle;

import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        List<String> lineDescriptors = AppUtil.linesFromResource("input.txt");
        List<Line> lines = lineDescriptors.stream().map(Line::from).collect(Collectors.toList());
        var map = new Map(1000, 1000);
        lines.forEach(l -> l.markOnMap(map));
        System.out.printf("Locations with more than two vents: %d%n",
                map.count(vents -> vents > 1));
    }

}
