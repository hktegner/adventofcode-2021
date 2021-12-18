package puzzle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BitsTest {

    @ParameterizedTest
    @NullSource
    @EmptySource
    void valueOf_nullLike(String binaryString) {
        assertThrows(
                NumberFormatException.class,
                () -> Bits.valueOf(binaryString)
        );
    }

    @ParameterizedTest
    @MethodSource("valueOfData")
    void valueOf_valid(String binaryString, int numericValue) {
        assertEquals(numericValue, Bits.valueOf(binaryString).value());
    }

    static Stream<Arguments> valueOfData() {
        return Stream.of(
                Arguments.of("0", 0),
                Arguments.of("00", 0),
                Arguments.of("00000000", 0),
                Arguments.of("000000000000", 0),
                Arguments.of("0101", 5)
        );
    }

    @Test
    void isZero() {
        var bits = Bits.valueOf("01010101");
        assertTrue(bits.isZero(1));
        assertTrue(bits.isZero(3));
        assertTrue(bits.isZero(5));
        assertTrue(bits.isZero(7));
        assertFalse(bits.isZero(0));
        assertFalse(bits.isZero(2));
        assertFalse(bits.isZero(4));
        assertFalse(bits.isZero(6));
    }

    @Test
    void isOne() {
        var bits = Bits.valueOf("01010101");
        assertTrue(bits.isOne(0));
        assertTrue(bits.isOne(2));
        assertTrue(bits.isOne(4));
        assertTrue(bits.isOne(6));
        assertFalse(bits.isOne(1));
        assertFalse(bits.isOne(3));
        assertFalse(bits.isOne(5));
        assertFalse(bits.isOne(7));
    }

    @Test
    void charAt() {
        var bits = Bits.valueOf("00001111");
        assertEquals('1', bits.charAt(0));
        assertEquals('1', bits.charAt(1));
        assertEquals('1', bits.charAt(2));
        assertEquals('1', bits.charAt(3));
        assertEquals('0', bits.charAt(4));
        assertEquals('0', bits.charAt(5));
        assertEquals('0', bits.charAt(6));
        assertEquals('0', bits.charAt(7));
    }
}