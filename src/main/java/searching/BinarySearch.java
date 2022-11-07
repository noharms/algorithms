package searching;

public class BinarySearch {

    /**
     * Returns the index of the element that has the specified key, or -1, if no such element is found.
     * <br>
     * If the array contains the searched for key multiple times (duplicates), we do not know which of them is found
     * but only that one of them will be found.
     */
    public static <T extends Comparable<T>> int find(T[] sorted, T key) {
        return findRecursive(sorted, 0, sorted.length, key);
    }

    private static <T extends Comparable<T>> int findRecursive(T[] sorted, int startIncl, int endExcl, T key) {
        if (startIncl >= endExcl) {
            return -1;
        }
        int midIndex = startIncl + (endExcl - startIncl) / 2;
        int comparison = key.compareTo(sorted[midIndex]);
        if (comparison == 0) {
            return midIndex;
        } else if (comparison > 0) {
            return findRecursive(sorted, midIndex + 1, endExcl, key);
        } else {
            return findRecursive(sorted, startIncl, midIndex, key);
        }
    }


    /**
     * The lower bound search differs from {@link BinarySearch#find(Comparable[], Comparable)} in that we expect to
     * have duplicates in the sorted input array and we are interested in the FIRST index at which a certain key
     * occurs.
     * <br>
     * For example, for input [1, 2, 42, 42, 42, 4, 5] the normal binary search may find index 3 for search key
     * 42, while the lower bound search will find index 2.
     * <br>
     * This comes at the cost of always doing log(n) steps until we have only one element in the search window.
     * Instead, the normal binary search has a better best case because if it finds the key it just stops.
     */
    public static <T extends Comparable<T>> int findLowerBound(T[] sorted, T key) {
        return findRecursiveLowerBound(sorted, 0, sorted.length, key);
    }

    private static <T extends Comparable<T>> int findRecursiveLowerBound(T[] sorted,
                                                                         int startIncl,
                                                                         int endExcl,
                                                                         T key) {
        if (startIncl >= endExcl) {
            return -1;
        }
        int arrayLength = endExcl - startIncl;
        int midIndex = startIncl + arrayLength / 2;
        int comparison = key.compareTo(sorted[midIndex]);
        if (comparison == 0) {
            int indexHitLeftFromCurrentHit = findRecursiveLowerBound(sorted, startIncl, midIndex, key);
            return indexHitLeftFromCurrentHit != -1 ? indexHitLeftFromCurrentHit : midIndex;
        } else if (comparison > 0) {
            return findRecursiveLowerBound(sorted, midIndex + 1, endExcl, key);
        } else {
            return findRecursiveLowerBound(sorted, startIncl, midIndex, key);
        }
    }

    /**
     * See documentation on {@link BinarySearch#findLowerBound(Comparable[], Comparable)}
     */
    public static <T extends Comparable<T>> int findUpperBound(T[] sorted, T key) {
        return findRecursiveUpperBound(sorted, 0, sorted.length, key);
    }

    private static <T extends Comparable<T>> int findRecursiveUpperBound(T[] sorted,
                                                                         int startIncl,
                                                                         int endExcl,
                                                                         T key) {
        if (startIncl >= endExcl) {
            return -1;
        }
        int arrayLength = endExcl - startIncl;
        int midIndex = startIncl + arrayLength / 2;
        int comparison = key.compareTo(sorted[midIndex]);
        if (comparison == 0) {
            int indexHitRightFromCurrentHit = findRecursiveUpperBound(sorted, midIndex + 1, endExcl, key);
            return indexHitRightFromCurrentHit != -1 ? indexHitRightFromCurrentHit : midIndex;

        } else if (comparison > 0) {
            return findRecursiveUpperBound(sorted, midIndex + 1, endExcl, key);
        } else {
            return findRecursiveUpperBound(sorted, startIncl, midIndex, key);
        }
    }
}
