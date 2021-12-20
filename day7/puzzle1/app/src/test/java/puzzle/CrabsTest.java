package puzzle;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CrabsTest {

    @Test
    void fuelToAlignAt() {
        var crabs = new Crabs(List.of(1,2,3));
        assertEquals(3, crabs.fuelToAlignAt(1));
        assertEquals(3, crabs.fuelToAlignAt(3));
        assertEquals(2, crabs.fuelToAlignAt(2));
        assertEquals(6, crabs.fuelToAlignAt(0));
    }

}