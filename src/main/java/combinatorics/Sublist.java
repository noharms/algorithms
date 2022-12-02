package combinatorics;

import java.util.*;

public class Sublist {

    /**
     * There are (n + n-1 + n-2 + ... 1) = n(n+1) = O(n^2) consecutive sublists of a list of n elements.
     * Finding all (startIncl, endIncl) indices is O(n^2). However, printing all sublists or collecting all of them
     * is O(n^3) because printing/collecting requires O(n) to go from startIncl to endIncl for eachs current sublist.
     */
    public static <T> Set<List<T>> allConsecutiveSublists(List<T> list) {
        Set<List<T>> result = new HashSet<>();
        for (int startIncl = 0; startIncl < list.size(); startIncl++) {
            List<T> current = new LinkedList<>();
            for (int i = startIncl; i < list.size(); i++) {
                current.add(list.get(i));
                result.add(new LinkedList<>(current));
            }
        }
        return result;
    }

    /**
     * There a more general sublists of a list than there are consecutive sublists. To be a sublist we only demand
     * that the i) the elements in the sublist were contained in the list and
     * ii) that the relative order of elements in the sublist is like it was in the original list.
     * <br>
     * A non-consecutive sublist of "abcd" is, e.g., "ac", or "ad", or "abd".
     * <br>
     * The number of sublists is hard to estimate but it is bounded by the number of subsets O(2^n) from above
     * and the number of consecutive sublists from below O(n^2).
     */
    public static <T> Set<List<T>> allSublists(List<T> list) {
        Set<List<T>> result = new HashSet<>();
        List<T> remainingElements = new LinkedList<>(list);
        LinkedList<T> currentSublist = new LinkedList<>();
        allSublists(currentSublist, remainingElements, result);
        return result;
    }

    private static <T> void allSublists(LinkedList<T> currentSublist,
                                        List<T> remainingElements,
                                        Set<List<T>> result
    ) {
        if (remainingElements.isEmpty()) {
            return;
        }
        for (int i = 0; i < remainingElements.size(); i++) {
            List<T> reducedRemainingElements = remainingElements.subList(i + 1, remainingElements.size());
            T nextElement = remainingElements.get(i);
            currentSublist.addLast(nextElement);
            result.add(new ArrayList<>(currentSublist));
            allSublists(currentSublist, reducedRemainingElements, result);
            currentSublist.removeLast();
        }
    }
}
