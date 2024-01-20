package combinatorics;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static java.util.Collections.emptySet;

public class UnorderedBiPartitions {

    /**
     * @return a {@code Set<Set<Set<T>>>} where the outermost set contains the found bi-partitions; each bi-partition
     * is represented as a {@code Set<Set<T>>} of size 2, which means the first partition is a {@code Set<T>} containing
     * the elements of partition 1 and the second partition is a {@Code Set<T>} containing the elements of partition 2.
     * <br><br>
     * For example, given a set like {1, 2, 3} the result will be
     * <pre>
     *     {
     *         {
     *             {1, 2}, {3}
     *         },
     *         {
     *             {1, 3}, {2}
     *         },
     *         {
     *             {2, 3}, {1}
     *         }
     *     }
     * </pre>
     */
    public static <T> Set<Set<Set<T>>> from(Set<T> set) {
        if (set.size() < 2) {
            return emptySet();
        }
        return computeRecursively(set);
    }

    private static <T> Set<Set<Set<T>>> computeRecursively(Set<T> set) {
        if (set.size() == 2) {
            Iterator<T> iterator = set.iterator();
            Set<Set<Set<T>>> allBiPartitions = new HashSet<>();
            allBiPartitions.add(Set.of(Set.of(iterator.next()), Set.of(iterator.next())));
            return allBiPartitions;
        }
        Set<T> oneElementSet = Set.of(set.iterator().next());
        Set<T> reducedSet = Sets.difference(set, oneElementSet);
        Set<Set<Set<T>>> allBiPartitions = computeRecursively(reducedSet);
        return combine(allBiPartitions, oneElementSet);
    }

    private static <T> Set<Set<Set<T>>> combine(Set<Set<Set<T>>> existingBiPartitions, Set<T> oneElementSet) {
        Set<Set<Set<T>>> enhancedBiPartitions = new HashSet<>();
        for (Set<Set<T>> biPartition : existingBiPartitions) {
            Iterator<Set<T>> iterator = biPartition.iterator();
            Set<T> partition1 = iterator.next();
            Set<T> partition2 = iterator.next();
            Set<T> partition1Enhanced = Sets.union(partition1, oneElementSet);
            Set<T> partition2Enhanced = Sets.union(partition2, oneElementSet);

            enhancedBiPartitions.add(Set.of(partition1, partition2Enhanced));
            enhancedBiPartitions.add(Set.of(partition1Enhanced, partition2));
        }
        Set<T> allPreviousElements = existingBiPartitions.iterator().next().stream().reduce(Sets::union).orElseThrow();
        enhancedBiPartitions.add(Set.of(oneElementSet, allPreviousElements));
        return enhancedBiPartitions;
    }
}
