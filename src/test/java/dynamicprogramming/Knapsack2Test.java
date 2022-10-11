package dynamicprogramming;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Knapsack2Test {

    private static final Knapsack.Item RED = new Knapsack.Item("red", 4, 4);
    private static final Knapsack.Item GREEN = new Knapsack.Item("green", 4, 5);
    private static final Knapsack.Item BLUE = new Knapsack.Item("blue", 8, 5);

    @Test
    void pickOptimum_knapsackCapacityRequiresSmallerStepSizeThanCostsWouldSuggest_works() {
        Knapsack knapsack = new Knapsack(9, List.of(GREEN, BLUE, RED));
        assertEquals(Set.of(GREEN, RED), knapsack.pickOptimum());
    }
}
