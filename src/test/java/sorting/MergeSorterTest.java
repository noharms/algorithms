package sorting;

import org.junit.jupiter.api.Test;

class MergeSorterTest {

    @Test
    void emptyArray() {
        MainTestMethods.integer_emptyArray(new MergeSorter<>());
    }

    @Test
    void oneElementArray() {
        MainTestMethods.integer_oneElementArray(new MergeSorter<>());
    }

    @Test
    void twoElementArray() {
        MainTestMethods.integer_twoElementArray(new MergeSorter<>());
    }

    @Test
    void alreadySortedArray() {
        MainTestMethods.integer_alreadySortedArray(new MergeSorter<>());
    }

    @Test
    void allSameElements() {
        MainTestMethods.integer_allSameElements(new MergeSorter<>());
    }

    @Test
    void multipleElementArray() {
        MainTestMethods.integer_multipleElementArray(new MergeSorter<>());
    }

    @Test
    void randomArray() {
        MainTestMethods.integer_randomArray(new MergeSorter<>());
    }

    @Test
    void randomArrayOddNumberElements() {
        MainTestMethods.integer_randomArrayOddNumberElements(new MergeSorter<>());
    }

    @Test
    void sortAlphanumericalWords() {
        MainTestMethods.string_alphanumericalStrings(new MergeSorter<>());
    }

}