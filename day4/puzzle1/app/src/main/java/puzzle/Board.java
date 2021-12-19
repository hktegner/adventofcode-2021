package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    public static final int INVALID_NUMBER = 0;
    public static final String REGEX_SPACES = "\\s+";
    private final int boardIndex;
    private boolean isComplete = false;

    /**
     * Creates a Bingo board from the lines of text passed in.
     * Will remove the lines that are read, leaving any lines
     * still in the list.
     */
    public static Board from(List<String> lines, int boardIndex) {
        var boardLines = new ArrayList<String>();
        for (var i = 0; i < 5; i++) {
            boardLines.add(lines.remove(0));
        }
        var rows =
                boardLines
                        .stream()
                        .map(Board::toNumbers)
                        .collect(Collectors.toList());
        return new Board(boardIndex, rows.get(0), rows.get(1), rows.get(2), rows.get(3), rows.get(4));
    }

    private static List<Integer> toNumbers(String boardLine) {
        return Arrays
                .stream(boardLine.trim().split(REGEX_SPACES))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    private static void populateRow(int rowIndex, List<Integer> numbers, int[][] cells) {
        for (int colIndex = 0; colIndex < 5; colIndex++) {
            cells[rowIndex][colIndex] = numbers.get(colIndex);
        }
    }

    /**
     * rowIndex, colIndex
     */
    private final int[][] cells = new int[5][5];

    /**
     * rowIndex, colIndex
     */
    private final boolean[][] marked = new boolean[5][5];

    private int lastMarked = INVALID_NUMBER;

    private Board(
            int boardIndex,
            List<Integer> row0,
            List<Integer> row1,
            List<Integer> row2,
            List<Integer> row3,
            List<Integer> row4
    ) {
        this.boardIndex = boardIndex;
        populateRow(0, row0, cells);
        populateRow(1, row1, cells);
        populateRow(2, row2, cells);
        populateRow(3, row3, cells);
        populateRow(4, row4, cells);
    }

    public int index() {
        return boardIndex;
    }

    public int number(int rowIndex, int colIndex) {
        return cells[rowIndex][colIndex];
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void mark(int number) {
        if (isComplete) {
            return;
        }
        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            for (int colIndex = 0; colIndex < 5; colIndex++) {
                if (cells[rowIndex][colIndex] == number) {
                    marked[rowIndex][colIndex] = true;
                    lastMarked = number;
                }
            }
        }
        if (isAnyLineMarked()) {
            isComplete = true;
        }
    }

    public boolean isMarked(int rowIndex, int colIndex) {
        return marked[rowIndex][colIndex];
    }

    public boolean isRowMarked(int rowIndex) {
        for (int colIndex = 0; colIndex < 5; colIndex++) {
            if (!marked[rowIndex][colIndex]) {
                return false;
            }
        }
        return true;
    }

    public boolean isColumnMarked(int colIndex) {
        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            if (!marked[rowIndex][colIndex]) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnyLineMarked() {
        return isRowMarked(0)
                || isRowMarked(1)
                || isRowMarked(2)
                || isRowMarked(3)
                || isRowMarked(4)
                || isColumnMarked(0)
                || isColumnMarked(1)
                || isColumnMarked(2)
                || isColumnMarked(3)
                || isColumnMarked(4);
    }

    public int getLastMarked() {
        return lastMarked;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        builder.append("Board (#").append(boardIndex).append(", lastMarked=").append(lastMarked).append(")\n");

        for (var rowIndex = 0; rowIndex < 5; rowIndex++) {
            for (var colIndex = 0; colIndex < 5; colIndex++) {
                if (isMarked(rowIndex, colIndex)) {
                    builder.append(String.format("[%2d] ", cells[rowIndex][colIndex]));
                } else {
                    builder.append(String.format(" %2d  ", cells[rowIndex][colIndex]));
                }
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public void reset() {
        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            for (int colIndex = 0; colIndex < 5; colIndex++) {
                marked[rowIndex][colIndex] = false;
            }
        }
        lastMarked = INVALID_NUMBER;
        isComplete = false;
    }

    public int totalUnmarkedNumbers() {
        int sum = 0;
        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            for (int colIndex = 0; colIndex < 5; colIndex++) {
                if (!marked[rowIndex][colIndex]) {
                    sum += cells[rowIndex][colIndex];
                }
            }
        }
        return sum;
    }
}
