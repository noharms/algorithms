package combinatorics;

import java.util.*;

public class Permutations {

    public static <T> Set<List<T>> permutations(List<T> tuple) {
        Set<List<T>> result = new HashSet<>();
        computeRecursive(new HashSet<>(tuple), new LinkedList<>(), result, tuple.size());
        return result;
    }

    private static <T> void computeRecursive(Set<T> elements,
                                             LinkedList<T> currentSequence,
                                             Set<List<T>> allPermutations,
                                             int finalLength) {
        if (currentSequence.size() == finalLength) {
            allPermutations.add(new ArrayList<>(currentSequence));
            return;
        }
        for (T element : elements) {
            Set<T> remainingElements = new HashSet<>(elements);
            remainingElements.remove(element);
            currentSequence.addLast(element);
            computeRecursive(remainingElements, currentSequence, allPermutations, finalLength);
            currentSequence.removeLast();
        }
    }
}
