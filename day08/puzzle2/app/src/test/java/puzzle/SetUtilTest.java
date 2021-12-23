package puzzle;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SetUtilTest {

    @Test
    void union() {
        assertEquals(Set.of(), SetUtil.union(Set.of(), Set.of()));
        assertEquals(Set.of("a", "b"), SetUtil.union(Set.of("a", "b"), Set.of()));
        assertEquals(Set.of("a", "b", "c"), SetUtil.union(Set.of("a"), Set.of("b", "c")));
        assertEquals(Set.of("a", "b", "c"), SetUtil.union(Set.of("a", "b"), Set.of("b", "c")));
    }

    @Test
    void intersection() {
        assertEquals(Set.of(), SetUtil.intersection(Set.of(), Set.of()));
        assertEquals(Set.of(), SetUtil.intersection(Set.of("a"), Set.of("b")));
        assertEquals(Set.of("b"), SetUtil.intersection(Set.of("a", "b"), Set.of("b", "c")));
        assertEquals(Set.of("a", "b"), SetUtil.intersection(Set.of("a", "b", "d"), Set.of("a", "b", "c")));
    }

    @Test
    void diff() {
        assertEquals(Set.of(), SetUtil.diff(Set.of(), Set.of()));
        assertEquals(Set.of(), SetUtil.diff(Set.of("a", "b"), Set.of("a", "b")));
        assertEquals(Set.of("b"), SetUtil.diff(Set.of("a", "b"), Set.of("a")));
        assertEquals(Set.of(), SetUtil.diff(Set.of("a"), Set.of("a", "b")));
    }

    @Test
    void chars() {
        var s1 = Set.of('a', 'b', 'c');
        var s2 = Set.of('b', 'd');

        assertEquals(Set.of('a', 'b', 'c', 'd'), SetUtil.union(s1, s2));
        assertEquals(Set.of('b'), SetUtil.intersection(s1, s2));
        assertEquals(Set.of('a', 'c'), SetUtil.diff(s1, s2));
    }

}