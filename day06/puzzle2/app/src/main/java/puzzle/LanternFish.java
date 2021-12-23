package puzzle;

import java.util.Optional;

public class LanternFish {

    public static final byte TIME_TO_SPAWN = 0;
    public static final byte SECOND_SPAWN_TIMER = 6;
    public static final byte NEW_FISH_SPAWN_TIMER = 8;
    private byte spawnTimer;

    public LanternFish(byte timeToSpawn) {
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

    public byte spawnTimer() {
        return spawnTimer;
    }
}
