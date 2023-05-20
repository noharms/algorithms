package arrayutils;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayUtils {

    public static <T extends Comparable<T>> void swap(int i, int j, T[] array) {
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
    public static <U extends Comparable<U>> int biPartitionAroundPivot(int startIncl, int endExcl, U[] array) {
        U pivotElement = array[startIncl];
        int nGreater = 0;
        int nLesserEqual = 1;
        int nElements = endExcl - startIncl;

        while (nGreater + nLesserEqual < nElements) {
            U currentElement = array[startIncl + nLesserEqual];
            if (currentElement.compareTo(pivotElement) > 0) {
                swap(startIncl + nLesserEqual, endExcl - 1 - nGreater, array);
                ++nGreater;
            } else {
                ++nLesserEqual;
            }
        }

        int pivotIndex = startIncl + nLesserEqual - 1;
        swap(startIncl, pivotIndex, array);
        return pivotIndex;
    }

    /**
     * Picks a random pivot element from the array and reorders the array into three partitions:
     * less, equal, greater with respect to the pivot.
     *
     * @return the index of the pivot after the partitioning
     */
    public static <T extends Comparable<T>> int triPartitionAroundPivot(int startIncl, int endExcl, T[] array) {
        // if array has even length, this is at start of left half: e.g. [1, 2, 3, 4] --> 0 + 3 / 2 = 1.5 -> 1
        int mid = startIncl + (endExcl - startIncl - 1) / 2;
        T pivotElement = array[mid];
        swap(mid, startIncl, array);
        int nLess = 0;
        int nEqual = 1;
        int nGreater = 0;
        while (nLess + nEqual + nGreater < (endExcl - startIncl)) {
            int current = startIncl + nLess + nEqual;
            int compareResult = array[current].compareTo(pivotElement);
            if (compareResult == 0) {
                ++nEqual;
            } else if (compareResult < 0) {
                swap(current, startIncl + nLess, array);
                ++nLess;
            } else {
                swap(current, endExcl - 1 - nGreater, array);
                ++nGreater;
            }
        }
        return startIncl + nLess + nEqual - 1;
    }

    /**
     * Weaving two arrays means two merge them in a way that the relative orders of the elements are preserved.
     * There are many possible weavings, e.g. given {1, 2, 3} and {4, 5} we could get the results
     * <ul>
     *     <li>{1, 2, 3, 4, 5}</li>
     *     <li>{1, 2, 4, 3, 5}</li>
     *     <li>{1, 4, 2, 3, 5}</li>
     *     <li>{4, 1, 2, 3, 5}</li>
     *     <li>{4, 1, 2, 5, 3}</li>
     *     <li>{4, 1, 5, 2, 3}</li>
     *     <li>{4, 5, 1, 2, 3}</li>
     * </ul>
     */
    public static <T> Set<LinkedList<T>> weaveCombinations(T[] array1, T[] array2) {
        if (array1.length == 0 && array2.length == 0) {
            return Collections.emptySet();
        }
        int nElementsToWeave = array1.length + array2.length;
        Set<LinkedList<T>> weaveCombinations = new HashSet<>();
        LinkedList<T> list1 = Arrays.stream(array1).collect(Collectors.toCollection(LinkedList::new));
        LinkedList<T> list2 = Arrays.stream(array2).collect(Collectors.toCollection(LinkedList::new));
        LinkedList<T> currentWeaving = new LinkedList<>();
        weaveCombinationsRecursively(nElementsToWeave, list1, list2, weaveCombinations, currentWeaving);
        return weaveCombinations;
    }

    private static <T> void weaveCombinationsRecursively(int nElements,
                                                         LinkedList<T> remaining1,
                                                         LinkedList<T> remaining2,
                                                         Set<LinkedList<T>> results,
                                                         LinkedList<T> currentWeaving) {
        if (currentWeaving.size() == nElements) {
            results.add(new LinkedList<>(currentWeaving));
        }
        if (remaining1.size() > 0) {
            T first = remaining1.get(0);
            remaining1.removeFirst();
            currentWeaving.add(first);
            weaveCombinationsRecursively(nElements, remaining1, remaining2, results, currentWeaving);
            currentWeaving.removeLast();
            remaining1.addFirst(first);
        }
        if (remaining2.size() > 0) {
            T first = remaining2.get(0);
            remaining2.removeFirst();
            currentWeaving.add(first);
            weaveCombinationsRecursively(nElements, remaining1, remaining2, results, currentWeaving);
            currentWeaving.removeLast();
            remaining2.addFirst(first);
        }
    }
}
