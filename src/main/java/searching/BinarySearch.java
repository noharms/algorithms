package searching;

public class BinarySearch {

    /**
     * Returns the index of the element that has the specified key, or -1, if no such element is found.
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
}
