package combinatorics;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UrnModelWithRepetitionWithOrderTest {

    @Test
    void empty_set_gives_empty_draw() {
        assertEquals(emptySet(), UrnModel.drawWithRepetitionWithOrder(emptySet(), 42));
    }

    @Test
    void n_draws_greater_than_set_gives_empty_draw() {
        assertEquals(
                Set.of(List.of(1, 1, 1)),
                UrnModel.drawWithRepetitionWithOrder(Set.of(1), 3)
        );
    }

    @Test
    void one_element_set_gives_one_element_draw() {
        assertEquals(Set.of(List.of(1)), UrnModel.drawWithRepetitionWithOrder(Set.of(1), 1));
    }

    @Test
    void two_element_set_zero_draws() {
        assertEquals(emptySet(), UrnModel.drawWithRepetitionWithOrder(Set.of(1, 2), 0));
    }

    @Test
    void two_element_set_one_draws() {
        assertEquals(Set.of(List.of(1), List.of(2)), UrnModel.drawWithRepetitionWithOrder(Set.of(1, 2), 1));
    }

    @Test
    void two_element_set_two_draws() {
        assertEquals(
            Set.of(List.of(1, 1), List.of(1, 2), List.of(2, 2), List.of(2, 1)),
            UrnModel.drawWithRepetitionWithOrder(Set.of(1, 2), 2)
        );
    }

    @Test
    void two_element_set_three_draws() {
        assertEquals(
                Set.of(
                        List.of(1, 1, 1),
                        List.of(1, 1, 2),
                        List.of(1, 2, 1),
                        List.of(1, 2, 2),
                        List.of(2, 1, 1),
                        List.of(2, 1, 2),
                        List.of(2, 2, 1),
                        List.of(2, 2, 2)
                ),
                UrnModel.drawWithRepetitionWithOrder(Set.of(1, 2), 3)
        );
    }

    @Test
    void three_element_set_zero_draws() {
        assertEquals(emptySet(), UrnModel.drawWithRepetitionWithOrder(Set.of(1, 2, 3), 0));
    }

    @Test
    void three_element_set_one_draws() {
        assertEquals(
            Set.of(List.of(1), List.of(2), List.of(3)),
            UrnModel.drawWithRepetitionWithOrder(Set.of(1, 2, 3), 1)
        );
    }

    @Test
    void three_element_set_two_draws() {
        assertEquals(
            Set.of(
                List.of(1, 1),
                List.of(1, 2),
                List.of(1, 3),
                List.of(2, 1),
                List.of(2, 2),
                List.of(2, 3),
                List.of(3, 1),
                List.of(3, 2),
                List.of(3, 3)
            ), UrnModel.drawWithRepetitionWithOrder(Set.of(1, 2, 3), 2));
    }

    @Test
    void three_element_set_three_draws() {
        assertEquals(
            Set.of(
                List.of(1, 1, 1),
                List.of(1, 1, 2),
                List.of(1, 1, 3),
                List.of(1, 2, 1),
                List.of(1, 2, 2),
                List.of(1, 2, 3),
                List.of(1, 3, 1),
                List.of(1, 3, 2),
                List.of(1, 3, 3),
                List.of(2, 1, 1),
                List.of(2, 1, 2),
                List.of(2, 1, 3),
                List.of(2, 2, 1),
                List.of(2, 2, 2),
                List.of(2, 2, 3),
                List.of(2, 3, 1),
                List.of(2, 3, 2),
                List.of(2, 3, 3),
                List.of(3, 1, 1),
                List.of(3, 1, 2),
                List.of(3, 1, 3),
                List.of(3, 2, 1),
                List.of(3, 2, 2),
                List.of(3, 2, 3),
                List.of(3, 3, 1),
                List.of(3, 3, 2),
                List.of(3, 3, 3)
            ), UrnModel.drawWithRepetitionWithOrder(Set.of(1, 2, 3), 3));
    }

    @Test
    void number_elements() {
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int draws = 4;
        long nElementsExpected = (int) Math.pow(numbers.size(), draws);
        assertEquals(nElementsExpected, UrnModel.drawWithRepetitionWithOrder(numbers, draws).size());
    }
}
