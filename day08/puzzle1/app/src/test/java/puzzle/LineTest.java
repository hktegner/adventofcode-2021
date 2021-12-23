package puzzle;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {

    @Test
    void knownDigits() {
        assertEquals(4, new Line("a b c d e f g h i j | abc abc abc abc").knownDisplayDigits());
        assertEquals(3, new Line("a b c d e f g h i j | a abcd abc abc").knownDisplayDigits());
        assertEquals(3, new Line("a b c d e f g h i j | a abcd abcdefg abcdefg").knownDisplayDigits());
        assertEquals(3, new Line("a b c d e f g h i j | a abcd abcdefg abc").knownDisplayDigits());
    }

    @Test
    void decodeDigits() {
//        var line = new Line("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf");
//        line.decodeDigits();
//        assertEquals(
//                Map.of(0, "cagedb",
//                       1, "ab",
//                        2, "gcdfa",
//                        3, "fbcad",
//                        4, "eafb",
//                        5, "cdfbe",
//                        6, "cdfgeb",
//                        7, "dab",
//                        8, "acedgfb",
//                        9, "cefabd"),
//                line.decodedDigitsMap()
//        );
    }

}
