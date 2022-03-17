package combinatorics;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.google.common.math.LongMath.binomial;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UrnModelWithoutRepetitionWithoutOrderTest {

    @Test
    void empty_set_draws_are_empty() {
        assertEquals(emptySet(), UrnModel.drawWithoutRepetitionWithoutOrder(emptySet(), 42));
    }

    @Test
    void more_draws_than_elements_gives_empty_draws() {
        assertEquals(emptySet(), UrnModel.drawWithoutRepetitionWithoutOrder(Set.of(1), 42));
    }

    @Test
    void one_element_set() {
        assertEquals(Set.of(Set.of(1)), UrnModel.drawWithoutRepetitionWithoutOrder(Set.of(1), 1));
    }

    @Test
    void two_element_set_zero_draws() {
        assertEquals(emptySet(), UrnModel.drawWithoutRepetitionWithoutOrder(Set.of(1, 2), 0));
    }

    @Test
    void two_element_set_one_draws() {
        assertEquals(Set.of(Set.of(1), Set.of(2)), UrnModel.drawWithoutRepetitionWithoutOrder(Set.of(1, 2), 1));
    }

    @Test
    void two_element_set_two_draws() {
        assertEquals(Set.of(Set.of(1, 2)), UrnModel.drawWithoutRepetitionWithoutOrder(Set.of(1, 2), 2));
    }

    @Test
    void three_element_set_zero_draws() {
        assertEquals(emptySet(), UrnModel.drawWithoutRepetitionWithoutOrder(Set.of(1, 2, 3), 0));
    }

    @Test
    void three_element_set_one_draws() {
        assertEquals(
            Set.of(Set.of(1), Set.of(2), Set.of(3)),
            UrnModel.drawWithoutRepetitionWithoutOrder(Set.of(1, 2, 3), 1)
        );
    }

    @Test
    void three_element_set_two_draws() {
        assertEquals(
            Set.of(Set.of(1, 2), Set.of(1, 3), Set.of(2, 3)),
            UrnModel.drawWithoutRepetitionWithoutOrder(Set.of(1, 2, 3), 2)
        );
    }

    @Test
    void three_element_set_three_draws() {
        assertEquals(Set.of(Set.of(1, 2, 3)), UrnModel.drawWithoutRepetitionWithoutOrder(Set.of(1, 2, 3), 3));
    }

    @Test
    void large_set_number_elements_as_expected() {
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int draws = 4;
        long nElementsExpected = binomial(numbers.size(), draws);
        assertEquals(nElementsExpected, UrnModel.drawWithoutRepetitionWithoutOrder(numbers, draws).size());
    }
}
