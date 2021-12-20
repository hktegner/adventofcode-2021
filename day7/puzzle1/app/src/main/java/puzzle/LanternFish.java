package puzzle;

import java.util.Optional;

public class LanternFish {

    public static final int TIME_TO_SPAWN = 0;
    public static final int SECOND_SPAWN_TIMER = 6;
    public static final int NEW_FISH_SPAWN_TIMER = 8;
    private int spawnTimer;

    public LanternFish(int timeToSpawn) {
        this.spawnTimer = timeToSpawn;
    }

    public Optional<LanternFish> ageADay() {
        if (spawnTimer == TIME_TO_SPAWN) {
            spawnTimer = SECOND_SPAWN_TIMER;
            return Optional.of(new LanternFish(NEW_FISH_SPAWN_TIMER));
        } else {
            spawnTimer--;
            return Optional.empty();
        }
    }

    public int spawnTimer() {
        return spawnTimer;
    }
}
