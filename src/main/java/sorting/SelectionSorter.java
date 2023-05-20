package sorting;

import arrayutils.ArrayUtils;

public class SelectionSorter<T extends Comparable<T>> implements Sorter<T> {

    /**
     * Sorts the input array, from the least to the greatest,
     * by iteratively building up a sorted sub-array from the end of the array.
     * <br>
     * This is achieved by searching for the maximum element within the sub-array of unsorted elements and swapping
     * it to the back.
     */
    @Override
    public void sort(T[] array) {
        int nUnsorted = array.length - 1;
        while (nUnsorted > 0) {
            int iMaxElement = 0;
            for (int j = 1; j <= nUnsorted; j++) {
                iMaxElement = array[j].compareTo(array[iMaxElement]) > 0 ? j : iMaxElement;
            }
            ArrayUtils.swap(iMaxElement, nUnsorted, array);
            nUnsorted--;
        }
    }
}
