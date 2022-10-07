package combinatorics;

import java.util.*;

public class AllPairings {

    /**
     * Computes all possible pairings from a set of distinct elements. E.g. if the input is {1, 2, 3, 4}
     * the result is {{(1,2), (3,4)} , {(1, 3), (2,4)}, {(1, 4), (2, 3)}}.
     * <br>
     * The resulting number of different pairings can be computed recursively: imagine we enumerate all elements
     * and fix the first of them; then we have n-1 possible partners to make a pair; for each of those n-1 pairs
     * we now have to compute all possible pairs from the remaining n-2 elements; so, again, from the n-2 elements
     * we fix one and form all possible n-3 pairs; and so on, until only 2 elements remain. That is, we get
     * (n-1) * (n-3) * (n-5) ... * 1 possibilities, which is the "double factorial" of n-1: (n-1)!!
     * <br>
     * Therefore, for n=4 elements we obtain 3 different pairings, for n=6 elements we already obtain 5*3=15 pairings.
     *
     *
     * @param elements must have an even number of elements
     * @return each pairing combination is returned as a map, which for a pair (1,2) maps 1 to 2 and 2 to 1.
     */
    public static <T> Set<Map<T, T>> allPossiblePairs(Set<T> elements) {
        throwIfInvalid(elements);
        if (elements.isEmpty()) {
            return Collections.emptySet();
        }
        Set<Map<T, T>> allPairs = new HashSet<>();
        Set<T> remainingElements = new HashSet<>(elements);
        Map<T, T> currentPairings = new HashMap<>();
        computeAllPairsRecursively(currentPairings, remainingElements, allPairs);
        return allPairs;
    }

    private static <T> void computeAllPairsRecursively(Map<T, T> currentPairings,
                                                       Set<T> remainingElements,
                                                       Set<Map<T, T>> allPairs
    ) {
        if (remainingElements.isEmpty()) {
            allPairs.add(new HashMap<>(currentPairings));
            return;
        }
        int nElementsRemaining = remainingElements.size();
        List<T> remainingElementsOrdered = new ArrayList<>(remainingElements);
        for (int i = 0; i < nElementsRemaining; i++) {
            T fixedElement = remainingElementsOrdered.get(i);
            for (int j = i + 1; j < nElementsRemaining; j++) {
                T assignedPartner = remainingElementsOrdered.get(j);

                remainingElements.remove(fixedElement);
                remainingElements.remove(assignedPartner);
                currentPairings.put(fixedElement, assignedPartner);
                currentPairings.put(assignedPartner, fixedElement);

                computeAllPairsRecursively(currentPairings, remainingElements, allPairs);

                remainingElements.add(fixedElement);
                remainingElements.add(assignedPartner);
                currentPairings.remove(fixedElement);
                currentPairings.remove(assignedPartner);
            }
        }
    }

    private static <T> void throwIfInvalid(Set<T> elements) {
        if (elements.size() % 2 != 0) {
            throw new IllegalArgumentException("Cannot compute pairings for uneven number of elements.");
        }
    }
}
