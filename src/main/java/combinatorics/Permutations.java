package combinatorics;

import java.util.*;

public class Permutations {

    public static <T> Set<List<T>> permutations(List<T> tuple) {
        Set<List<T>> result = new HashSet<>();
        computeRecursive(new HashSet<>(tuple), tuple.size(), new LinkedList<>(), result);
        return result;
    }

    private static <T> void computeRecursive(Set<T> elements,
                                             int finalLength,
                                             LinkedList<T> currentSequence,
                                             Set<List<T>> allPermutations) {
        if (currentSequence.size() == finalLength) {
            allPermutations.add(new ArrayList<>(currentSequence));
            return;
        }
        for (T element : elements) {
            Set<T> remainingElements = new HashSet<>(elements);
            remainingElements.remove(element);
            currentSequence.addLast(element);
            computeRecursive(remainingElements, finalLength, currentSequence, allPermutations);
            currentSequence.removeLast();
        }
    }
}
