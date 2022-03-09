package combinatorics;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static combinatorics.Permutations.permutations;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PermutationsTest {

    @Test
    void permutations_of_empty_list() {
        assertEquals(Set.of(emptyList()), permutations(emptyList()));
    }

    @Test
    void permutations_of_single_element() {
        assertEquals(Set.of(List.of(42)), permutations(List.of(42)));
    }

    @Test
    void permutations_of_two_elements() {
        assertEquals(Set.of(List.of(1, 2), List.of(2, 1)), permutations(List.of(1, 2)));
    }

    @Test
    void permutations_of_three_elements() {
        assertEquals(
            Set.of(List.of(1, 2, 3),
                   List.of(1, 3, 2),
                   List.of(2, 1, 3),
                   List.of(2, 3, 1),
                   List.of(3, 1, 2),
                   List.of(3, 2, 1)),
            permutations(List.of(1, 2, 3))
        );
    }

    @Test
    void result_is_the_same_for_all_input_orders() {
        assertEquals(permutations(List.of(1, 2, 3)), permutations(List.of(3, 2, 1)));
        assertEquals(permutations(List.of(1, 2, 3)), permutations(List.of(3, 1, 2)));
        assertEquals(permutations(List.of(1, 2, 3)), permutations(List.of(2, 1, 3)));
        assertEquals(permutations(List.of(1, 2, 3)), permutations(List.of(2, 3, 1)));
        assertEquals(permutations(List.of(1, 2, 3)), permutations(List.of(1, 3, 2)));
    }

    @Test
    void strings_also_work() {
        assertEquals(
            Set.of(List.of("1", "2", "3"),
                   List.of("1", "3", "2"),
                   List.of("2", "1", "3"),
                   List.of("2", "3", "1"),
                   List.of("3", "1", "2"),
                   List.of("3", "2", "1")),
            permutations(List.of("1", "2", "3"))
        );
    }
}