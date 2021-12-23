package puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LanternFishTest {

    @Test
    void ageADay() {
        var fish = new LanternFish((byte)3);
        assertFalse(fish.ageADay().isPresent());
        assertEquals(2, fish.spawnTimer());
        assertFalse(fish.ageADay().isPresent());
        assertEquals(1, fish.spawnTimer());
        assertFalse(fish.ageADay().isPresent());
        assertEquals(0, fish.spawnTimer());
        // Time to spawn
        var spawn = fish.ageADay();
        assertTrue(spawn.isPresent());
        assertEquals(LanternFish.NEW_FISH_SPAWN_TIMER, spawn.get().spawnTimer());
        assertEquals(LanternFish.SECOND_SPAWN_TIMER, fish.spawnTimer());
    }

}