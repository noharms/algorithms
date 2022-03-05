package sorting;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * A rather academic sorter that does not sort an array but a stack - this means partitioning cannot be done in-place
 * on the input array but instead two auxiliary stacks are created in each level of recursion.
 */
public class QuickSorterStackBased {

    public static <T extends Comparable<T>> void quickSort(LinkedList<T> stack) {
        if (stack.size() <= 1) {
            return;
        }
        // partition on two auxiliary stacks
        T pivot = stack.pop();
        LinkedList<T> lesser = new LinkedList<>();
        LinkedList<T> greaterEqual = new LinkedList<>();
        while (!stack.isEmpty()) {
            T value = stack.pop();
            if (value.compareTo(pivot) < 0) {
                lesser.push(value);
            } else {
                greaterEqual.push(value);
            }
        }

        // sort aux stacks
        quickSort(lesser);
        quickSort(greaterEqual);

        // transfer back sorted partitions - lesser elements before greater elements
        // Caveat: since the greatest element is at the end of greaterEqual, we need to work at its end, which a stack does not normally allow
        // TODO: removeLast is not a normal Stack API
        while(!greaterEqual.isEmpty()) {
            stack.push(greaterEqual.removeLast());
        }
        stack.push(pivot);
        while (!lesser.isEmpty()) {
            stack.push(lesser.removeLast());
        }
    }

}
