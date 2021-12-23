package puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void mark() {
        var map = new Map(10, 10);
        setAndValidateMark(map, 0, 0);
        setAndValidateMark(map, 1, 0);
        setAndValidateMark(map, 2, 0);
        setAndValidateMark(map, 3, 0);

        setAndValidateMark(map, 9, 0);
        setAndValidateMark(map, 0, 1);
        setAndValidateMark(map, 0, 2);
        setAndValidateMark(map, 0, 3);
    }

    @Test
    void draw() {
        var map = new Map(3, 3);
        map.mark(0, 0);

        map.mark(1, 0);
        map.mark(1, 0);

        map.mark(0, 1);
        map.mark(0, 1);
        map.mark(0, 1);

        map.mark(1, 1);
        map.mark(1, 1);
        map.mark(1, 1);
        map.mark(1, 1);

        assertEquals("""
                12.
                34.
                ...
                """.trim(),
                map.draw().trim()
                );
    }

    private void setAndValidateMark(Map map, int x, int y) {
        map.mark(x, y);
        assertTrue(map.isMarked(x, y));
    }

}