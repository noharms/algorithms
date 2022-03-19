package sorting;

import static arrayutils.ArrayUtils.biPartitionAroundPivot;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public void sort(T[] array) {
        quickSort(array, 0, array.length);
    }

    private static <U extends Comparable<U>> void quickSort(U[] array, int startIncl, int endExcl) {
        if (endExcl - startIncl < 2) {
            return;
        }
        int pivotIndex = biPartitionAroundPivot(array, startIncl, endExcl);
        quickSort(array, startIncl, pivotIndex);
        quickSort(array, pivotIndex + 1, endExcl);
    }

}
