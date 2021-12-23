package puzzle;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.lang.Integer.toBinaryString;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LifeSupportRatingTest {

    public static Stream<Arguments> valueData() {
        return Stream.of(
                Arguments.of(5, 6, 30),
                Arguments.of(0, 0, 0),
                Arguments.of(1, 1, 1),
                Arguments.of(10, 60, 600)
        );
    }

    @ParameterizedTest
    @MethodSource("valueData")
    void value(int o2GenRating, int co2ScrubRating, int expectedValue) {
        assertEquals(
                expectedValue,
                new LifeSupportRating(
                        Bits.valueOf(toBinaryString(o2GenRating)),
                        Bits.valueOf(toBinaryString(co2ScrubRating)))
                        .value()
        );
    }

}