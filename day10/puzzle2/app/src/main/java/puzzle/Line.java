package puzzle;

import java.util.Stack;

import static puzzle.State.INCOMPLETE;
import static puzzle.State.VALID;

public class Line implements Comparable<Line> {

    private final ParseResult parseResult;

    private final String content;

    public Line(String content) {
        this.content = content;
        this.parseResult = parse(content);
    }

    public boolean isCorrupted() {
        return parseResult.state().equals(State.CORRUPTED);
    }

    public boolean isValid() {
        return parseResult.state().equals(VALID);
    }

    public boolean isIncomplete() {
        return parseResult.state().equals(INCOMPLETE);
    }

    private static ParseResult parse(String content) {
        var stack = new Stack<Character>();
        char lastChar = ' ';
        for (var ch : content.toCharArray()) {
            lastChar = ch;
            switch (ch) {
                case '{':
                case '(':
                case '<':
                case '[':
                    stack.push(ch);
                    break;
                case '}':
                case ')':
                case '>':
                case ']':
                    if (!stack.isEmpty() && stack.peek() == reverseChar(ch)) {
                        stack.pop();
                    } else {
                        return ParseResult.of(State.CORRUPTED, ch, null);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid character " + ch);
            }
        }
        return stack.isEmpty() ? ParseResult.of(VALID, lastChar, null)
                : ParseResult.of(INCOMPLETE, lastChar, buildCompletionString(stack));
    }

    private static String buildCompletionString(Stack<Character> stack) {
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(reverseChar(stack.pop()));
        }
        return result.toString();
    }

    private static Character reverseChar(char ch) {
        return switch (ch) {
            case '{' -> '}';
            case '[' -> ']';
            case '<' -> '>';
            case '(' -> ')';
            case '}' -> '{';
            case ']' -> '[';
            case '>' -> '<';
            case ')' -> '(';
            default -> throw new IllegalArgumentException("Invalid character " + ch);
        };
    }

    public ParseResult parseResult() {
        return parseResult;
    }

    public String completionString() {
        return parseResult.completionString();
    }

    public long completionScore() {
        return parseResult.completionScore();
    }

    public String toString() {
        return String.format("Line(content=[%s], compStr=[%s], compScore=[%d])",
                content, completionString(), completionScore());
    }

    @Override
    public int compareTo(Line other) {
        long diff = completionScore() - other.completionScore();
        return (int) Math.signum(diff);
    }
}

