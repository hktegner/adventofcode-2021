package puzzle;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LevelMapTest {

  private LevelMap map;

  @BeforeEach
  void beforeEach() {
    map = new LevelMap(
        List.of(
            List.of(0, 1, 2),
            List.of(1, 2, 1),
            List.of(2, 1, 0)
        )
    );
  }

  @Test
  void levelAt() {
    assertThat(map.levelAt(0, 0), equalTo(0));
    assertThat(map.levelAt(1, 0), equalTo(1));
    assertThat(map.levelAt(2, 0), equalTo(2));
    assertThat(map.levelAt(0, 1), equalTo(1));
    assertThat(map.levelAt(1, 1), equalTo(2));
    assertThat(map.levelAt(2, 1), equalTo(1));
    assertThat(map.levelAt(0, 2), equalTo(2));
    assertThat(map.levelAt(1, 2), equalTo(1));
    assertThat(map.levelAt(2, 2), equalTo(0));
  }

  @Test
  void isOnMap() {
    assertTrue(map.isOnMap(0, 0));
    assertTrue(map.isOnMap(1, 0));
    assertTrue(map.isOnMap(2, 0));
    assertTrue(map.isOnMap(0, 1));
    assertTrue(map.isOnMap(1, 1));
    assertTrue(map.isOnMap(2, 1));
    assertTrue(map.isOnMap(0, 2));
    assertTrue(map.isOnMap(1, 2));
    assertTrue(map.isOnMap(2, 2));

    assertFalse(map.isOnMap(-1, -1));
    assertFalse(map.isOnMap(3, 3));
    assertFalse(map.isOnMap(-1, 3));
    assertFalse(map.isOnMap(3, -1));
  }

  @Test
  void testToString() {
    assertEquals(
        """
            012
            121
            210
            """,
        map.toString()
    );
  }

  @Test
  void increaseLevelAt() {
    map.resetAll();

    map.increaseLevelAt(0, 0);
    map.increaseLevelAt(0, 1);
    map.increaseLevelAt(0, 2);

    map.increaseLevelAt(1, 0);
    map.increaseLevelAt(1, 1);
    map.increaseLevelAt(1, 2);
    map.increaseLevelAt(1, 0);
    map.increaseLevelAt(1, 1);
    map.increaseLevelAt(1, 2);

    map.increaseLevelAt(2, 0);
    map.increaseLevelAt(2, 1);
    map.increaseLevelAt(2, 2);
    map.increaseLevelAt(2, 0);
    map.increaseLevelAt(2, 1);
    map.increaseLevelAt(2, 2);
    map.increaseLevelAt(2, 0);
    map.increaseLevelAt(2, 1);
    map.increaseLevelAt(2, 2);

    assertEquals(1, map.levelAt(0, 0));
    assertEquals(1, map.levelAt(0, 1));
    assertEquals(1, map.levelAt(0, 2));
    assertEquals(2, map.levelAt(1, 0));
    assertEquals(2, map.levelAt(1, 1));
    assertEquals(2, map.levelAt(1, 2));
    assertEquals(3, map.levelAt(2, 0));
    assertEquals(3, map.levelAt(2, 1));
    assertEquals(3, map.levelAt(2, 2));
  }

  @Test
  void resetLevelAt() {
    map.resetLevelAt(0, 0);
    map.resetLevelAt(1, 0);
    map.resetLevelAt(2, 0);

    assertEquals(0, map.levelAt(0, 0));
    assertEquals(0, map.levelAt(1, 0));
    assertEquals(0, map.levelAt(2, 0));
    assertEquals(1, map.levelAt(0, 1));
    assertEquals(2, map.levelAt(1, 1));
    assertEquals(1, map.levelAt(2, 1));
    assertEquals(2, map.levelAt(0, 2));
    assertEquals(1, map.levelAt(1, 2));
    assertEquals(0, map.levelAt(2, 2));
  }

  @Test
  void mark_isMarked() {
    map.mark(0, 0);
    map.mark(1, 1);
    map.mark(2, 2);

    assertTrue(map.isMarked(0, 0));
    assertFalse(map.isMarked(1, 0));
    assertFalse(map.isMarked(2, 0));
    assertFalse(map.isMarked(0, 1));
    assertTrue(map.isMarked(1, 1));
    assertFalse(map.isMarked(2, 1));
    assertFalse(map.isMarked(0, 2));
    assertFalse(map.isMarked(1, 2));
    assertTrue(map.isMarked(2, 2));
  }

  @Test
  void unmarkAll() {
    map.mark(0, 0);
    map.mark(1, 1);
    map.mark(2, 2);

    map.resetAll();
    assertFalse(map.isMarked(0, 0));
    assertFalse(map.isMarked(1, 0));
    assertFalse(map.isMarked(2, 0));
    assertFalse(map.isMarked(0, 1));
    assertFalse(map.isMarked(1, 1));
    assertFalse(map.isMarked(2, 1));
    assertFalse(map.isMarked(0, 2));
    assertFalse(map.isMarked(1, 2));
    assertFalse(map.isMarked(2, 2));
  }

}