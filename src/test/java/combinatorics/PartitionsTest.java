package combinatorics;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartitionsTest {

    @Test
    void bellNumber_1_is_1() {
        assertEquals(1, Partitions.bellNumber(1));
    }

    @Test
    void bellNumber_2_is_2() {
        assertEquals(2, Partitions.bellNumber(2));
    }

    @Test
    void bellNumber_3_is_5() {
        assertEquals(5, Partitions.bellNumber(3));
    }

    @Test
    void bellNumber_4_is_15() {
        assertEquals(15, Partitions.bellNumber(4));
    }

    @Test
    void bellNumber_5_is_52() {
        assertEquals(52, Partitions.bellNumber(5));
    }

    @Test
    void bellNumber_6_is_203() {
        assertEquals(203, Partitions.bellNumber(6));
    }

    @Test
    void allPartitions_one_element_set() {
        assertEquals(Set.of(Set.of(Set.of(1))), Partitions.allPartitions(Set.of(1)));
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
            Partitions.allPartitions(Set.of(1, 2)));
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
            Partitions.allPartitions(Set.of(1, 2, 3)));
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
        Set<Set<Set<Integer>>> actual = Partitions.allPartitions(Set.of(1, 2, 3, 4));
        assertEquals(expected, actual);
        assertEquals(Partitions.bellNumber(4), actual.size());
    }

    @Test
    void number_elements_matches_for_large_set() {
        assertEquals(Partitions.bellNumber(8),
                     Partitions.allPartitions(IntStream.range(0, 8).boxed().collect(Collectors.toSet())).size());
    }
}