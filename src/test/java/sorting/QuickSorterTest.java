package sorting;

import org.junit.jupiter.api.Test;

class QuickSorterTest {

    @Test
    void emptyArray() {
        MainTestMethods.integer_emptyArray(new QuickSorter<>());
    }

    @Test
    void oneElementArray() {
        MainTestMethods.integer_oneElementArray(new QuickSorter<>());
    }

    @Test
    void twoElementArray() {
        MainTestMethods.integer_twoElementArray(new QuickSorter<>());
    }

    @Test
    void alreadySortedArray() {
        MainTestMethods.integer_alreadySortedArray(new QuickSorter<>());
    }

    @Test
    void allSameElements() {
        MainTestMethods.integer_allSameElements(new QuickSorter<>());
    }

    @Test
    void multipleElementArray() {
        MainTestMethods.integer_multipleElementArray(new QuickSorter<>());
    }

    @Test
    void randomArray() {
        MainTestMethods.integer_randomArray(new QuickSorter<>());
    }

    @Test
    void randomArrayOddNumberElements() {
        MainTestMethods.integer_randomArrayOddNumberElements(new QuickSorter<>());
    }

    @Test
    void sortAlphanumericalWords() {
        MainTestMethods.string_alphanumericalStrings(new QuickSorter<>());
    }

}