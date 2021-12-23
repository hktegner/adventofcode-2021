package puzzle;

public record PowerConsumption (Bits gamma, Bits epsilon) {

    public int value() {
        return gamma.value() * epsilon.value();
    }

}
