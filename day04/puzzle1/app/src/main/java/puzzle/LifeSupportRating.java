package puzzle;

public record LifeSupportRating(Bits o2GenRating, Bits co2ScrubRating) {

    public int value() {
        return o2GenRating.value() * co2ScrubRating.value();
    }

}
