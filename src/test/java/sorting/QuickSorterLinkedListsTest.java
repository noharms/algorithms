package sorting;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuickSorterLinkedListsTest {

    @Test
    void empty_linkedList() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        QuickSorterLinkedLists.quickSort(linkedList);
        assertEquals(new LinkedList<>(), linkedList);
    }

    @Test
    void one_element_linkedList() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(42);
        QuickSorterLinkedLists.quickSort(linkedList);
        assertEquals(new LinkedList<>(List.of(42)), linkedList);
    }

    @Test
    void two_elements_equal() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(42);
        linkedList.addFirst(42);

        QuickSorterLinkedLists.quickSort(linkedList);

        assertEquals(42, linkedList.removeFirst());
        assertEquals(42, linkedList.removeFirst());
    }


    @Test
    void two_element_linkedList_reverse_order() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(42);
        linkedList.addFirst(41);

        QuickSorterLinkedLists.quickSort(linkedList);

        assertEquals(41, linkedList.removeFirst());
        assertEquals(42, linkedList.removeFirst());
    }

    @Test
    void two_element_linkedList_natural_order() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(41);
        linkedList.addFirst(42);

        QuickSorterLinkedLists.quickSort(linkedList);

        assertEquals(41, linkedList.removeFirst());
        assertEquals(42, linkedList.removeFirst());
    }

    @Test
    void three_element_linkedList_unordered() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(42);
        linkedList.addFirst(41);
        linkedList.addFirst(43);

        QuickSorterLinkedLists.quickSort(linkedList);

        assertEquals(41, linkedList.removeFirst());
        assertEquals(42, linkedList.removeFirst());
        assertEquals(43, linkedList.removeFirst());
    }

    @Test
    void multiple_elements_linkedList_unordered() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (Integer num : new Random().ints(100).boxed().toArray(Integer[]::new)) {
            linkedList.addFirst(num);
        }
        LinkedList<Integer> copy = new LinkedList<>(linkedList);

        QuickSorterLinkedLists.quickSort(linkedList);
        Collections.sort(copy);

        assertEquals(copy, linkedList);
    }

    @Test
    void strings_work() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.addFirst("42");
        linkedList.addFirst("41");
        linkedList.addFirst("43");

        QuickSorterLinkedLists.quickSort(linkedList);

        assertEquals("41", linkedList.removeFirst());
        assertEquals("42", linkedList.removeFirst());
        assertEquals("43", linkedList.removeFirst());
    }
}