package searching;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static searching.QuickSelector.findKthSmallestElement;

class QuickSelectorTest {

    @Test
    void empty_array_throws() {
        assertThrows(IllegalArgumentException.class, () -> findKthSmallestElement(new Integer[0], 0));
        assertThrows(IllegalArgumentException.class, () -> findKthSmallestElement(new Integer[0], 1));
        assertThrows(IllegalArgumentException.class, () -> findKthSmallestElement(new Integer[0], 42));
    }

    @Test
    void negative_k_throws() {
        assertThrows(IllegalArgumentException.class, () -> findKthSmallestElement(new Integer[]{42}, -1));
        assertThrows(IllegalArgumentException.class, () -> findKthSmallestElement(new Integer[]{42}, -42));
    }

    @Test
    void k_larger_than_array_throws() {
        assertThrows(IllegalArgumentException.class, () -> findKthSmallestElement(new Integer[]{42}, 2));
        assertThrows(IllegalArgumentException.class, () -> findKthSmallestElement(new Integer[]{42, 43}, 3));
    }

    @Test
    void one_element() {
        assertEquals(42, findKthSmallestElement(new Integer[]{42}, 0));
        assertEquals(-42, findKthSmallestElement(new Integer[]{-42}, 0));
    }

    @Test
    void two_elements() {
        assertEquals(42, findKthSmallestElement(new Integer[]{42, 43}, 0));
        assertEquals(43, findKthSmallestElement(new Integer[]{42, 43}, 1));

        assertEquals(42, findKthSmallestElement(new Integer[]{43, 42}, 0));
        assertEquals(43, findKthSmallestElement(new Integer[]{43, 42}, 1));
    }

    @Test
    void three_elements() {
        assertEquals(42, findKthSmallestElement(new Integer[]{42, 43, 44}, 0));
        assertEquals(43, findKthSmallestElement(new Integer[]{42, 43, 44}, 1));
        assertEquals(44, findKthSmallestElement(new Integer[]{42, 43, 44}, 2));

        assertEquals(42, findKthSmallestElement(new Integer[]{42, 44, 43}, 0));
        assertEquals(43, findKthSmallestElement(new Integer[]{42, 44, 43}, 1));
        assertEquals(44, findKthSmallestElement(new Integer[]{42, 44, 43}, 2));

        assertEquals(42, findKthSmallestElement(new Integer[]{43, 44, 42}, 0));
        assertEquals(43, findKthSmallestElement(new Integer[]{43, 44, 42}, 1));
        assertEquals(44, findKthSmallestElement(new Integer[]{43, 44, 42}, 2));

        assertEquals(42, findKthSmallestElement(new Integer[]{43, 42, 44}, 0));
        assertEquals(43, findKthSmallestElement(new Integer[]{43, 42, 44}, 1));
        assertEquals(44, findKthSmallestElement(new Integer[]{43, 42, 44}, 2));

        assertEquals(42, findKthSmallestElement(new Integer[]{44, 43, 42}, 0));
        assertEquals(43, findKthSmallestElement(new Integer[]{44, 43, 42}, 1));
        assertEquals(44, findKthSmallestElement(new Integer[]{44, 43, 42}, 2));

        assertEquals(42, findKthSmallestElement(new Integer[]{44, 42, 43}, 0));
        assertEquals(43, findKthSmallestElement(new Integer[]{44, 42, 43}, 1));
        assertEquals(44, findKthSmallestElement(new Integer[]{44, 42, 43}, 2));
    }

    @Test
    void strings_also_work() {
        assertEquals("42", findKthSmallestElement(new String[]{"42", "43"}, 0));
        assertEquals("43", findKthSmallestElement(new String[]{"42", "43"}, 1));

        assertEquals("42", findKthSmallestElement(new String[]{"43", "42"}, 0));
        assertEquals("43", findKthSmallestElement(new String[]{"43", "42"}, 1));
    }

    @Test
    void random_array() {
        Integer[] numbers = new Random().ints(100).boxed().toArray(Integer[]::new);

        int k = 50;
        int kthSmallest = findKthSmallestElement(numbers, k);

        Arrays.sort(numbers);
        assertEquals(numbers[k], kthSmallest);
    }
}