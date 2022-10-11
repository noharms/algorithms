package dynamicprogramming;

import com.google.common.math.IntMath;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Collections.emptySet;

public class Knapsack {

    private final int capacity;
    private final List<Item> items;

    public Knapsack(int capacity, List<Item> items) {
        validate(items, capacity);
        this.capacity = capacity;
        this.items = items;
    }

    private static void validate(List<Item> items, int capacity) {
        if (!items.stream().allMatch(item -> item.load > 0 && item.value > 0)) {
            throw new IllegalArgumentException("Loads and values must be > 0 for all items.");
        }
        if (!items.stream().allMatch(item -> item.load <= capacity)) {
            throw new IllegalArgumentException("All single items should fit in the knapsack.");
        }
        if (new HashSet<>(items).size() != items.size()) {
            throw new IllegalArgumentException("No duplicates allowed.");
        }
    }

    public static record Item(String name, int load, int value) {
    }

    public Set<Item> pickOptimum() {
        if (items.isEmpty()) {
            return emptySet();
        }
        int nItems = items.size();
        List<List<Set<Item>>> subSolutions = computeSubSolutions();
        int nSubKnapsacks = subSolutions.get(0).size();
        return subSolutions.get(nItems).get(nSubKnapsacks - 1);
    }

    private List<List<Set<Item>>> computeSubSolutions() {
        int minLoadDelta = findMinDelta(items);
        int capacityStepSize = IntMath.gcd(capacity, minLoadDelta);
        int nSubCapacities = capacity / capacityStepSize;

        List<List<Set<Item>>> subSolutions = initializeSubSolutions(items.size(), nSubCapacities);
        List<Integer> capacities = IntStream.range(1, nSubCapacities + 1)
                                            .map(j -> j * capacityStepSize)
                                            .boxed()
                                            .toList();
        for (int i = 0; i < items.size(); i++) {
            Item currentItem = items.get(i);
            for (int j = 0; j < nSubCapacities; j++) {
                int currentCapacity = capacities.get(j);

                boolean isCurrentItemFitting = currentItem.load <= currentCapacity;
                int currentItemValue = isCurrentItemFitting ? currentItem.value : 0;
                int currentItemLoad = isCurrentItemFitting ? currentItem.load : 0;
                int remainingCapacity = currentCapacity - currentItemLoad;
                int jRemainingCapacity = remainingCapacity / capacityStepSize - 1;
                Set<Item> itemsRemainingCapacity = jRemainingCapacity >= 0 ?
                        subSolutions.get(i).get(jRemainingCapacity) :
                        emptySet();

                Set<Item> currentChoice = new HashSet<>();
                Set<Item> maxWithoutCurrentItem = subSolutions.get(i).get(j);
                if (currentItemValue + summedValue(itemsRemainingCapacity) > summedValue(maxWithoutCurrentItem)) {
                    currentChoice.add(currentItem);
                    currentChoice.addAll(itemsRemainingCapacity);
                } else {
                    currentChoice.addAll(maxWithoutCurrentItem);
                }
                subSolutions.get(i + 1).add(currentChoice);
            }
        }
        return subSolutions;
    }

    private static int findMinDelta(List<Item> items) {
        if (items.size() < 2) {
            return items.get(0).load;
        }
        int minDelta = Math.abs(items.get(1).load - items.get(0).load);
        for (int i = 2; i < items.size(); i++) {
            int delta = Math.abs(items.get(i).load - items.get(i - 1).load);
            minDelta = Math.min(minDelta, delta);
        }
        return minDelta;
    }

    private static int summedValue(Set<Item> items) {
        return items.stream().mapToInt(Item::value).sum();
    }

    // note: we fill the 0th row with empty hashsets here and following rows will be filled on the fly
    private static List<List<Set<Item>>> initializeSubSolutions(int nItems, int nSubKnapsacks) {
        List<List<Set<Item>>> subSolutions = new ArrayList<>();
        for (int i = 0; i <= nItems; i++) {
            subSolutions.add(new ArrayList<>());
        }
        for (int j = 0; j < nSubKnapsacks; j++) {
            subSolutions.get(0).add(new HashSet<>());
        }
        return subSolutions;
    }

}
