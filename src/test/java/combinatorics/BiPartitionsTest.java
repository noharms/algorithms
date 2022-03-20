package combinatorics;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BiPartitionsTest {

    @Test
    void empty_set_returns_empty_set() {
        assertEquals(emptySet(), BiPartitions.from(emptySet()));
    }

    @Test
    void one_element_set_returns_empty_set() {
        assertEquals(emptySet(), BiPartitions.from(emptySet()));
    }

    @Test
    void two_element_set() {
        assertEquals(Set.of(Set.of(Set.of(1), Set.of(2))), BiPartitions.from(Set.of(1, 2)));
    }

    @Test
    void three_element_set() {
        assertEquals(
            Set.of(
                Set.of(Set.of(1), Set.of(2, 3)),
                Set.of(Set.of(1, 2), Set.of(3)),
                Set.of(Set.of(1, 3), Set.of(2))
            ),
            BiPartitions.from(Set.of(1, 2, 3))
        );
    }

    @Test
    void four_element_set() {
        assertEquals(
            Set.of(
                Set.of(Set.of(1), Set.of(2, 3, 4)),
                Set.of(Set.of(1, 4), Set.of(2, 3)),
                Set.of(Set.of(1, 2), Set.of(3, 4)),
                Set.of(Set.of(1, 2, 4), Set.of(3)),
                Set.of(Set.of(1, 3), Set.of(2, 4)),
                Set.of(Set.of(1, 3, 4), Set.of(2)),
                Set.of(Set.of(4), Set.of(1, 2, 3))
            ),
            BiPartitions.from(Set.of(1, 2, 3, 4))
        );
    }

    @Test
    void works_with_strings() {
        assertEquals(
            Set.of(
                Set.of(Set.of("1"), Set.of("2", "3")),
                Set.of(Set.of("1", "2"), Set.of("3")),
                Set.of(Set.of("1", "3"), Set.of("2"))
            ),
            BiPartitions.from(Set.of("1", "2", "3"))
        );
    }

    @Test
    void large_set() {
        int nElements = 8;
        Set<Integer> numbers = new Random().ints(nElements).boxed().collect(Collectors.toSet());
        int nExpected = IntStream.range(1, nElements).reduce((iPrev, i) -> iPrev * 2 + 1).orElseThrow();
        assertEquals(nExpected, BiPartitions.from(numbers).size());
    }
}