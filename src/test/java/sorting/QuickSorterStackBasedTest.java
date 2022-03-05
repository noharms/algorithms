package sorting;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuickSorterStackBasedTest {

    @Test
    void empty_stack() {
        LinkedList<Integer> stack = new LinkedList<>();
        QuickSorterStackBased.quickSort(stack);
        assertEquals(new LinkedList<>(), stack);
    }

    @Test
    void one_element_stack() {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(42);
        QuickSorterStackBased.quickSort(stack);
        assertEquals(new LinkedList<>(List.of(42)), stack);
    }

    @Test
    void two_elements_equal() {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(42);
        stack.push(42);

        QuickSorterStackBased.quickSort(stack);

        assertEquals(42, stack.pop());
        assertEquals(42, stack.pop());
    }


    @Test
    void two_element_stack_reverse_order() {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(42);
        stack.push(41);

        QuickSorterStackBased.quickSort(stack);

        assertEquals(41, stack.pop());
        assertEquals(42, stack.pop());
    }

    @Test
    void two_element_stack_natural_order() {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(41);
        stack.push(42);

        QuickSorterStackBased.quickSort(stack);

        assertEquals(41, stack.pop());
        assertEquals(42, stack.pop());
    }

    @Test
    void three_element_stack_unordered() {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(42);
        stack.push(41);
        stack.push(43);

        QuickSorterStackBased.quickSort(stack);

        assertEquals(41, stack.pop());
        assertEquals(42, stack.pop());
        assertEquals(43, stack.pop());
    }

    @Test
    void multiple_elements_stack_unordered() {
        LinkedList<Integer> stack = new LinkedList<>();
        for (Integer num : new Random().ints(100).boxed().toArray(Integer[]::new)) {
            stack.push(num);
        }
        LinkedList<Integer> copy = new LinkedList<>(stack);

        QuickSorterStackBased.quickSort(stack);
        Collections.sort(copy);

        assertEquals(copy, stack);
    }

    @Test
    void strings_work() {
        LinkedList<String> stack = new LinkedList<>();
        stack.push("42");
        stack.push("41");
        stack.push("43");

        QuickSorterStackBased.quickSort(stack);

        assertEquals("41", stack.pop());
        assertEquals("42", stack.pop());
        assertEquals("43", stack.pop());
    }
}