package dynamicprogramming;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KnapsackTest {

    private static final Knapsack.Item WATCH = new Knapsack.Item("watch", 1, 8);
    private static final Knapsack.Item STONE = new Knapsack.Item("stone", 10, 1);
    private static final Knapsack.Item PLANK = new Knapsack.Item("plank", 4, 2);
    private static final Knapsack.Item LAPTOP = new Knapsack.Item("laptop", 5, 11);
    private static final Knapsack.Item MOBILE = new Knapsack.Item("mobile", 2, 10);

    @Test
    void item_with_0_cost_throws() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Knapsack(1, List.of(new Knapsack.Item("dummy", 0, 42)))
        );
    }

    @Test
    void item_with_negative_cost_throws() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Knapsack(1, List.of(new Knapsack.Item("dummy", -1, 42)))
        );
    }

    @Test
    void item_with_0_value_throws() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Knapsack(1, List.of(new Knapsack.Item("dummy", 1, 0)))
        );
    }

    @Test
    void item_with_negative_value_throws() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Knapsack(1, List.of(new Knapsack.Item("dummy", 1, -1)))
        );
    }

    @Test
    void duplicate_items_throws() {
        assertThrows(IllegalArgumentException.class, () -> new Knapsack(1, List.of(WATCH, WATCH)));
    }

    @Test
    void empty_capacity_empty_items_gives_empty_set() {
        assertEquals(emptySet(), new Knapsack(0, emptyList()).pickOptimum());
    }

    @Test
    void one_item_exceeds_capacity_alone_throws() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Knapsack(0, List.of(new Knapsack.Item("dummy", 1, 1))).pickOptimum()
        );
    }

    @Test
    void one_item_picks_trivial_knapsack() {
        assertEquals(Set.of(WATCH), new Knapsack(16, List.of(WATCH)).pickOptimum());
    }

    @Test
    void two_items_both_fit_pick_both() {
        assertEquals(Set.of(WATCH, MOBILE), new Knapsack(16, List.of(WATCH, MOBILE)).pickOptimum());
    }

    @Test
    void two_items_only_one_fits_pick_more_valuable_one() {
        assertEquals(Set.of(MOBILE), new Knapsack(5, List.of(PLANK, MOBILE)).pickOptimum());
    }

    @Test
    void three_items_all_fit_pick_all() {
        assertEquals(Set.of(WATCH, MOBILE, STONE), new Knapsack(16, List.of(WATCH, MOBILE, STONE)).pickOptimum());
    }

    @Test
    void three_items_two_medium_value_togeter_better_than_single_high_value_alone() {
        assertEquals(Set.of(PLANK, MOBILE), new Knapsack(6, List.of(LAPTOP, MOBILE, PLANK)).pickOptimum());
    }

    @Test
    void three_items_two_medium_value_togeter_worse_than_single_high_value_alone() {
        assertEquals(Set.of(LAPTOP), new Knapsack(5, List.of(LAPTOP, WATCH, PLANK)).pickOptimum());
    }

    @Test
    void order_does_not_matter() {
        assertEquals(Set.of(PLANK, MOBILE), new Knapsack(6, List.of(LAPTOP, MOBILE, PLANK)).pickOptimum());
        assertEquals(Set.of(PLANK, MOBILE), new Knapsack(6, List.of(LAPTOP, PLANK, MOBILE)).pickOptimum());
        assertEquals(Set.of(PLANK, MOBILE), new Knapsack(6, List.of(MOBILE, LAPTOP, PLANK)).pickOptimum());
        assertEquals(Set.of(PLANK, MOBILE), new Knapsack(6, List.of(MOBILE, PLANK, LAPTOP)).pickOptimum());
        assertEquals(Set.of(PLANK, MOBILE), new Knapsack(6, List.of(PLANK, MOBILE, LAPTOP)).pickOptimum());
        assertEquals(Set.of(PLANK, MOBILE), new Knapsack(6, List.of(PLANK, LAPTOP, MOBILE)).pickOptimum());
    }

    @Test
    void example_from_book() {
        Knapsack.Item guitar = new Knapsack.Item("guitar", 1, 15);
        Knapsack.Item laptop = new Knapsack.Item("laptop", 3, 20);
        Knapsack.Item stereo = new Knapsack.Item("stereo", 4, 30);
        assertEquals(Set.of(guitar, laptop), new Knapsack(4, List.of(guitar, laptop, stereo)).pickOptimum());
    }

    @Test
    void example_from_book_2() {
        Knapsack.Item guitar = new Knapsack.Item("guitar", 1, 15);
        Knapsack.Item laptop = new Knapsack.Item("laptop", 3, 20);
        Knapsack.Item stereo = new Knapsack.Item("stereo", 4, 30);
        Knapsack.Item phone = new Knapsack.Item("phone", 1, 20);
        assertEquals(Set.of(phone, laptop), new Knapsack(4, List.of(guitar, laptop, stereo, phone)).pickOptimum());
    }
}