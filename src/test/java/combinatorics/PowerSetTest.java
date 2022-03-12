package combinatorics;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PowerSetTest {

    @Test
    void empty_set() {
        assertEquals(Set.of(emptySet()), PowerSet.from(emptySet()));
    }

    @Test
    void single_element() {
        assertEquals(Set.of(emptySet(), Set.of(1)), PowerSet.from(Set.of(1)));
    }

    @Test
    void two_elements() {
        assertEquals(
            Set.of(
                emptySet(),
                Set.of(1),
                Set.of(2),
                Set.of(1, 2)
            ),
            PowerSet.from(Set.of(1, 2))
        );
    }

    @Test
    void three_elements() {
        assertEquals(
            Set.of(
                emptySet(),
                Set.of(1),
                Set.of(2),
                Set.of(3),
                Set.of(1, 2),
                Set.of(1, 3),
                Set.of(2, 3),
                Set.of(1, 2, 3)
            ),
            PowerSet.from(Set.of(1, 2, 3))
        );
    }

    @Test
    void strings_also_work() {
        assertEquals(
            Set.of(
                emptySet(),
                Set.of("1"),
                Set.of("2"),
                Set.of("3"),
                Set.of("1", "2"),
                Set.of("1", "3"),
                Set.of("2", "3"),
                Set.of("1", "2", "3")
            ),
            PowerSet.from(Set.of("1", "2", "3"))
        );
    }

    @Test
    void numer_elements_is_2_to_n() {
        Set<Set<Integer>> powerSet = PowerSet.from(IntStream.range(0, 10).boxed().collect(Collectors.toSet()));
        assertEquals((int) Math.pow(2, 10), powerSet.size());
    }
}