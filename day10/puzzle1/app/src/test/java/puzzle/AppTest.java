/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package puzzle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void findTotalErrorScore() {
        var app = new App("test_input_sample_26397.txt");
        assertEquals(26397, app.findTotalErrorScore());
    }

    @ParameterizedTest
    @MethodSource("findMiddleCompletionScoreData")
    void findMiddleCompletionScore() {
        var app = new App("test_input_sample_26397.txt");
        assertEquals(288957, app.findMiddleCompletionScore());
    }

    public static Stream<Arguments> findMiddleCompletionScoreData() {
        return Stream.of(
                Arguments.of("test_input_sample_26397.txt", 288957),
                Arguments.of("test_input_3lines.txt", 2)
        );
    }
}
