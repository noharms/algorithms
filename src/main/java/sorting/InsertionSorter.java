package sorting;

import static arrayutils.ArrayUtils.swap;

public class InsertionSorter<T extends Comparable<T>> implements Sorter<T> {

    /**
     * Sorts the input array, from the least to the greatest,
     * by iteratively building up a sorted sub-array from the start of the array.
     * <br>
     * This is achieved by taking the element after the sorted sub-array and inserting
     * it into the correct position inside the sorted sub-array (by consecutive swaps).
     * <br>
     * Memory aide: insertion sort is usually how we sort the obtained hand cards
     * in a card game!
     */
    @Override
    public void sort(T[] array) {
        int iEndSortedSubarray = 0;
        while (iEndSortedSubarray < array.length - 1) {
            insertNextUnsortedElementToSubarray(iEndSortedSubarray + 1, array);
            ++iEndSortedSubarray;
        }
    }

    private void insertNextUnsortedElementToSubarray(int iNextUnsortedElement, T[] array) {
        int backwardsIterator = iNextUnsortedElement; // start at the next unsorted element
        while (backwardsIterator > 0 && array[backwardsIterator].compareTo(array[backwardsIterator - 1]) < 0) {
            swap(backwardsIterator, backwardsIterator - 1, array);
            --backwardsIterator;
        }
    }

    // alternative, maybe more readable version (only for int)
//    public static void sortByInsertion(int[] array) {
//        for (int forwardCounter = 1; forwardCounter <= array.length - 1; ++forwardCounter) {
//
//            // "insert" the element array[forwardCounter] to the correct position in the already sorted sub-array left of it;
//            // do this by repeatedly swapping it to the left if  the left element is larger
//            for (int backwardCounter = forwardCounter; backwardCounter > 0; --backwardCounter) {
//                int currentValue = array[backwardCounter];
//                int leftValue = array[backwardCounter - 1];
//
//                if (leftValue <= currentValue) { // this means we have swapped the element often enough, it is "inserted" now
//                    break;
//                } else { // leftValue > currentValue --> need to swap
//                    swap(array, backwardCounter, backwardCounter - 1);
//                }
//
//            }
//        }
//    }
}
