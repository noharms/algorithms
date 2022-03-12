package combinatorics;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.emptySet;

public class PowerSet {

    public static <T> Set<Set<T>> from(Set<T> set) {
        Set<Set<T>> powerSet = new HashSet<>();
        powerSet.add(emptySet());
        for (T element : set) {
            Set<Set<T>> subsetsWithElement = new HashSet<>();
            for (Set<T> subset : powerSet) {
                Set<T> enhancedSubset = new HashSet<>(subset);
                enhancedSubset.add(element);
                subsetsWithElement.add(enhancedSubset);
            }
            powerSet.addAll(subsetsWithElement);
        }
        return powerSet;
    }
}
