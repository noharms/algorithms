package combinatorics;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.google.common.math.LongMath.factorial;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UrnModelTest {

    @Test
    void withoutRepetition_withOrder_empty_set() {
        assertEquals(emptySet(), UrnModel.drawWithoutRepetitionWithOrder(emptySet(), 42));
    }

    @Test
    void withoutRepetition_withOrder_k_greater_size() {
        assertEquals(emptySet(), UrnModel.drawWithoutRepetitionWithOrder(Set.of(1, 2, 3), 4));
    }

    @Test
    void withoutRepetition_withOrder_one_element_set_zero_draws() {
        assertEquals(emptySet(), UrnModel.drawWithoutRepetitionWithOrder(Set.of(1), 0));
    }

    @Test
    void withoutRepetition_withOrder_one_element_set_one_draw() {
        assertEquals(Set.of(List.of(1)), UrnModel.drawWithoutRepetitionWithOrder(Set.of(1), 1));
    }

    @Test
    void withoutRepetition_withOrder_two_element_set_zero_draws() {
        assertEquals(emptySet(), UrnModel.drawWithoutRepetitionWithOrder(Set.of(1, 2), 0));
    }

    @Test
    void withoutRepetition_withOrder_two_element_set_one_draws() {
        assertEquals(Set.of(List.of(1), List.of(2)), UrnModel.drawWithoutRepetitionWithOrder(Set.of(1, 2), 1));
    }

    @Test
    void withoutRepetition_withOrder_two_element_set_two_draws() {
        assertEquals(Set.of(List.of(1, 2), List.of(2, 1)), UrnModel.drawWithoutRepetitionWithOrder(Set.of(1, 2), 2));
    }

    @Test
    void withoutRepetition_withOrder_three_element_set_zero_draws() {
        assertEquals(emptySet(), UrnModel.drawWithoutRepetitionWithOrder(Set.of(1, 2, 3), 0));
    }

    @Test
    void withoutRepetition_withOrder_three_element_set_one_draws() {
        assertEquals(
                Set.of(
                        List.of(1),
                        List.of(2),
                        List.of(3)
                ),
                UrnModel.drawWithoutRepetitionWithOrder(Set.of(1, 2, 3), 1)
        );
    }

    @Test
    void withoutRepetition_withOrder_three_element_set_two_draws() {
        assertEquals(
                Set.of(
                        List.of(1, 2),
                        List.of(1, 3),
                        List.of(2, 1),
                        List.of(2, 3),
                        List.of(3, 1),
                        List.of(3, 2)
                ),
                UrnModel.drawWithoutRepetitionWithOrder(Set.of(1, 2, 3), 2)
        );
    }

    @Test
    void withoutRepetition_withOrder_three_element_set_three_draws() {
        assertEquals(
                Set.of(
                        List.of(1, 2, 3),
                        List.of(1, 3, 2),
                        List.of(2, 1, 3),
                        List.of(2, 3, 1),
                        List.of(3, 1, 2),
                        List.of(3, 2, 1)
                ),
                UrnModel.drawWithoutRepetitionWithOrder(Set.of(1, 2, 3), 3)
        );
    }

    @Test
    void number_elements() {
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int draws = 4;
        long nElementsExpected = factorial(numbers.size()) / factorial(numbers.size() - draws);
        assertEquals(nElementsExpected, UrnModel.drawWithoutRepetitionWithOrder(numbers, draws).size());
    }
}