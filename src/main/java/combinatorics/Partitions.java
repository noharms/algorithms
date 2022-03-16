package combinatorics;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static com.google.common.math.IntMath.binomial;

public class Partitions {

    public static <T> Set<Set<Set<T>>> allPartitions(Set<T> set) {
        return computeRecursively(set);
    }

    private static <T> Set<Set<Set<T>>> computeRecursively(Set<T> set) {
        if (set.size() == 1) {
            return Set.of(Set.of(set));
        }
        T pivotElement = set.iterator().next();
        Set<T> reducedSet = new HashSet<>(set);
        reducedSet.remove(pivotElement);
        Set<Set<Set<T>>> reducedPartitions = computeRecursively(reducedSet);
        return allPartitions(reducedPartitions, pivotElement);
    }

    private static <T> Set<Set<Set<T>>> allPartitions(Set<Set<Set<T>>> basePartitions, T addendum) {
        Set<Set<Set<T>>> enhancedPartitions = new HashSet<>();
        for (Set<Set<T>> partition : basePartitions) {
            enhancedPartitions.addAll(allPossibleDistributions(partition, addendum));
            enhancedPartitions.add(enhance(partition, Set.of(addendum)));
        }
        return enhancedPartitions;
    }

    // given a single partition and an additional element, compute all partitions that can be obtained when adding
    // the addendum to one of the existing member sets
    private static <T> Set<Set<Set<T>>> allPossibleDistributions(Set<Set<T>> basePartition, T addendum) {
        Set<Set<Set<T>>> partitionsWithAddendum = new HashSet<>();
        for (Set<T> memberSet : basePartition) {
            Set<Set<T>> newPartition = new HashSet<>(basePartition);
            newPartition.remove(memberSet);

            Set<T> withPivot = new HashSet<>(memberSet);
            withPivot.add(addendum);

            newPartition.add(withPivot);
            partitionsWithAddendum.add(newPartition);
        }
        return partitionsWithAddendum;
    }

    private static <T> Set<Set<T>> enhance(Set<Set<T>> partition, Set<T> newMemberSet) {
        Set<Set<T>> copy = new HashSet<>(partition);
        copy.add(newMemberSet);
        return copy;
    }

    public static <T> int bellNumber(int nElements) {
        int[] allBellNumbers = new int[nElements + 1];
        allBellNumbers[0] = 1;
        allBellNumbers[1] = 1;
        return bellRecursive(nElements, allBellNumbers);
    }

    private static int bellRecursive(int n, int[] previousBellNumbers) {
        if (n <= 1) {
            return 1;
        }
        previousBellNumbers[n - 1] = bellRecursive(n - 1, previousBellNumbers);
        int[] binomials = IntStream.range(0, n).map(i -> binomial(n - 1, i)).toArray();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += binomials[i] * previousBellNumbers[i];
        }
        return sum;
    }
}
