package sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

public class HeapSorter<T extends Comparable<T>> implements Sorter<T> {

    private final PriorityQueue<T> heap;

    public HeapSorter() {
        heap = new PriorityQueue<>();
    }

    public void sort(T[] array) {
        heap.addAll(Arrays.stream(array).toList());
        for (int i = 0; i < heap.size(); ++i) {
            array[i] = heap.remove();
        }
    }

}
