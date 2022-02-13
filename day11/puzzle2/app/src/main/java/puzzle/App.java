package puzzle;

import java.util.function.Predicate;
import java.util.stream.Collectors;

public class App {

  private final LevelMap map;
  private final OctopusEvolver evolver;

  public static void main(String[] args) {
    var app = new App("input.txt");
    System.out.printf("After %d steps, all octopuses flashed", app.firstStepWhenAllFlash());
  }

  public App(String resourceName) {
    var numberMatrix = AppUtil.linesFromResource(resourceName)
        .stream()
        .map(String::trim)
        .filter(Predicate.not(String::isEmpty))
        .map(AppUtil::toIntList)
        .collect(Collectors.toList());
    map = new LevelMap(numberMatrix);
    evolver = new OctopusEvolver(map);
  }

  public int firstStepWhenAllFlash() {
    var steps = 0;
    while (evolver.countFlashed() != 100) {
      evolver.evolveSteps(1);
      steps++;
    }
    return steps;
  }
}