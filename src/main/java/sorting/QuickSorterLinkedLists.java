package sorting;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * A rather academic sorter that does not sort an array but a stack - this means partitioning cannot be done in-place
 * on the input array but instead two auxiliary stacks are created in each level of recursion.
 */
public class QuickSorterLinkedLists {

    public static <T extends Comparable<T>> void quickSort(LinkedList<T> linkedList) {
        if (linkedList.size() <= 1) {
            return;
        }
        // partition on two auxiliary stacks
        T pivot = linkedList.removeFirst();
        LinkedList<T> lesser = new LinkedList<>();
        LinkedList<T> greaterEqual = new LinkedList<>();
        while (!linkedList.isEmpty()) {
            T value = linkedList.removeFirst();
            if (value.compareTo(pivot) < 0) {
                lesser.addFirst(value);
            } else {
                greaterEqual.addFirst(value);
            }
        }

        // sort aux stacks
        quickSort(lesser);
        quickSort(greaterEqual);

        // transfer back sorted partitions - lesser elements before greater elements
        // Caveat: since the greatest element is at the end of greaterEqual, we need to work at its end, which a stack does not normally allow
        // TODO: removeLast is not a normal Stack API
        while(!greaterEqual.isEmpty()) {
            linkedList.addFirst(greaterEqual.removeLast());
        }
        linkedList.addFirst(pivot);
        while (!lesser.isEmpty()) {
            linkedList.addFirst(lesser.removeLast());
        }
    }

}
