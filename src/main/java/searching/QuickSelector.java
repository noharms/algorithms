package searching;

import java.util.Arrays;

import static arrayutils.ArrayUtils.swap;

/**
 * Finds the kth smallest element in O(n) time by using the quick-select strategy. Note that we use O(n) space to make
 * a copy (otherwise the algorithm would rearrange the input).
 * <br>
 * Note that naive algorithms would sort the input (i.e. O(n log n)) or find the minimum k times (i.e. O(k n))
 * to pick the kth largest element.
 */
public class QuickSelector {

    /**
     * @param k must be in the range 0 to array.length - 1
     */
    public static <T extends Comparable<T>> T findKthSmallestElement(T[] array, int k) {
        if (k < 0 || k >= array.length) {
            String msg = "Choose 0 <= k < array length (%s was chosen but length is %s).".formatted(k, array.length);
            throw new IllegalArgumentException(msg);
        }
        T[] copy = Arrays.copyOf(array, array.length); // O(n) space for copying, so the copy can be reordered
        return findKthSmallest(copy, k);
    }

    /**
     * Caveat: this reorders the input array ! the kth smallest element will be at index k after this method completes
     */
    private static <T extends Comparable<T>> T findKthSmallest(T[] array, int k) {
        int startIncl = 0;
        int endExcl = array.length;

        int indexPivot = partitionAroundPivot(array, startIncl, endExcl);
        int offsetPivot = indexPivot - startIncl;
        while (offsetPivot != k) {
            if (offsetPivot > k) {
                endExcl = indexPivot;
            } else { // indexPivot < startIncl + k
                k = k - offsetPivot - 1;
                startIncl = indexPivot + 1;
            }
            indexPivot = partitionAroundPivot(array, startIncl, endExcl);
            offsetPivot = indexPivot - startIncl;
        }
        return array[indexPivot];
    }

    /* returns the index of the pivot after the partitioning, e.g. startIncl + offset of the pivot */
    private static <T extends Comparable<T>> int partitionAroundPivot(T[] array, int startIncl, int endExcl) {
        // if array has even length, this is at start of left half: e.g. [1, 2, 3, 4] --> 0 + 3 / 2 = 1.5 -> 1
        int mid = startIncl + (endExcl - startIncl - 1) / 2;
        T pivotElement = array[mid];
        swap(array, mid, startIncl);
        int nLess = 0;
        int nEqual = 1;
        int nGreater = 0;
        while (nLess + nEqual + nGreater < (endExcl - startIncl)) {
            int current = startIncl + nLess + nEqual;
            int compareResult = array[current].compareTo(pivotElement);
            if (compareResult == 0) {
                ++nEqual;
            } else if (compareResult < 0) {
                swap(array, current, startIncl + nLess);
                ++nLess;
            } else {
                swap(array, current, endExcl - 1 - nGreater);
                ++nGreater;
            }
        }
        return startIncl + nLess + nEqual - 1;
    }

}
