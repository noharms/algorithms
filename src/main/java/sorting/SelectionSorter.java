package sorting;

import arrayutils.ArrayUtils;

public class SelectionSorter<T extends Comparable<T>> implements Sorter<T> {

    /**
     * Sorts the input array, from the least to the greatest,
     * by iteratively building up a sorted sub-array from the end of the array.
     * <br>
     * This is achieved by searching for the maximum element within the sub-array
     * of unsorted elements and swapping it to the back.
     */
    @Override
    public void sort(T[] array) {
        // initially, nothing is sorted, so subarray starts beyond the end
        for (int iStartSortedSubarray = array.length; iStartSortedSubarray > 0; iStartSortedSubarray--) {
            int iMaxElement = findIndexOfMax(0, iStartSortedSubarray, array);
            ArrayUtils.swap(iMaxElement, iStartSortedSubarray - 1, array);
        }
    }

    private int findIndexOfMax(int startIncl, int endExcl, T[] array) {
        int iMaxElement = startIncl;
        for (int i = startIncl + 1; i < endExcl; i++) {
            iMaxElement = array[i].compareTo(array[iMaxElement]) > 0 ? i : iMaxElement;
        }
        return iMaxElement;
    }
}
