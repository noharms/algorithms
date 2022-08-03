package sorting;

import org.junit.jupiter.api.Test;

class HeapSorterTest {

    @Test
    void emptyArray() {
        MainTestMethods.integer_emptyArray(new HeapSorter<>());
    }

    @Test
    void oneElementArray() {
        MainTestMethods.integer_oneElementArray(new HeapSorter<>());
    }

    @Test
    void twoElementArray() {
        MainTestMethods.integer_twoElementArray(new HeapSorter<>());
    }

    @Test
    void alreadySortedArray() {
        MainTestMethods.integer_alreadySortedArray(new HeapSorter<>());
    }

    @Test
    void allSameElements() {
        MainTestMethods.integer_allSameElements(new HeapSorter<>());
    }

    @Test
    void multipleElementArray() {
        MainTestMethods.integer_multipleElementArray(new HeapSorter<>());
    }

    @Test
    void randomArray() {
        MainTestMethods.integer_randomArray(new HeapSorter<>());
    }

    @Test
    void randomArrayOddNumberElements() {
        MainTestMethods.integer_randomArrayOddNumberElements(new HeapSorter<>());
    }

    @Test
    void sortAlphanumericalWords() {
        MainTestMethods.string_alphanumericalStrings(new HeapSorter<>());
    }
}