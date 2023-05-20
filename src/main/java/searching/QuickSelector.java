package searching;

import java.util.Arrays;

import static arrayutils.ArrayUtils.triPartitionAroundPivot;

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

        int indexPivot = triPartitionAroundPivot(startIncl, endExcl, array);
        int offsetPivot = indexPivot - startIncl;
        while (offsetPivot != k) {
            if (offsetPivot > k) {
                endExcl = indexPivot;
            } else { // indexPivot < startIncl + k
                k = k - offsetPivot - 1;
                startIncl = indexPivot + 1;
            }
            indexPivot = triPartitionAroundPivot(startIncl, endExcl, array);
            offsetPivot = indexPivot - startIncl;
        }
        return array[indexPivot];
    }

}
