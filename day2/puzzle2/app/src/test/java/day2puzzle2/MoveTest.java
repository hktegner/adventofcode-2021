package day2puzzle2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MoveTest {

    @ParameterizedTest(name = "{index}: \"{0}\"")
    @NullSource
    @EmptySource
    void toMove_nullLike(String instruction) {
        var actualMove = Move.toMove(instruction);
        assertNull(actualMove);
    }

    @ParameterizedTest
    @MethodSource("toMoveTestData")
    void toMove_valid(String instruction, Move expectedMove) {
        var actualMove = Move.toMove(instruction);
        assertEquals(expectedMove, actualMove);
    }

    public static Stream<Arguments> toMoveTestData() {
        return Stream.of(
                Arguments.of("forward 5", new Move(Direction.forward, 5)),
                Arguments.of("up 2", new Move(Direction.up, 2)),
                Arguments.of("down 205839", new Move(Direction.down, 205839))
        );
    }

    @Test
    void direction() {
        assertEquals(Direction.forward, new Move(Direction.forward, 5).direction());
    }

    @Test
    void amount() {
        assertEquals(3, new Move(Direction.down, 3).amount());
    }
}