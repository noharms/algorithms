package searching;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {

    @Test
    void empty_array_returns_minus1() {
        assertEquals(-1, BinarySearch.find(new Integer[0], 42));
    }

    @Test
    void not_found_returns_minus1() {
        assertEquals(-1, BinarySearch.find(new Integer[]{1}, 42));
    }

    @Test
    void one_element_matches_returns_0() {
        assertEquals(0, BinarySearch.find(new Integer[]{42}, 42));
    }

    @Test
    void two_element_matches() {
        assertEquals(0, BinarySearch.find(new Integer[]{42, 43}, 42));
        assertEquals(1, BinarySearch.find(new Integer[]{41, 42}, 42));
    }

    @Test
    void three_element_matches() {
        assertEquals(0, BinarySearch.find(new Integer[]{42, 43, 45}, 42));
        assertEquals(1, BinarySearch.find(new Integer[]{41, 42, 44}, 42));
        assertEquals(2, BinarySearch.find(new Integer[]{40, 41, 42}, 42));
    }

    @Test
    void many_element_matches() {
        Integer[] arr = new Random().ints(100).boxed().toArray(Integer[]::new);
        int someValue = arr[0];
        Arrays.sort(arr);
        assertEquals(Arrays.binarySearch(arr, someValue), BinarySearch.find(arr, someValue));
    }

    @Test
    void strings_work() {
        assertEquals(0, BinarySearch.find(new String[]{"42", "43", "45"}, "42"));
        assertEquals(1, BinarySearch.find(new String[]{"41", "42", "44"}, "42"));
        assertEquals(2, BinarySearch.find(new String[]{"40", "41", "42"}, "42"));
    }
}