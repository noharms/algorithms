package searching;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {

    @Test
    void find_on_empty_array_returns_minus1() {
        assertEquals(-1, BinarySearch.find(new Integer[0], 42));
    }

    @Test
    void find_not_found_returns_minus1() {
        assertEquals(-1, BinarySearch.find(new Integer[]{1}, 42));
    }

    @Test
    void find_on_one_element_which_matches_returns_0() {
        assertEquals(0, BinarySearch.find(new Integer[]{42}, 42));
    }

    @Test
    void find_on_two_elements_where_one_matches() {
        assertEquals(0, BinarySearch.find(new Integer[]{42, 43}, 42));
        assertEquals(1, BinarySearch.find(new Integer[]{41, 42}, 42));
    }

    @Test
    void find_on_three_elements_where_one_matches() {
        assertEquals(0, BinarySearch.find(new Integer[]{42, 43, 45}, 42));
        assertEquals(1, BinarySearch.find(new Integer[]{41, 42, 44}, 42));
        assertEquals(2, BinarySearch.find(new Integer[]{40, 41, 42}, 42));
    }

    @Test
    void find_on_many_elements_where_one_matches() {
        Integer[] arr = new Random().ints(100).boxed().toArray(Integer[]::new);
        int someValue = arr[0];
        Arrays.sort(arr);
        assertEquals(Arrays.binarySearch(arr, someValue), BinarySearch.find(arr, someValue));
    }

    @Test
    void find_on_strings_work() {
        assertEquals(0, BinarySearch.find(new String[]{"42", "43", "45"}, "42"));
        assertEquals(1, BinarySearch.find(new String[]{"41", "42", "44"}, "42"));
        assertEquals(2, BinarySearch.find(new String[]{"40", "41", "42"}, "42"));
    }

    @Test
    void find_on_array_with_duplicates_works() {
        assertEquals(5, BinarySearch.find(new Integer[]{41, 41, 42, 42, 42, 42, 42, 42, 42, 43}, 42));
    }

    //---------------
    @Test
    void findLowerBound_on_empty_array_returns_minus1() {
        assertEquals(-1, BinarySearch.findLowerBound(new Integer[0], 42));
    }

    @Test
    void findLowerBound_on_not_found_returns_minus1() {
        assertEquals(-1, BinarySearch.findLowerBound(new Integer[]{1}, 42));
    }

    @Test
    void findLowerBound_on_one_element_matches_returns_0() {
        assertEquals(0, BinarySearch.findLowerBound(new Integer[]{42}, 42));
    }

    @Test
    void findLowerBound_on_two_elements_where_one_matchs() {
        assertEquals(1, BinarySearch.findLowerBound(new Integer[]{41, 42}, 42));
    }

    @Test
    void findLowerBound_on_two_elements_where_both_match() {
        assertEquals(0, BinarySearch.findLowerBound(new Integer[]{42, 42}, 42));
    }

    @Test
    void findLowerBound_on_three_element_where_one_matches() {
        assertEquals(0, BinarySearch.findLowerBound(new Integer[]{42, 43, 45}, 42));
        assertEquals(1, BinarySearch.findLowerBound(new Integer[]{41, 42, 44}, 42));
        assertEquals(2, BinarySearch.findLowerBound(new Integer[]{40, 41, 42}, 42));
    }

    @Test
    void findLowerBound_on_three_element_where_multiple_match() {
        assertEquals(0, BinarySearch.findLowerBound(new Integer[]{42, 42, 42}, 42));
        assertEquals(0, BinarySearch.findLowerBound(new Integer[]{42, 42, 45}, 42));
        assertEquals(1, BinarySearch.findLowerBound(new Integer[]{41, 42, 42}, 42));
    }

    @Test
    void findLowerBound_on_four_elements_where_multiple_match() {
        assertEquals(0, BinarySearch.findLowerBound(new Integer[]{42, 42, 42, 42}, 42));
        assertEquals(0, BinarySearch.findLowerBound(new Integer[]{42, 42, 42, 45}, 42));
        assertEquals(0, BinarySearch.findLowerBound(new Integer[]{42, 42, 43, 45}, 42));
        assertEquals(1, BinarySearch.findLowerBound(new Integer[]{41, 42, 42, 42}, 42));
        assertEquals(2, BinarySearch.findLowerBound(new Integer[]{41, 41, 42, 42}, 42));
    }

    @Test
    void findLowerBound_on_strings_work() {
        assertEquals(0, BinarySearch.findLowerBound(new String[]{"42", "43", "45"}, "42"));
        assertEquals(1, BinarySearch.findLowerBound(new String[]{"41", "42", "44"}, "42"));
        assertEquals(2, BinarySearch.findLowerBound(new String[]{"40", "41", "42"}, "42"));

        assertEquals(0, BinarySearch.findLowerBound(new String[]{"42", "42", "43", "45"}, "42"));
        assertEquals(1, BinarySearch.findLowerBound(new String[]{"41", "42", "42", "42"}, "42"));
        assertEquals(2, BinarySearch.findLowerBound(new String[]{"41", "41", "42", "42"}, "42"));
    }

    //---------------
    @Test
    void findUpperBound_on_empty_array_returns_minus1() {
        assertEquals(-1, BinarySearch.findUpperBound(new Integer[0], 42));
    }

    @Test
    void findUpperBound_on_not_found_returns_minus1() {
        assertEquals(-1, BinarySearch.findUpperBound(new Integer[]{1}, 42));
    }

    @Test
    void findUpperBound_on_one_element_matches_returns_0() {
        assertEquals(0, BinarySearch.findUpperBound(new Integer[]{42}, 42));
    }

    @Test
    void findUpperBound_on_two_elements_where_one_matchs() {
        assertEquals(1, BinarySearch.findUpperBound(new Integer[]{41, 42}, 42));
    }

    @Test
    void findUpperBound_on_two_elements_where_both_match() {
        assertEquals(1, BinarySearch.findUpperBound(new Integer[]{42, 42}, 42));
    }

    @Test
    void findUpperBound_on_three_element_where_one_matches() {
        assertEquals(0, BinarySearch.findUpperBound(new Integer[]{42, 43, 45}, 42));
        assertEquals(1, BinarySearch.findUpperBound(new Integer[]{41, 42, 44}, 42));
        assertEquals(2, BinarySearch.findUpperBound(new Integer[]{40, 41, 42}, 42));
    }

    @Test
    void findUpperBound_on_three_element_where_multiple_match() {
        assertEquals(2, BinarySearch.findUpperBound(new Integer[]{42, 42, 42}, 42));
        assertEquals(1, BinarySearch.findUpperBound(new Integer[]{42, 42, 45}, 42));
        assertEquals(2, BinarySearch.findUpperBound(new Integer[]{41, 42, 42}, 42));
    }

    @Test
    void findUpperBound_on_four_elements_where_multiple_match() {
        assertEquals(3, BinarySearch.findUpperBound(new Integer[]{42, 42, 42, 42}, 42));
        assertEquals(2, BinarySearch.findUpperBound(new Integer[]{42, 42, 42, 45}, 42));
        assertEquals(1, BinarySearch.findUpperBound(new Integer[]{42, 42, 43, 45}, 42));
        assertEquals(3, BinarySearch.findUpperBound(new Integer[]{41, 42, 42, 42}, 42));
        assertEquals(3, BinarySearch.findUpperBound(new Integer[]{41, 41, 42, 42}, 42));
    }

    @Test
    void findUpperBound_on_strings_work() {
        assertEquals(0, BinarySearch.findUpperBound(new String[]{"42", "43", "45"}, "42"));
        assertEquals(1, BinarySearch.findUpperBound(new String[]{"41", "42", "44"}, "42"));
        assertEquals(2, BinarySearch.findUpperBound(new String[]{"40", "41", "42"}, "42"));

        assertEquals(1, BinarySearch.findUpperBound(new String[]{"42", "42", "43", "45"}, "42"));
        assertEquals(3, BinarySearch.findUpperBound(new String[]{"41", "42", "42", "42"}, "42"));
        assertEquals(3, BinarySearch.findUpperBound(new String[]{"41", "41", "42", "42"}, "42"));
    }
}