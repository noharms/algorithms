package arrayutils;

public class ArrayUtils {

    public static <T extends Comparable<T>> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Picks a random pivot element from the array and reorders the array into two partitions:
     * less or equal than, greater than, with respect to the pivot.
     *
     * @return the index of the pivot after the partitioning
     */
    public static <U extends Comparable<U>> int biPartitionAroundPivot(U[] array, int startIncl, int endExcl) {
        U pivotElement = array[startIncl];
        int nGreater = 0;
        int nLesserEqual = 1;
        int nElements = endExcl - startIncl;

        while (nGreater + nLesserEqual < nElements) {
            U currentElement = array[startIncl + nLesserEqual];
            if (currentElement.compareTo(pivotElement) > 0) {
                swap(array, startIncl + nLesserEqual, endExcl - 1 - nGreater);
                ++nGreater;
            } else {
                ++nLesserEqual;
            }
        }

        int pivotIndex = startIncl + nLesserEqual - 1;
        swap(array, startIncl, pivotIndex);
        return pivotIndex;
    }

    /**
     * Picks a random pivot element from the array and reorders the array into three partitions:
     * less, equal, greater with respect to the pivot.
     *
     * @return the index of the pivot after the partitioning
     */
    public static <T extends Comparable<T>> int triPartitionAroundPivot(T[] array, int startIncl, int endExcl) {
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
