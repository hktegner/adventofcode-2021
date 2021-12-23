package puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LineTest {

    @Test
    void knownDigits() {
        var problem = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | ";
        assertEquals(4, new Line(problem + "dab dab dab dab").knownDisplayDigits());
        assertEquals(3, new Line(problem + "a eafb dab dab").knownDisplayDigits());
        assertEquals(3, new Line(problem + "a eafb acedbfg acedbfg").knownDisplayDigits());
        assertEquals(3, new Line(problem + "a eafb acedbfg dab").knownDisplayDigits());
    }

    @Test
    void decodeDigits() {
        var line = new Line("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf");

        assertEquals(8, line.decodedDigit("acedgfb").identifiedAs());
        assertEquals(5, line.decodedDigit("cdfbe").identifiedAs());
        assertEquals(2, line.decodedDigit("gcdfa").identifiedAs());
        assertEquals(3, line.decodedDigit("fbcad").identifiedAs());
        assertEquals(7, line.decodedDigit("dab").identifiedAs());
        assertEquals(9, line.decodedDigit("cefabd").identifiedAs());
        assertEquals(6, line.decodedDigit("cdfgeb").identifiedAs());
        assertEquals(4, line.decodedDigit("eafb").identifiedAs());
        assertEquals(0, line.decodedDigit("cagedb").identifiedAs());
        assertEquals(1, line.decodedDigit("ab").identifiedAs());

        assertEquals(5353, line.displayValue());
    }

}
