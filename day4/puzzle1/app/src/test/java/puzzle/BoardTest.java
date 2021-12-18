package puzzle;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardTest {

    private Board createBoard(String... lines) {
        var linesList = new ArrayList<>(Arrays.asList(lines));
        return Board.from(linesList, 0);
    }

    @Test
    void mark_isMarked() {
        var board = createBoard(
                "1  2  3  4  5",
                " 6  7  8  9 10",
                "11 12 13 14 15",
                "16 17 18 19 20",
                "21 22 23 24 25"
        );
        board.mark(6);
        board.mark(7);
        board.mark(8);
        board.mark(9);
        board.mark(10);

        assertFalse(board.isMarked(0, 0));
        assertFalse(board.isMarked(0, 1));
        assertFalse(board.isMarked(2, 0));
        assertFalse(board.isMarked(2, 4));
        assertFalse(board.isMarked(4, 4));
        assertTrue(board.isMarked(1, 0));
        assertTrue(board.isMarked(1, 1));
        assertTrue(board.isMarked(1, 2));
        assertTrue(board.isMarked(1, 3));
        assertTrue(board.isMarked(1, 4));
    }

    @Test
    void isRowMarked() {
        var board = createBoard(
                "1  2  3  4  5",
                " 6  7  8  9 10",
                "11 12 13 14 15",
                "16 17 18 19 20",
                "21 22 23 24 25"
        );
        // mark row 2
        board.mark(11);
        board.mark(12);
        board.mark(13);
        board.mark(14);
        board.mark(15);
        // and a smattering of others
        board.mark(10);
        board.mark(16);
        board.mark(19);
        board.mark(24);

        assertFalse(board.isRowMarked(0));
        assertFalse(board.isRowMarked(1));
        assertFalse(board.isRowMarked(3));
        assertFalse(board.isRowMarked(4));
        assertTrue(board.isRowMarked(2));
    }

    @Test
    void totalUnmarkedNumbers() {
        var board = createBoard(
                "1  2  3  4  5",
                " 6  7  8  9 10",
                "11 12 13 14 15",
                "16 17 18 19 20",
                "21 22 23 24 25"
        );
        IntStream.range(6,26).forEach(board::mark);
        assertEquals(25, board.getLastMarked());
        assertEquals(15, board.totalUnmarkedNumbers());
    }

    @Test
    void isColumnMarked() {
        var board = createBoard(
                "1  2  3  4  5",
                " 6  7  8  9 10",
                "11 12 13 14 15",
                "16 17 18 19 20",
                "21 22 23 24 25"
        );
        // mark col4
        board.mark(5);
        board.mark(10);
        board.mark(15);
        board.mark(20);
        board.mark(25);
        // and a smattering of others
        board.mark(6);
        board.mark(12);
        board.mark(3);
        board.mark(21);

        assertFalse(board.isColumnMarked(0));
        assertFalse(board.isColumnMarked(1));
        assertFalse(board.isColumnMarked(2));
        assertFalse(board.isColumnMarked(3));
        assertTrue(board.isColumnMarked(4));
    }

    @Test
    void isAnyLineMarked() {
        var board = createBoard(
                "1  2  3  4  5",
                " 6  7  8  9 10",
                "11 12 13 14 15",
                "16 17 18 19 20",
                "21 22 23 24 25"
        );
        // mark almost all of row 1
        board.mark(1);
        board.mark(2);
        board.mark(3);
        board.mark(4);
        // mark all of col 2
        board.mark(3);
        board.mark(8);
        board.mark(13);
        board.mark(18);
        board.mark(23);

        assertTrue(board.isAnyLineMarked());
    }

    @Test
    void getLastMarked() {
        var board = createBoard(
                "1  2  3  4  5",
                " 6  7  8  9 10",
                "11 12 13 14 15",
                "16 17 18 19 20",
                "21 22 23 24 25"
        );
        assertEquals(Board.INVALID_NUMBER, board.getLastMarked());
        board.mark(1);
        assertEquals(1, board.getLastMarked());
        board.mark(16);
        assertEquals(16, board.getLastMarked());
    }
}