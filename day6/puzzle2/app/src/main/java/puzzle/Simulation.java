package puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Simulation {

    public static final int NEW_FISH_STAGE = 8;
    public static final int MATURE_RESTART_STAGE = 6;
    private final List<SpawnStage> stages = new ArrayList<>();

    /**
     * Create a simulation with {@link LanternFish} with the given initial spawn timers.
     */
    public Simulation(List<Integer> spawnTimers) {
        IntStream
                .rangeClosed(0, 8)
                .forEach(stageIndex -> stages.add(new SpawnStage(stageIndex, 0)));
        spawnTimers.forEach(stageIndex -> stages.get(stageIndex).spawnNewFish(1));
    }

    public void runFor(int days) {
        IntStream
                .rangeClosed(1, days)
                .forEach(i -> evolveFish());
    }

    private void evolveFish() {
        var newStages = new ArrayList<SpawnStage>();
        IntStream.rangeClosed(0, 8)
                .forEach(i -> newStages.add(new SpawnStage(i, 0)));

        // Handle spawning of new fish for stage 0
        // - Spawn new fish at stage 8
        newStages.get(NEW_FISH_STAGE).spawnNewFish(stages.get(0).getNumberOfFish());
        // - Reset fish to stage 6
        stages.get(0).transferToNextStage(newStages.get(MATURE_RESTART_STAGE));

        assert stages.get(0).getNumberOfFish() == 0;

        // Handle evolution of stage 1..8
        IntStream.rangeClosed(1, 8)
                .forEach(i -> stages.get(i).transferToNextStage(newStages.get(i - 1)));

        stages.clear();
        stages.addAll(newStages);
    }

    public long fishCount() {
        return stages
                .stream()
                .map(SpawnStage::getNumberOfFish)
                .reduce(Long::sum).orElse(0L);
    }

    public long fishAtStage(int stageIndex) {
        if (stageIndex < 0 || stageIndex > 8) {
            throw new IllegalArgumentException("Invalid stage index " + stageIndex);
        }
        return stages.get(stageIndex).getNumberOfFish();
    }

    public List<Integer> fishes() {
        var list = new ArrayList<Integer>();
        stages.forEach(s -> {
            LongStream
                    .rangeClosed(1, s.getNumberOfFish())
                    .forEach(count -> list.add(s.getStageIndex()));
        });
        return list;
    }

}
