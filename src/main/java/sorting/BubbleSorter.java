package sorting;

import arrayutils.ArrayUtils;

public class BubbleSorter<T extends Comparable<T>> implements Sorter<T> {

    /**
     * Sorts the input array, from the least to the greatest,
     * by iteratively building up a sorted sub-array from the end of the array.
     * <br>
     * This is achieved by inspecting pairs of elements and swapping them if the greater element is before the lesser.
     */
    @Override
    public void sort(T[] array) {
        // initially, nothing is sorted, so subarray starts beyond the end
        for (int iStartSortedSubarray = array.length; iStartSortedSubarray > 0; iStartSortedSubarray--) {
            swapRepeatedlyFromTo(0, iStartSortedSubarray, array);
        }
    }

    private void swapRepeatedlyFromTo(int startIncl, int endExcl, T[] array) {
        for (int i = startIncl; i < endExcl - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                ArrayUtils.swap(i, i + 1, array);
            }
        }
    }

}
