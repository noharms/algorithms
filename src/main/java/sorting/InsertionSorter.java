package sorting;

import static arrayutils.ArrayUtils.swap;

public class InsertionSorter<T extends Comparable<T>> implements Sorter<T> {

    /**
     * Sorts the input array, from the least to the greatest,
     * by iteratively building up a sorted sub-array from the start of the array.
     * <br>
     * This is achieved by taking the element after the sorted sub-array and inserting
     * it into the correct position inside the sorted sub-array (by consecutive swaps).
     */
    @Override
    public void sort(T[] array) {
        int iterator = 1;
        while (iterator <= array.length - 1) {
            int backwardsIterator = iterator;
            while (backwardsIterator > 0 && array[backwardsIterator].compareTo(array[backwardsIterator - 1]) < 0) {
                swap(array, backwardsIterator, backwardsIterator - 1);
                --backwardsIterator;
            }
            ++iterator;
        }
    }
}
