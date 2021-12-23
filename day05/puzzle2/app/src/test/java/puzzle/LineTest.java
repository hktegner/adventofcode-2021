package puzzle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LineTest {

    public static Stream<Arguments> fromData() {
        return Stream.of(
                Arguments.of("1,1 -> 1,2", new Line(1, 1, 1, 2)),
                Arguments.of("120,231 -> 99,612", new Line(120, 231, 99, 612))
        );
    }

    @ParameterizedTest
    @MethodSource("fromData")
    void from(String descriptor, Line expectedLine) {
        assertEquals(expectedLine, Line.from(descriptor));
    }

    public static Stream<Arguments> isStraightData() {
        return Stream.of(
                Arguments.of("horizontal", new Line(1, 1, 10, 1), true),
                Arguments.of("vertical", new Line(1, 1, 10, 1), true),
                Arguments.of("diagonal", new Line(1, 1, 10, 10), false),
                Arguments.of("point", new Line(10, 10, 10, 10), true)
        );
    }

    @ParameterizedTest(name = "{index}: {0} ({argumentsWithNames})")
    @MethodSource("isStraightData")
    void isStraight(String testCase, Line line, boolean expectedIsStraight) {
        assertEquals(expectedIsStraight, line.isStraight(), testCase);
    }

    @Test
    void markOnMap_straight() {
        var map = new Map(3, 3);
        assertEquals(
                """
                        ...
                        ...
                        ...
                        """,
                map.draw()
        );
        new Line(0, 0, 2, 0).markOnMap(map); // top horizontal line
        new Line(2, 0, 2, 2).markOnMap(map); // right vertical line
        assertEquals(
                """
                        112
                        ..1
                        ..1
                        """,
                map.draw()
        );
    }

    @Test
    void markOnMap_diagonal() {
        var map = new Map(3, 3);
        assertEquals(
                """
                        ...
                        ...
                        ...
                        """,
                map.draw()
        );

        Line.from("1,0 -> 2,1").markOnMap(map);
        Line.from("1,0 -> 0,1").markOnMap(map);
        Line.from("0,1 -> 1,2").markOnMap(map);
        Line.from("1,2 -> 2,1").markOnMap(map);

        assertEquals(
                """
                        .2.
                        2.2
                        .2.
                        """,
                map.draw()
        );
    }
}