package puzzle;

import java.util.HashSet;
import java.util.Set;

public class SetUtil {

    public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
        var result = new HashSet<E>();
        result.addAll(s1);
        result.addAll(s2);
        return result;
    }

    public static <E> Set<E> intersection(Set<E> s1, Set<E> s2) {
        var result = new HashSet<>(s1);
        result.retainAll(s2);
        return result;
    }

    public static <E> Set<E> diff(Set<E> s1, Set<E> s2) {
        var result = new HashSet<>(s1);
        result.removeAll(s2);
        return result;
    }

}
