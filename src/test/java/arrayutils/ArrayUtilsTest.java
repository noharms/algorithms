package arrayutils;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static arrayutils.ArrayUtils.weaveCombinations;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayUtilsTest {

    private static final Integer[] ARRAY = {1, 4, 2, 5};
    public static final Integer[] EMPTY_ARRAY = new Integer[0];

    @Test
    public void weave_two_empty_arrays_gives_empty_list() {
        assertEquals(emptySet(), weaveCombinations(EMPTY_ARRAY, new Integer[0]));
    }

    @Test
    public void weave_1st_array_empty_gives_single_list_matching_the_2nd_array() {
        assertEquals(Set.of(List.of(1, 4, 2, 5)), weaveCombinations(EMPTY_ARRAY, ARRAY));
    }

    @Test
    public void weave_2nd_array_empty_gives_single_list_matching_the_1st_array() {
        assertEquals(Set.of(List.of(1, 4, 2, 5)), weaveCombinations(ARRAY, EMPTY_ARRAY));
    }

    @Test
    void weave_1_and_2_gives_expected_combinations() {
        Set<List<Integer>> expectedWeavings = Set.of(List.of(1, 2), List.of(2, 1));
        assertEquals(expectedWeavings, weaveCombinations(new Integer[]{1}, new Integer[]{2}));
    }

    @Test
    void weave_12_and_3_gives_expected_combinations() {
        Set<List<Integer>> expectedWeavings = Set.of(
                List.of(1, 2, 3),
                List.of(1, 3, 2),
                List.of(3, 1, 2)
        );
        assertEquals(expectedWeavings, weaveCombinations(new Integer[]{1, 2}, new Integer[]{3}));
    }

    @Test
    void weave_1_and_23_gives_expected_combinations() {
        Set<List<Integer>> expectedWeavings = Set.of(
                List.of(1, 2, 3),
                List.of(2, 1, 3),
                List.of(2, 3, 1)
        );
        assertEquals(expectedWeavings, weaveCombinations(new Integer[]{1}, new Integer[]{2, 3}));
    }

    @Test
    void weave_12_and_34_gives_expected_combinations() {
        Set<List<Integer>> expectedWeavings = Set.of(
                List.of(1, 2, 3, 4),
                List.of(1, 3, 2, 4),
                List.of(1, 3, 4, 2),
                List.of(3, 1, 2, 4),
                List.of(3, 1, 4, 2),
                List.of(3, 4, 1, 2)
        );
        assertEquals(expectedWeavings, weaveCombinations(new Integer[]{1, 2}, new Integer[]{3, 4}));
    }

    @Test
    void weave_123_and_45_gives_expected_combinations() {
        Set<List<Integer>> expectedWeavings = Set.of(
                List.of(1, 2, 3, 4, 5),
                List.of(1, 2, 4, 3, 5),
                List.of(1, 2, 4, 5, 3),
                List.of(1, 4, 2, 3, 5),
                List.of(1, 4, 2, 5, 3),
                List.of(1, 4, 5, 2, 3),
                List.of(4, 1, 2, 3, 5),
                List.of(4, 1, 2, 5, 3),
                List.of(4, 1, 5, 2, 3),
                List.of(4, 5, 1, 2, 3)
        );
        assertEquals(expectedWeavings, weaveCombinations(new Integer[]{1, 2, 3}, new Integer[]{4, 5}));
    }
}