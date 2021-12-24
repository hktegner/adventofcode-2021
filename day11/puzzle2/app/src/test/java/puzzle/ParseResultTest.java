package puzzle;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static puzzle.ParseResult.of;
import static puzzle.State.INCOMPLETE;

class ParseResultTest {

    @Test
    void completionScore() {
        // Single-character scores
        assertThat(of(INCOMPLETE, '(', ")").completionScore(), equalTo(1L));
        assertThat(of(INCOMPLETE, '[', "]").completionScore(), equalTo(2L));
        assertThat(of(INCOMPLETE, '{', "}").completionScore(), equalTo(3L));
        assertThat(of(INCOMPLETE, '<', ">").completionScore(), equalTo(4L));

        // Two-character scores
        assertThat(of(INCOMPLETE, '(', ")>").completionScore(), equalTo(9L));
        assertThat(of(INCOMPLETE, '{', "})").completionScore(), equalTo(16L));
        assertThat(of(INCOMPLETE, '{', "}]").completionScore(), equalTo(17L));

        // Three-character scores
        assertThat(of(INCOMPLETE, '{', "})]").completionScore(), equalTo(82L));

        // Four character scores
        assertThat(of(INCOMPLETE, '{', "})]>").completionScore(), equalTo(414L));
    }

    @Test
    void bug1() {
        var result = ParseResult.of(INCOMPLETE, '{', "})]}]>)>}>)))");
        assertThat(result.completionScore(), equalTo(807764906L));
    }
}