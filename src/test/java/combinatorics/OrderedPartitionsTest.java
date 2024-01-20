package combinatorics;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderedPartitionsTest {

    @Test
    void for_0_elements_on_0_containers() {
        assertEquals(
                emptySet(),
                OrderedPartitions.partitionFrom0ToIn(0, 0)
        );
    }

    @Test
    void for_0_elements_on_1_containers() {
        assertEquals(
                emptySet(),
                OrderedPartitions.partitionFrom0ToIn(0, 1)
        );
    }

    @Test
    void for_1_elements_on_0_containers() {
        assertEquals(
                emptySet(),
                OrderedPartitions.partitionFrom0ToIn(1, 0)
        );
    }

    @Test
    void for_1_elements_on_1_containers() {
        assertEquals(
                Set.of(
                        List.of(Set.of(0))
                ),
                OrderedPartitions.partitionFrom0ToIn(1, 1)
        );
    }

    @Test
    void for_3_elements_on_1_containers() {
        assertEquals(
                Set.of(
                        List.of(Set.of(0, 1, 2))
                ),
                OrderedPartitions.partitionFrom0ToIn(3, 1)
        );
    }

    @Test
    void for_2_elements_on_2_containers() {
        assertEquals(
                Set.of(
                        List.of(
                                Set.of(0, 1),
                                Set.of()
                        ),
                        List.of(
                                Set.of(0),
                                Set.of(1)
                        ),
                        List.of(
                                Set.of(1),
                                Set.of(0)
                        ),
                        List.of(
                                Set.of(),
                                Set.of(0, 1)
                        )
                ),
                OrderedPartitions.partitionFrom0ToIn(2, 2)
        );
    }

    @Test
    void for_2_elements_on_3_containers() {
        assertEquals(
                Set.of(
                        List.of(
                                Set.of(0, 1),
                                Set.of(),
                                Set.of()
                        ),
                        List.of(
                                Set.of(),
                                Set.of(0, 1),
                                Set.of()
                        ),
                        List.of(
                                Set.of(),
                                Set.of(),
                                Set.of(0, 1)
                        ),
                        List.of(
                                Set.of(0),
                                Set.of(1),
                                Set.of()
                        ),
                        List.of(
                                Set.of(1),
                                Set.of(0),
                                Set.of()
                        ),
                        List.of(
                                Set.of(),
                                Set.of(0),
                                Set.of(1)
                        ),
                        List.of(
                                Set.of(),
                                Set.of(1),
                                Set.of(0)
                        ),
                        List.of(
                                Set.of(0),
                                Set.of(),
                                Set.of(1)
                        ),
                        List.of(
                                Set.of(1),
                                Set.of(),
                                Set.of(0)
                        )
                ),
                OrderedPartitions.partitionFrom0ToIn(2, 3)
        );
    }

    // TODO more tests for integerPartition

    // TODO more tests for unorderedPartition
}