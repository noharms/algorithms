package combinatorics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OrderedPartitions {

    /**
     * @param n the number of integers from 1 to n are going to be distributed on the given number of containers
     * @param k the number of containers that the partition consists of
     * @return all possible partitions of the numbers from 1 to n on k separate containers; each partition is
     */
    public static <T> Set<List<Set<Integer>>> partitionFrom0ToIn(int n, int k) {
        Set<Integer> containerIds = IntStream.range(0, k).boxed().collect(Collectors.toSet());
        Set<List<Integer>> allContainerAssignments = UrnModel.drawWithRepetitionWithOrder(containerIds, n);
        return allContainerAssignments
                .stream()
                .map(assignment -> createPartition(k, assignment))
                .collect(Collectors.toSet());
    }

    private static List<Set<Integer>> createPartition(int nContainers, List<Integer> containerIds) {
        int nElements = containerIds.size();
        List<Set<Integer>> partition = IntStream
                .range(0, nContainers)
                .<Set<Integer>>mapToObj(i -> new HashSet<>())
                .toList();
        for (int elementId = 0; elementId < nElements; elementId++) {
            int containerId = containerIds.get(elementId);
            partition.get(containerId).add(elementId);
        }
        return partition;
    }

    /**
     *  Partitioning n different elements into k containers is equivalent to "drawing a container-id for each element
     *  with repetition (a drawn container-id can be drawn again) with order". Thus, we can use the
     *  {@link UrnModel} by enumerating each element in the input set with an id from 0 to nElements, then drawing
     *  a container id for each element, and then building the partition from the drawing result.
     *  <br><br>
     *  Caveat: we assume that a container of a partition can be empty
     */
    public static <T> Set<List<Set<T>>> from(Set<T> set, int nContainers) {
        List<T> elementList = new ArrayList<>(set);
        return partitionFrom0ToIn(elementList.size(), nContainers)
                .stream()
                .map(partition -> translateIdsToObjects(partition, elementList))
                .collect(Collectors.toSet());
    }

    private static <T> List<Set<T>> translateIdsToObjects(List<Set<Integer>> partition, List<T> elementById) {
        return partition
                .stream()
                .map(elementIds -> elementIds.stream().map(elementById::get).collect(Collectors.toSet()))
                .toList();
    }
}
