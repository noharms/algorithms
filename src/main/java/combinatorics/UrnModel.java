package combinatorics;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptySet;

public class UrnModel {

    public static <T> Set<List<T>> drawWithoutRepetitionWithOrder(Set<T> set, int k) {
        if (k > set.size() || k == 0) {
            return emptySet();
        }
        Set<List<T>> result = new HashSet<>();
        withoutRepetitionWithOrder(set, k, new LinkedList<>(), result);
        return result;
    }

    private static <T> void withoutRepetitionWithOrder(Set<T> set,
                                                       int maxDraws,
                                                       LinkedList<T> currentDraw,
                                                       Set<List<T>> completedDraws) {
        if(currentDraw.size() == maxDraws) {
            completedDraws.add(new LinkedList<>(currentDraw));
            return;
        }
        for (T element : set) {
            currentDraw.addLast(element);
            Set<T> reducedSet = new HashSet<>(set);
            reducedSet.remove(element);
            withoutRepetitionWithOrder(reducedSet, maxDraws, currentDraw, completedDraws);
            currentDraw.removeLast();
        }
    }
}
