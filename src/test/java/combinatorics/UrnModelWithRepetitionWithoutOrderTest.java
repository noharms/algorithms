package combinatorics;

import com.google.common.collect.HashMultiset;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.google.common.math.LongMath.binomial;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UrnModelWithRepetitionWithoutOrderTest {

    @Test
    void empty_set_gives_empty_draw() {
        assertEquals(emptySet(), UrnModel.drawWithRepetitionWithoutOrder(emptySet(), 42));
    }

    @Test
    void n_draws_greater_than_set_gives_empty_draw() {
        assertEquals(emptySet(), UrnModel.drawWithRepetitionWithoutOrder(Set.of(1), 42));
    }

    @Test
    void one_element_set_gives_one_element_draw() {
        assertEquals(
            Set.of(HashMultiset.create(Set.of(1))),
            UrnModel.drawWithRepetitionWithoutOrder(Set.of(1), 1)
        );
    }

    @Test
    void two_element_set_zero_draws() {
        assertEquals(emptySet(), UrnModel.drawWithRepetitionWithoutOrder(Set.of(1, 2), 0));
    }

    @Test
    void two_element_set_one_draws() {
        assertEquals(
            Set.of(
                HashMultiset.create(Set.of(1)),
                HashMultiset.create(Set.of(2))
            ),
            UrnModel.drawWithRepetitionWithoutOrder(Set.of(1, 2), 1)
        );
    }

    @Test
    void two_element_set_two_draws() {
        assertEquals(
            Set.of(
                HashMultiset.create(List.of(1, 1)),
                HashMultiset.create(List.of(1, 2)),
                HashMultiset.create(List.of(2, 2))
            ),
            UrnModel.drawWithRepetitionWithoutOrder(Set.of(1, 2), 2)
        );
    }

    @Test
    void three_element_set_zero_draws() {
        assertEquals(emptySet(), UrnModel.drawWithRepetitionWithoutOrder(Set.of(1, 2, 3), 0));
    }

    @Test
    void three_element_set_one_draws() {
        assertEquals(
            Set.of(
                HashMultiset.create(Set.of(1)),
                HashMultiset.create(Set.of(2)),
                HashMultiset.create(Set.of(3))
            ),
            UrnModel.drawWithRepetitionWithoutOrder(Set.of(1, 2, 3), 1)
        );
    }

    @Test
    void three_element_set_two_draws() {
        assertEquals(
            Set.of(
                HashMultiset.create(List.of(1, 1)),
                HashMultiset.create(List.of(1, 2)),
                HashMultiset.create(List.of(1, 3)),
                HashMultiset.create(List.of(2, 2)),
                HashMultiset.create(List.of(2, 3)),
                HashMultiset.create(List.of(3, 3))
            ), UrnModel.drawWithRepetitionWithoutOrder(Set.of(1, 2, 3), 2));
    }

    @Test
    void three_element_set_three_draws() {
        assertEquals(
            Set.of(
                HashMultiset.create(List.of(1, 1, 1)),
                HashMultiset.create(List.of(1, 1, 2)),
                HashMultiset.create(List.of(1, 1, 3)),
                HashMultiset.create(List.of(1, 2, 2)),
                HashMultiset.create(List.of(1, 2, 3)),
                HashMultiset.create(List.of(1, 3, 3)),
                HashMultiset.create(List.of(2, 2, 2)),
                HashMultiset.create(List.of(2, 2, 3)),
                HashMultiset.create(List.of(2, 3, 3)),
                HashMultiset.create(List.of(3, 3, 3))
            ), UrnModel.drawWithRepetitionWithoutOrder(Set.of(1, 2, 3), 3));
    }

    @Test
    void number_elements() {
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int draws = 4;
        long nElementsExpected = binomial(numbers.size() + draws - 1, draws);
        assertEquals(nElementsExpected, UrnModel.drawWithRepetitionWithoutOrder(numbers, draws).size());
    }
}
