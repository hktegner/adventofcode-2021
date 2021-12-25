package puzzle;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

public class OctopusEvolver {

  public static final int FLASH_THRESHOLD = 9;
  private final LevelMap energyLevelsMap;

  public OctopusEvolver(LevelMap map) {
    this.energyLevelsMap = map;
  }

  /**
   * Simulate the evolution of the Dumbo Octopusses by making a number of steps in their evolutional
   * cycle from the current state.
   *
   * @param stepCount how many steps to simulate
   * @return how many flashes occurred
   */
  public int evolveSteps(int stepCount) {
    return IntStream.range(0, stepCount)
        .map(stepIndex -> {
          // sub-step 1: increase all energy levels by 1
          visitPositions(energyLevelsMap::increaseLevelAt);

          // sub-step 2: level > 9 -> flash (mark), increase neighbours by 1
          //             continue until no more levels > 9 (and not marked)
          while (countPositions(this::isFlashable) > 0) {
            visitPositions(this::flash);
          }

          // sub-step 3: reset levels > 9 to 0 (unmark)
          //   - count flashes (count ismarked)
          var flashes = countPositions(this::isFlashed);
          //   - zeroise energy level
          visitPositions(this::resetFlashed);
          return flashes;
        }).sum();
  }

  boolean isFlashed(int x, int y) {
    return energyLevelsMap.isMarked(x, y);
  }

  void resetFlashed(int x, int y) {
    if (isFlashed(x, y)) {
      energyLevelsMap.resetLevelAt(x, y);
      energyLevelsMap.unmark(x, y);
    }
  }

  boolean isFlashable(int x, int y) {
    return energyLevelsMap.levelAt(x, y) > FLASH_THRESHOLD
        && !energyLevelsMap.isMarked(x, y);
  }

  void flash(int x, int y) {
    if (energyLevelsMap.levelAt(x, y) > FLASH_THRESHOLD && !energyLevelsMap.isMarked(x, y)) {
      energyLevelsMap.mark(x, y);
      // above
      if (energyLevelsMap.isOnMap(x - 1, y - 1)) {
        energyLevelsMap.increaseLevelAt(x - 1, y - 1);
      }
      if (energyLevelsMap.isOnMap(x, y - 1)) {
        energyLevelsMap.increaseLevelAt(x, y - 1);
      }
      if (energyLevelsMap.isOnMap(x + 1, y - 1)) {
        energyLevelsMap.increaseLevelAt(x + 1, y - 1);
      }
      // sides
      if (energyLevelsMap.isOnMap(x - 1, y)) {
        energyLevelsMap.increaseLevelAt(x - 1, y);
      }
      if (energyLevelsMap.isOnMap(x + 1, y)) {
        energyLevelsMap.increaseLevelAt(x + 1, y);
      }
      // below
      if (energyLevelsMap.isOnMap(x - 1, y + 1)) {
        energyLevelsMap.increaseLevelAt(x - 1, y + 1);
      }
      if (energyLevelsMap.isOnMap(x, y + 1)) {
        energyLevelsMap.increaseLevelAt(x, y + 1);
      }
      if (energyLevelsMap.isOnMap(x + 1, y + 1)) {
        energyLevelsMap.increaseLevelAt(x + 1, y + 1);
      }
    }
  }

  void visitPositions(BiConsumer<Integer, Integer> positionVisitor) {
    for (int y = 0; y < energyLevelsMap.getMaxHeight(); y++) {
      for (int x = 0; x < energyLevelsMap.getMaxWidth(); x++) {
        positionVisitor.accept(x, y);
      }
    }
  }

  public int countFlashed() {
    return countPositions((x,y) -> energyLevelsMap.levelAt(x,y) == 0);
  }

  int countPositions(BiPredicate<Integer, Integer> filter) {
    int count = 0;
    for (int y = 0; y < energyLevelsMap.getMaxHeight(); y++) {
      for (int x = 0; x < energyLevelsMap.getMaxWidth(); x++) {
        if (filter.test(x, y)) {
          count++;
        }
      }
    }
    return count;
  }

}
