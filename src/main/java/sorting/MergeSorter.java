package sorting;

import java.util.Arrays;

public class MergeSorter<T extends Comparable<T>> implements Sorter<T> {

    /**
     * Requires O(n) additional space.
     */
    @Override
    public void sort(T[] array) {
        mergeSort(array, 0, array.length);
    }

    private void mergeSort(T[] array, int startIncl, int endExcl) {
        int nElements = endExcl - startIncl;
        if (nElements < 2) {
            return;
        }
        int nLeft = nElements / 2; // note: if nElements even, mid will round down to the lesser side
        mergeSort(array, startIncl, startIncl + nLeft);
        mergeSort(array, startIncl + nLeft, endExcl);
        mergeSortedHalves(array, startIncl, endExcl, nLeft);
    }

    private void mergeSortedHalves(T[] array, int startIncl, int endExcl, int nLeft) {

        T[] left = Arrays.copyOfRange(array, startIncl, startIncl + nLeft);
        T[] right = Arrays.copyOfRange(array, startIncl + nLeft, endExcl);

        int i = 0;
        int j = 0;
        while (i < left.length && j < right.length) {
            int compareResult = left[i].compareTo(right[j]);
            if (compareResult < 0) {
                array[startIncl + i + j] = left[i];
                ++i;
            } else {
                array[startIncl + i + j] = right[j];
                ++j;
            }
        }
        while (i < left.length) {
            array[startIncl + i + j] = left[i++];
        }

        while (j < right.length) {
            array[startIncl + i + j] = right[j++];
        }
    }

}
