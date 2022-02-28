package sorting;

import org.junit.jupiter.api.Test;

class SelectionSorterTest {

    @Test
    void emptyArray() {
        MainTestMethods.integer_emptyArray(new SelectionSorter<>());
    }

    @Test
    void oneElementArray() {
        MainTestMethods.integer_oneElementArray(new SelectionSorter<>());
    }

    @Test
    void twoElementArray() {
        MainTestMethods.integer_twoElementArray(new SelectionSorter<>());
    }

    @Test
    void alreadySortedArray() {
        MainTestMethods.integer_alreadySortedArray(new SelectionSorter<>());
    }

    @Test
    void allSameElements() {
        MainTestMethods.integer_allSameElements(new SelectionSorter<>());
    }

    @Test
    void multipleElementArray() {
        MainTestMethods.integer_multipleElementArray(new SelectionSorter<>());
    }

    @Test
    void randomArray() {
        MainTestMethods.integer_randomArray(new SelectionSorter<>());
    }

    @Test
    void randomArrayOddNumberElements() {
        MainTestMethods.integer_randomArrayOddNumberElements(new SelectionSorter<>());
    }

    @Test
    void sortAlphanumericalWords() {
        MainTestMethods.string_alphanumericalStrings(new SelectionSorter<>());
    }

}