package puzzle;

/**
 * A group of Lanternfish at a certain stage of spawn cycle.
 */
public class SpawnStage {

    private final int stageIndex;
    private long numberOfFish;

    public SpawnStage(int stageIndex, long initialNumberOfFish) {
        this.stageIndex = stageIndex;
        this.numberOfFish = initialNumberOfFish;
    }

    public int getStageIndex() {
        return stageIndex;
    }

    public void transferToNextStage(SpawnStage nextStage) {
        nextStage.receiveAll(this.transferAll());
    }

    public void spawnNewFish(long newFish) {
        numberOfFish += newFish;
    }

    private void receiveAll(long transferAll) {
        numberOfFish += transferAll;
    }

    private long transferAll() {
        var allFish = numberOfFish;
        numberOfFish = 0L;
        return allFish;
    }

    public long getNumberOfFish() {
        return numberOfFish;
    }
}
