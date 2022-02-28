package sorting;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Random;

public class MainTestMethods {

    static void integer_emptyArray(Sorter<Integer> sorter) {
        Integer[] arr = {};

        sorter.sort(arr);

        Assertions.assertArrayEquals(new Integer[0], arr);
    }

    static void integer_oneElementArray(Sorter<Integer> sorter) {
        Integer[] arr = {42};

        sorter.sort(arr);

        Assertions.assertArrayEquals(new Integer[]{42}, arr);
    }

    static void integer_twoElementArray(Sorter<Integer> sorter) {
        Integer[] arr = {43, 42};

        sorter.sort(arr);

        Assertions.assertArrayEquals(new Integer[]{42, 43}, arr);
    }

    static void integer_alreadySortedArray(Sorter<Integer> sorter) {
        Integer[] arr = {1, 2, 3, 4, 5};

        sorter.sort(arr);

        Assertions.assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, arr);
    }

    static void integer_allSameElements(Sorter<Integer> sorter) {
        Integer[] arr = {1, 1, 1, 1, 1};

        sorter.sort(arr);

        Assertions.assertArrayEquals(new Integer[]{1, 1, 1, 1, 1}, arr);
    }

    static void integer_multipleElementArray(Sorter<Integer> sorter) {
        Integer[] arr = {5, 2, 4, 3, 1};

        sorter.sort(arr);

        Assertions.assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, arr);
    }

    static void integer_randomArray(Sorter<Integer> sorter) {
        Random randomGenerator = new Random();
        Integer[] arr = randomGenerator.ints(100).boxed().toArray(Integer[]::new);
        Integer[] copy = Arrays.copyOf(arr, arr.length);

        sorter.sort(arr);
        Arrays.sort(copy);

        Assertions.assertArrayEquals(copy, arr);
    }

    static void integer_randomArrayOddNumberElements(Sorter<Integer> sorter) {
        Random randomGenerator = new Random();
        Integer[] arr = randomGenerator.ints(101).boxed().toArray(Integer[]::new);
        Integer[] copy = Arrays.copyOf(arr, arr.length);

        sorter.sort(arr);
        Arrays.sort(copy);

        Assertions.assertArrayEquals(copy, arr);
    }

    static void string_alphanumericalStrings(Sorter<String> sorter) {
        String[] arr = {"5", "21", "a4", "3", "1"};
        String[] copy = Arrays.copyOf(arr, arr.length);

        sorter.sort(arr);
        Arrays.sort(copy);

        Assertions.assertArrayEquals(copy, arr);
    }
}
