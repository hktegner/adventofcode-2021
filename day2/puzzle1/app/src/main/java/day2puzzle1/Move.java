package day2puzzle1;

public record Move(Direction direction, int amount) {

    /**
     * Returns null for an empty string. Or throws an exception if the format of the instruction
     * is not valid.
     */
    public static Move toMove(String instruction) {
        instruction = instruction == null ? "" : instruction.trim();
        if (instruction.isEmpty()) {
            return null;
        }
        String[] split = instruction.split(" ");
        if (split.length != 2) {
            throw new IllegalStateException(String.format("Instruction %s is not two parts separated by space",
                    instruction));
        }
        try {
            int amount = Integer.parseInt(split[1]);
            var direction = Direction.valueOf(split[0]);
            return new Move(direction, amount);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(String.format("Instruction %s, second part %s, is not a number",
                    instruction, split[1]), e);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(String.format("Instruction %s had an invalid direction %s",
                    instruction, split[0]));
        }
    }
}
