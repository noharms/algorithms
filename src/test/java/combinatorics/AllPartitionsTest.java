package combinatorics;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AllPartitionsTest {

    @Test
    void bellNumber_1_is_1() {
        assertEquals(1, AllPartitions.bellNumber(1));
    }

    @Test
    void bellNumber_2_is_2() {
        assertEquals(2, AllPartitions.bellNumber(2));
    }

    @Test
    void bellNumber_3_is_5() {
        assertEquals(5, AllPartitions.bellNumber(3));
    }

    @Test
    void bellNumber_4_is_15() {
        assertEquals(15, AllPartitions.bellNumber(4));
    }

    @Test
    void bellNumber_5_is_52() {
        assertEquals(52, AllPartitions.bellNumber(5));
    }

    @Test
    void bellNumber_6_is_203() {
        assertEquals(203, AllPartitions.bellNumber(6));
    }

    @Test
    void allPartitions_one_element_set() {
        assertEquals(Set.of(Set.of(Set.of(1))), AllPartitions.from(Set.of(1)));
    }

    @Test
    void allPartitions_two_element_set() {
        assertEquals(
            Set.of(
                Set.of(
                    Set.of(1),
                    Set.of(2)
                ),
                Set.of(
                    Set.of(1, 2)
                )
            ),
            AllPartitions.from(Set.of(1, 2)));
    }

    @Test
    void allPartitions_three_element_set() {
        assertEquals(
            Set.of(
                Set.of(
                    Set.of(1),
                    Set.of(2),
                    Set.of(3)
                ),
                Set.of(
                    Set.of(1, 2),
                    Set.of(3)
                ),
                Set.of(
                    Set.of(1, 3),
                    Set.of(2)
                ),
                Set.of(
                    Set.of(2, 3),
                    Set.of(1)
                ),
                Set.of(
                    Set.of(1, 2, 3)
                )
            ),
            AllPartitions.from(Set.of(1, 2, 3)));
    }

    @Test
    void allPartitions_four_element_set() {
        Set<Set<Set<Integer>>> expected = Set.of(
            Set.of(
                Set.of(1),
                Set.of(2),
                Set.of(3),
                Set.of(4)
            ),
            Set.of(
                Set.of(1, 2),
                Set.of(3),
                Set.of(4)
            ),
            Set.of(
                Set.of(1, 3),
                Set.of(2),
                Set.of(4)
            ),
            Set.of(
                Set.of(2, 3),
                Set.of(1),
                Set.of(4)
            ),
            Set.of(
                Set.of(1, 2, 3),
                Set.of(4)
            ),
            Set.of(
                Set.of(1, 2, 4),
                Set.of(3)
            ),
            Set.of(
                Set.of(1, 2),
                Set.of(3, 4)
            ),
            Set.of(
                Set.of(1, 3, 4),
                Set.of(2)
            ),
            Set.of(
                Set.of(1, 3),
                Set.of(2, 4)
            ),
            Set.of(
                Set.of(2, 3, 4),
                Set.of(1)
            ),
            Set.of(
                Set.of(2, 3),
                Set.of(1, 4)
            ),
            Set.of(
                Set.of(2),
                Set.of(3),
                Set.of(1, 4)
            ),
            Set.of(
                Set.of(1),
                Set.of(3),
                Set.of(2, 4)
            ),
            Set.of(
                Set.of(1),
                Set.of(2),
                Set.of(3, 4)
            ),
            Set.of(
                Set.of(1, 2, 3, 4)
            )
        );
        Set<Set<Set<Integer>>> actual = AllPartitions.from(Set.of(1, 2, 3, 4));
        assertEquals(expected, actual);
        assertEquals(AllPartitions.bellNumber(4), actual.size());
    }

    @Test
    void number_elements_matches_for_large_set() {
        assertEquals(AllPartitions.bellNumber(8),
                     AllPartitions.from(IntStream.range(0, 8).boxed().collect(Collectors.toSet())).size());
    }
}