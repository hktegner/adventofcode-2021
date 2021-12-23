package puzzle;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Simulation {

    private final List<LanternFish> fishes;

    /**
     * Create a simulation with {@link LanternFish} with the given initial spawn timers.
     */
    public Simulation(List<Integer> spawnTimers) {
        fishes = spawnTimers.stream().map(LanternFish::new).collect(Collectors.toList());
    }

    public void runFor(int days) {
        IntStream.range(0, days)
                .forEach(i -> {
                            var newFishes =
                                    fishes
                                            .stream()
                                            .map(LanternFish::ageADay)
                                            .filter(Optional::isPresent)
                                            .map(Optional::get)
                                            .collect(Collectors.toList());
                            fishes.addAll(newFishes);
                        }
                );
    }

    public int fishCount() {
        return fishes.size();
    }

}
