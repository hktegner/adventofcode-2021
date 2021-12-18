package puzzle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Game {

    private final List<Integer> drawOrder;
    private final List<Board> boards;

    public Game(List<Integer> drawOrder, List<Board> boards) {
        this.drawOrder = drawOrder;
        this.boards = boards;
    }

    public static Game from(String resourceName) {
        List<String> lines = readLines(resourceName);
        List<Integer> drawOrder =
                Arrays.stream(lines.remove(0).split(","))
                        .map(Integer::valueOf)
                        .collect(Collectors.toList());
        ArrayList<Board> boards = new ArrayList<>();
        int boardIndex = 0;
        while (lines.size() > 0) {
            boards.add(Board.from(lines, boardIndex++));
        }
        return new Game(drawOrder, boards);
    }

    private static List<String> readLines(String resourceName) {
        try {
            var input = Game.class.getClassLoader().getResourceAsStream(resourceName);
            if (input == null) {
                throw new IllegalStateException("Could not read resource");
            }
            var content = new String(input.readAllBytes());
            return content
                    .lines()
                    .filter(Predicate.not(String::isBlank))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read board file", e);
        }
    }

    /**
     * Plays the game of Bingo, drawing numbers according to the draw order,
     * until one board has a complete row or column.
     * @return the winning board if there was a winner
     */
    public Optional<Board> play() {
        boards.forEach(Board::reset);

        for (var number : drawOrder) {
            for (var board: boards) {
                board.mark(number);
                if (board.isAnyLineMarked()) {
                    return Optional.of(board);
                }
            }
        }

        // Nobody one, all numbers played.
        return Optional.empty();
    }
}
