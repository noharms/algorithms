package arrayutils;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static arrayutils.ArrayUtils.findIndicesMaxima;
import static arrayutils.ArrayUtils.weaveCombinations;
import static java.util.Collections.emptyList;
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

    @Test
    void no_local_maxima_on_empty_array() {
        assertEquals(emptyList(), findIndicesMaxima(new int[0]));
    }

    @Test
    void no_local_maxima_on_one_element_array() {
        assertEquals(emptyList(), findIndicesMaxima(new int[]{42}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{-42}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{0}));
    }

    @Test
    void no_local_maxima_on_two_element_arrays() {
        assertEquals(emptyList(), findIndicesMaxima(new int[]{42, 43}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{42, 42}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{42, 41}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{0, 42}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{-42, -43}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{-42, -42}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{-42, -41}));
    }

    @Test
    void three_points_with_maximum_in_the_middle() {
        assertEquals(List.of(Map.entry(1, 1)), findIndicesMaxima(new int[]{41, 42, 40}));
        assertEquals(List.of(Map.entry(1, 1)), findIndicesMaxima(new int[]{-41, -40, -41}));
        assertEquals(List.of(Map.entry(1, 1)), findIndicesMaxima(new int[]{-41, 40, -41}));
        assertEquals(List.of(Map.entry(1, 1)), findIndicesMaxima(new int[]{-2, 0, -1}));
    }

    @Test
    void three_points_with_minimum_in_the_middle() {
        assertEquals(emptyList(), findIndicesMaxima(new int[]{41, 40, 41}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{-41, -42, -41}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{41, -43, 41}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{2, 0, 1}));
    }

    @Test
    void strictly_increasing_arrays_have_no_maxima() {
        assertEquals(emptyList(), findIndicesMaxima(new int[]{1, 2, 3}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{41, 42, 43}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{-43, -42, -41}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{0, 42, 143}));
    }

    @Test
    void strictly_decreasing_arrays_have_no_maxima() {
        assertEquals(emptyList(), findIndicesMaxima(new int[]{3, 2, 1}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{43, 42, 41}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{-41, -42, -43}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{143, 42, 0}));
    }

    @Test
    void monotonically_increasing_arrays_have_no_maxima() {
        assertEquals(emptyList(), findIndicesMaxima(new int[]{1, 1, 3}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{1, 3, 3}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{41, 41, 43}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{41, 43, 43}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{-43, -43, -41}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{-43, -41, -41}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{0, 0, 143}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{0, 143, 143}));
    }

    @Test
    void monotonically_decreasing_arrays_have_no_maxima() {
        assertEquals(emptyList(), findIndicesMaxima(new int[]{3, 3, 1}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{3, 1, 1}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{43, 43, 41}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{43, 41, 41}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{-41, -41, -43}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{-41, -43, -43}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{143, 143, 0}));
        assertEquals(emptyList(), findIndicesMaxima(new int[]{143, 0, 0}));
    }

    @Test
    void larger_array_with_single_maximum() {
        assertEquals(List.of(Map.entry(5, 5)), findIndicesMaxima(new int[]{41, 1, -3, 4, 12, 42, 41, 2}));
    }

    @Test
    void larger_array_with_single_maximum_before_right_boundary() {
        assertEquals(List.of(Map.entry(5, 5)), findIndicesMaxima(new int[]{41, 1, -3, 4, 12, 42, 41}));
    }

    @Test
    void larger_array_with_single_maximum_after_left_boundary() {
        assertEquals(List.of(Map.entry(1, 1)), findIndicesMaxima(new int[]{41, 43, -3, -4, -42}));
    }

    @Test
    void larger_array_with_two_maxima() {
        assertEquals(List.of(Map.entry(1, 1), Map.entry(5, 5)), findIndicesMaxima(new int[]{41, 43, -3, 4, 12, 42, 41, 2}));
    }

    @Test
    void zig_zag_array_with_multiple_maxima() {
        assertEquals(
                List.of(Map.entry(1, 1), Map.entry(3, 3), Map.entry(5, 5)),
                findIndicesMaxima(new int[]{41, 43, 41, 43, 41, 43, 41})
        );
    }

    @Test
    void single_plateau_maximum() {
        assertEquals(List.of(Map.entry(2, 4)), findIndicesMaxima(new int[]{0, 2, 4, 4, 4, 1, -1}));
    }

    @Test
    void multiple_plateau_maximum() {
        assertEquals(List.of(Map.entry(1, 2), Map.entry(4, 5)), findIndicesMaxima(new int[]{0, 4, 4, 2, 3, 3, -1}));
    }

    @Test
    void one_single_point_and_one_plateau_maximum() {
        assertEquals(List.of(Map.entry(1, 1), Map.entry(3, 5)), findIndicesMaxima(new int[]{0, 2, 0, 4, 4, 4, -1}));
    }
}