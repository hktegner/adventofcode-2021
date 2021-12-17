/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package day2puzzle1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    public static Stream<Arguments> findFinalPositionData() {
        return Stream.of(
                Arguments.of("/test_input_nolines_0,0.txt", new Position(0, 0)),
                Arguments.of("/test_input_onlydown_0,10.txt", new Position(0, 10)),
                Arguments.of("/test_input_onlyforward_15,0.txt", new Position(15, 0)),
                Arguments.of("/test_input_allpos_10,10.txt", new Position(10, 10))
        );
    }

    @ParameterizedTest
    @MethodSource("findFinalPositionData")
    void findFinalPosition(String resourceName, Position expectedFinalPosition) {
        var app = new App();
        assertEquals(expectedFinalPosition, app.findFinalPosition(new Position(0,0), resourceName));
    }

}
