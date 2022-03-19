package sorting;

import utils.ArrayUtils;

public class BubbleSorter<T extends Comparable<T>> implements Sorter<T> {

    /**
     * Sorts the input array, from the least to the greatest,
     * by iteratively building up a sorted sub-array from the end of the array.
     * <br>
     * This is achieved by inspecting pairs of elements and swapping them if the greater element is before the lesser.
     */
    @Override
    public void sort(T[] array) {
        int nUnsorted = array.length - 1;
        while (nUnsorted > 0) {
            for (int j = 0; j < nUnsorted; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    ArrayUtils.swap(array, j, j + 1);
                }
            }
            --nUnsorted;
        }
    }

}
