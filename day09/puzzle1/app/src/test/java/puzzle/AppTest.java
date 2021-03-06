/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void totalRiskOfLowPoints() {
        assertEquals(15, new App("test_input_sample_15.txt").totalRiskOfLowPoints());
    }

    @Test
    void productOfThreeBiggestBasins() {
        assertEquals(1134, new App("test_input_sample_15.txt").productOfThreeBiggestBasins());
    }

}
