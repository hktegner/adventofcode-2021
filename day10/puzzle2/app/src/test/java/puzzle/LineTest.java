package puzzle;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static puzzle.ParseResult.of;
import static puzzle.State.CORRUPTED;
import static puzzle.State.INCOMPLETE;
import static puzzle.State.VALID;

class LineTest {

    @Test
    void incomplete() {
        assertTrue(new Line("<").isIncomplete());
        assertFalse(new Line(">").isIncomplete());
        assertFalse(new Line("<>").isIncomplete());
    }

    @Test
    void corrupt() {
        assertTrue(new Line(">").isCorrupted());
        assertFalse(new Line("{").isCorrupted());
        assertFalse(new Line("()").isCorrupted());
    }

    @Test
    void parseCorrupted() {
        assertEquals(of(CORRUPTED, ')', null), new Line(")").parseResult());
        assertEquals(of(CORRUPTED, ')', null), new Line("<)").parseResult());
        assertEquals(of(CORRUPTED, '>', null), new Line("{>").parseResult());
        assertEquals(of(CORRUPTED, ']', null), new Line("[{]").parseResult());
        assertEquals(of(CORRUPTED, '}', null), new Line("{<[]>(}").parseResult());
    }

    @Test
    void parseIncomplete() {
        assertEquals(of(INCOMPLETE, '{', "}"), new Line("{").parseResult());
        assertEquals(of(INCOMPLETE, '[', "]"), new Line("[").parseResult());
        assertEquals(of(INCOMPLETE, '<', ">"), new Line("<").parseResult());
        assertEquals(of(INCOMPLETE, '{', "}"), new Line("{").parseResult());
        assertEquals(of(INCOMPLETE, ']', "}"), new Line("{()<>[]").parseResult());
        assertEquals(of(INCOMPLETE, ')', ")}"), new Line("{(<[](){}>()").parseResult());
    }

    @Test
    void parseValid() {
        assertEquals(of(VALID, '}', null), new Line("{}").parseResult());
        assertEquals(of(VALID, ']', null), new Line("[]").parseResult());
        assertEquals(of(VALID, '>', null), new Line("<>").parseResult());
        assertEquals(of(VALID, ')', null), new Line("()").parseResult());
        assertEquals(of(VALID, ')', null), new Line("()").parseResult());
    }

    @Test
    void completionString() {
        assertThat(new Line("{}").completionString(), nullValue());
        assertThat(new Line("{").completionString(), equalTo("}"));
        assertThat(new Line("{<").completionString(), equalTo(">}"));
        assertThat(new Line("{<>(").completionString(), equalTo(")}"));
        assertThat(new Line("{<{}{}()([").completionString(), equalTo("])>}"));
    }

    @Test
    void completionStringScore() {
        // Corrupt line = 0
        assertThat(new Line("}").completionScore(), equalTo(0L));

        // Incomplete lines get a score
        // - single character score
        assertThat(new Line("{").completionScore(), equalTo(3L));
        // - multi character score
        assertThat(new Line("{(").completionScore(), equalTo(8L));
    }

    @Test
    void compare() {
        var line1 = new Line("(");
        var line2 = new Line("((");
        var line3 = new Line("(((");

        assertEquals(0, line1.compareTo(line1));
        assertEquals(-1, line1.compareTo(line2));
        assertEquals(1, line3.compareTo(line2));
    }

    @Test
    void bug1() {
        var line = new Line("<(((<{<(<{{[[[()<>]{[]()}]][{<()[]><<>{}>}]}}[<{[{[][]}[<><>]]}{{{(){}}[<>()]}{[[]()]{<>[]}}}>{[({{}");
        assertEquals("})]}]>)>}>)))>", line.completionString());
        assertEquals(4038824534L, line.completionScore());
    }

}