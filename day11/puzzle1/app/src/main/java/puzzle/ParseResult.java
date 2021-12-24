package puzzle;

record ParseResult(State state, char lastProcessed, String completionString) {

    public static ParseResult of(State state, char lastProcessed, String completionString) {
        return new ParseResult(state, lastProcessed, completionString);
    }

    public int errorPoints() {
        return state.equals(State.CORRUPTED) ?
                errorScoreForChar(lastProcessed) : 0;
    }

    private int errorScoreForChar(char lastProcessed) {
        return switch (lastProcessed) {
            case ')' -> 3;
            case ']' -> 57;
            case '}' -> 1197;
            case '>' -> 25137;
            default -> 0;
        };
    }

    public String completionString() {
        return completionString;
    }

    public long completionScore() {
        if (completionString == null || completionString.isEmpty()) {
            return 0L;
        }
        long total = 0L;
        for (char ch : completionString.toCharArray()) {
            total *= 5L;
            total += completionScoreForChar(ch);
        }
        return total;
    }

    private int completionScoreForChar(char ch) {
        return switch (ch) {
            case ')' -> 1;
            case ']' -> 2;
            case '}' -> 3;
            case '>' -> 4;
            default -> throw new IllegalArgumentException("Invalid character to score " + ch);
        };
    }
}
