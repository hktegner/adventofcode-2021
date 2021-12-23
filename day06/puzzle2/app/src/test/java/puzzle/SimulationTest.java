package puzzle;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimulationTest {

    @Test
    void constructor() {
        var sim = new Simulation(List.of(1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5));
        IntStream
                .rangeClosed(0, 5)
                .forEach(stageIndex -> assertEquals(stageIndex, sim.fishAtStage(stageIndex)));
        IntStream
                .rangeClosed(6, 8)
                .forEach(stageIndex -> assertEquals(0, sim.fishAtStage(stageIndex)));
    }

    @Test
    void fish() {
        List<Integer> spawnTimers = List.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5);
        var sim = new Simulation(spawnTimers);
        assertEquals(spawnTimers, sim.fishes());
    }

    @Test
    void simulate_oneFish_singleDays() {
        var sim = new Simulation(List.of(1));
        assertEquals(List.of(1), sim.fishes());

        sim.runFor(1);
        assertEquals(List.of(0), sim.fishes());

        sim.runFor(1);
        assertEquals(List.of(6, 8), sim.fishes());

        sim.runFor(1);
        assertEquals(List.of(5, 7), sim.fishes());
    }

    @Test
    void simulate_oneFishPerStage_singleDay() {
        var sim = new Simulation(List.of(0,1,2,3,4,5,6,7,8));
        assertEquals(List.of(0,1,2,3,4,5,6,7,8), sim.fishes());

        sim.runFor(1);
        assertEquals(List.of(0,1,2,3,4,5,6,6,7,8), sim.fishes());

        sim.runFor(1);
        assertEquals(List.of(0,1,2,3,4,5,5,6,6,7,8), sim.fishes());

        sim.runFor(1);
        assertEquals(List.of(0,1,2,3,4,4,5,5,6,6,7,8), sim.fishes());

        sim.runFor(1);
        assertEquals(List.of(0,1,2,3,3,4,4,5,5,6,6,7,8), sim.fishes());

        sim.runFor(1);
        assertEquals(List.of(0,1,2,2,3,3,4,4,5,5,6,6,7,8), sim.fishes());

        sim.runFor(1);
        assertEquals(List.of(0,1,1,2,2,3,3,4,4,5,5,6,6,7,8), sim.fishes());

        sim.runFor(1);
        assertEquals(List.of(0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,8), sim.fishes());

        sim.runFor(1);
        assertEquals(List.of(0,0,1,1,2,2,3,3,4,4,5,5,6,6,6,7,8,8), sim.fishes());
    }

    @Test
    void simulate_multipleFish_multipleDays() {
        var sim = new Simulation(List.of(0,1,2,3,4,5,6,7,8));
        sim.runFor(8);
        assertEquals(List.of(0,0,1,1,2,2,3,3,4,4,5,5,6,6,6,7,8,8), sim.fishes());
    }

    @Test
    void sample_problem() {
        var sim = new Simulation(List.of(3,4,3,1,2));
        sim.runFor(256);
        assertEquals(26984457539L, sim.fishCount());
    }
}