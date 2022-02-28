package sorting;

import org.junit.jupiter.api.Test;

class BubbleSorterTest {

    @Test
    void emptyArray() {
        MainTestMethods.integer_emptyArray(new BubbleSorter<>());
    }

    @Test
    void oneElementArray() {
        MainTestMethods.integer_oneElementArray(new BubbleSorter<>());
    }

    @Test
    void twoElementArray() {
        MainTestMethods.integer_twoElementArray(new BubbleSorter<>());
    }

    @Test
    void alreadySortedArray() {
        MainTestMethods.integer_alreadySortedArray(new BubbleSorter<>());
    }

    @Test
    void allSameElements() {
        MainTestMethods.integer_allSameElements(new BubbleSorter<>());
    }

    @Test
    void multipleElementArray() {
        MainTestMethods.integer_multipleElementArray(new BubbleSorter<>());
    }

    @Test
    void randomArray() {
        MainTestMethods.integer_randomArray(new BubbleSorter<>());
    }

    @Test
    void randomArrayOddNumberElements() {
        MainTestMethods.integer_randomArrayOddNumberElements(new BubbleSorter<>());
    }

    @Test
    void sortAlphanumericalWords() {
        MainTestMethods.string_alphanumericalStrings(new BubbleSorter<>());
    }

}