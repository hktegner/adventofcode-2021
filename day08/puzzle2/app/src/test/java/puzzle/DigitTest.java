package puzzle;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DigitTest {

    @Test
    void cons() {
        var digit = new Digit("abcdef");
        assertEquals("abcdef", digit.digitCode());
    }

    @Test
    void segmentCodes() {
        var digit = new Digit("abcdef");
        assertEquals(Set.of('a', 'b', 'c', 'd', 'e', 'f'), digit.segmentCodes());
    }

    @Test
    void identified() {
        var digit = new Digit("abc");
        assertFalse(digit.isIdentified());
        digit.identifyAs(7);
        assertTrue(digit.isIdentified());
    }

}