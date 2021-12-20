package puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpawnStageTest {

    @Test
    void constructor() {
        var stage = new SpawnStage(1, 5);
        assertEquals(1, stage.getStageIndex());
        assertEquals(5, stage.getNumberOfFish());
    }

    @Test
    void spawnNewFish() {
        var stage = new SpawnStage(3, 10);
        stage.spawnNewFish(5);
        assertEquals(15, stage.getNumberOfFish());
        stage.spawnNewFish(7);
        assertEquals(22, stage.getNumberOfFish());
    }

    @Test
    void transferToNewStage() {
        var fromStage = new SpawnStage(3, 100);
        var toStage = new SpawnStage(2, 200);
        fromStage.transferToNextStage(toStage);
        assertEquals(0, fromStage.getNumberOfFish());
        assertEquals(300, toStage.getNumberOfFish());
    }

}