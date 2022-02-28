package sorting;

import org.junit.jupiter.api.Test;

class InsertionSorterTest {

    @Test
    void emptyArray() {
        MainTestMethods.integer_emptyArray(new InsertionSorter<>());
    }

    @Test
    void oneElementArray() {
        MainTestMethods.integer_oneElementArray(new InsertionSorter<>());
    }

    @Test
    void twoElementArray() {
        MainTestMethods.integer_twoElementArray(new InsertionSorter<>());
    }

    @Test
    void alreadySortedArray() {
        MainTestMethods.integer_alreadySortedArray(new InsertionSorter<>());
    }

    @Test
    void allSameElements() {
        MainTestMethods.integer_allSameElements(new InsertionSorter<>());
    }

    @Test
    void multipleElementArray() {
        MainTestMethods.integer_multipleElementArray(new InsertionSorter<>());
    }

    @Test
    void randomArray() {
        MainTestMethods.integer_randomArray(new InsertionSorter<>());
    }

    @Test
    void randomArrayOddNumberElements() {
        MainTestMethods.integer_randomArrayOddNumberElements(new InsertionSorter<>());
    }

    @Test
    void sortAlphanumericalWords() {
        MainTestMethods.string_alphanumericalStrings(new InsertionSorter<>());
    }

}