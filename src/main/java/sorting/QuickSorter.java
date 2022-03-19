package sorting;

import static utils.ArrayUtils.swap;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public void sort(T[] array) {
        quickSort(array, 0, array.length);
    }

    private static <U extends Comparable<U>> void quickSort(U[] array, int startIncl, int endExcl) {
        if (endExcl - startIncl < 2) {
            return;
        }
        int pivotIndex = partitionAroundPivot(array, startIncl, endExcl);
        quickSort(array, startIncl, pivotIndex);
        quickSort(array, pivotIndex + 1, endExcl);
    }

    private static <U extends Comparable<U>> int partitionAroundPivot(U[] array, int startIncl, int endExcl) {
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

}
