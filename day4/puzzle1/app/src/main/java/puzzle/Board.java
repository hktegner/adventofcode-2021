package puzzle;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    public static final int INVALID_NUMBER = 0;

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
            List<Integer> row0,
            List<Integer> row1,
            List<Integer> row2,
            List<Integer> row3,
            List<Integer> row4
    ) {
        populateRow(0, row0, cells);
        populateRow(1, row1, cells);
        populateRow(2, row2, cells);
        populateRow(3, row3, cells);
        populateRow(4, row4, cells);
    }

    public void mark(int number) {
        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            for (int colIndex = 0; colIndex < 5; colIndex++) {
                if (cells[rowIndex][colIndex] == number) {
                    marked[rowIndex][colIndex] = true;
                    lastMarked = number;
                }
            }
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
        return "Board{" +
                "cells=" + Arrays.toString(cells) +
                ", marked=" + Arrays.toString(marked) +
                ", lastMarked=" + lastMarked +
                '}';
    }

    private static void populateRow(int rowIndex, List<Integer> numbers, int[][] cells) {
        for (int colIndex = 0; colIndex < 5; colIndex++) {
            cells[rowIndex][colIndex] = numbers.get(colIndex);
        }
    }

    /**
     * Creates a Bingo board from the lines of text passed in.
     * Will remove the lines that are read, leaving any lines
     * still in the list.
     */
    public static Board from(List<String> lines) {
        return new Board(
                Arrays.stream(lines.remove(0).split(" "))
                        .map(Integer::valueOf).collect(Collectors.toList()),
                Arrays.stream(lines.remove(0).split(" "))
                        .map(Integer::valueOf).collect(Collectors.toList()),
                Arrays.stream(lines.remove(0).split(" "))
                        .map(Integer::valueOf).collect(Collectors.toList()),
                Arrays.stream(lines.remove(0).split(" "))
                        .map(Integer::valueOf).collect(Collectors.toList()),
                Arrays.stream(lines.remove(0).split(" "))
                        .map(Integer::valueOf).collect(Collectors.toList())
        );
    }
}
