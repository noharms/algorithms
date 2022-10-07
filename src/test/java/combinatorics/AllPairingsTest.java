package combinatorics;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static combinatorics.AllPairings.allPossiblePairs;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AllPairingsTest {

    @Test
    void allPossiblePairings_empty_givesEmpty() {
        assertEquals(emptySet(), allPossiblePairs(Set.of()));
    }

    @Test
    void allPossiblePairings_unevenNumberElements_throws() {
        assertThrows(IllegalArgumentException.class, () -> allPossiblePairs(Set.of(1)));
        assertThrows(IllegalArgumentException.class, () -> allPossiblePairs(Set.of(1, 2, 3)));
        assertThrows(IllegalArgumentException.class, () -> allPossiblePairs(Set.of(1, 2, 3, 4, 5)));
    }

    @Test
    void allPossiblePairings_12() {
        assertEquals(Set.of(Map.of(1, 2, 2, 1)), allPossiblePairs(Set.of(1, 2)));
    }

    @Test
    void allPossiblePairings_21() {
        assertEquals(Set.of(Map.of(1, 2, 2, 1)), allPossiblePairs(Set.of(2, 1)));
    }

    @Test
    void allPossiblePairs_1234() {
        Set<Map<Integer, Integer>> expectedPairings = Set.of(
            Map.of(
                1, 2,
                2, 1,
                3, 4,
                4, 3
            ),
            Map.of(
                1, 3,
                3, 1,
                2, 4,
                4, 2
            ),
            Map.of(
                1, 4,
                4, 1,
                2, 3,
                3, 2
            )
        );

        assertEquals(expectedPairings, allPossiblePairs(Set.of(1, 2, 3, 4)));
    }

    @Test
    void allPossiblePairs_4213() {
        Set<Map<Integer, Integer>> expectedPairings = Set.of(
            Map.of(
                1, 2,
                2, 1,
                3, 4,
                4, 3
            ),
            Map.of(
                1, 3,
                3, 1,
                2, 4,
                4, 2
            ),
            Map.of(
                1, 4,
                4, 1,
                2, 3,
                3, 2
            )
        );

        assertEquals(expectedPairings, allPossiblePairs(Set.of(4, 2, 1, 3)));
    }

    @Test
    void allPossiblePairs_123456() {
        Set<Map<Integer, Integer>> expectedPairings = Set.of(
            Map.of(
                1, 2,
                2, 1,
                3, 4,
                4, 3,
                5, 6,
                6, 5
            ),
            Map.of(
                1, 2,
                2, 1,
                3, 5,
                5, 3,
                4, 6,
                6, 4
            ),
            Map.of(
                1, 2,
                2, 1,
                3, 6,
                6, 3,
                4, 5,
                5, 4
            ),
            Map.of(
                1, 3,
                3, 1,
                2, 4,
                4, 2,
                5, 6,
                6, 5
            ),
            Map.of(
                1, 3,
                3, 1,
                2, 5,
                5, 2,
                4, 6,
                6, 4
            ),
            Map.of(
                1, 3,
                3, 1,
                2, 6,
                6, 2,
                4, 5,
                5, 4
            ),
            Map.of(
                1, 4,
                4, 1,
                2, 3,
                3, 2,
                5, 6,
                6, 5
            ),
            Map.of(
                1, 4,
                4, 1,
                2, 5,
                5, 2,
                3, 6,
                6, 3
            ),
            Map.of(
                1, 4,
                4, 1,
                2, 6,
                6, 2,
                3, 5,
                5, 3
            ),
            Map.of(
                1, 5,
                5, 1,
                2, 3,
                3, 2,
                4, 6,
                6, 4
            ),
            Map.of(
                1, 5,
                5, 1,
                2, 4,
                4, 2,
                3, 6,
                6, 3
            ),
            Map.of(
                1, 5,
                5, 1,
                2, 6,
                6, 2,
                3, 4,
                4, 3
            ),
            Map.of(
                1, 6,
                6, 1,
                2, 3,
                3, 2,
                4, 5,
                5, 4
            ),
            Map.of(
                1, 6,
                6, 1,
                2, 4,
                4, 2,
                3, 5,
                5, 3
            ),
            Map.of(
                1, 6,
                6, 1,
                2, 5,
                5, 2,
                3, 4,
                4, 3
            )
        );

        assertEquals(expectedPairings, allPossiblePairs(Set.of(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void allPossiblePairs_10elements_givesDoubleFactorialOf9Possibilities() {
        int doubleFactorial9 = 9 * 7 * 5 * 3;
        assertEquals(doubleFactorial9, allPossiblePairs(Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).size());
    }
}