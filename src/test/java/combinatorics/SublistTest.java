package combinatorics;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SublistTest {

    @Test
    void allConsecutiveSublists_from_empty_list_returns_empty_set() {
        Set<List<String>> result = Sublist.allConsecutiveSublists(emptyList());

        assertEquals(emptySet(), result);
    }

    @Test
    void allConsecutiveSublists_from_one_element_list_returns_only_the_one_element_list() {
        Set<List<String>> result = Sublist.allConsecutiveSublists(List.of("a"));

        assertEquals(Set.of(List.of("a")), result);
    }

    @Test
    void allConsecutiveSublists_from_two_element_list() {
        Set<List<String>> result = Sublist.allConsecutiveSublists(List.of("a", "b"));

        assertEquals(Set.of(List.of("a"), List.of("a", "b"), List.of("b")), result);
    }

    @Test
    void allConsecutiveSublists_from_three_element_list() {
        Set<List<String>> result = Sublist.allConsecutiveSublists(List.of("a", "b", "c"));

        assertEquals(
            Set.of(
                List.of("a"),
                List.of("a", "b"),
                List.of("a", "b", "c"),
                List.of("b"),
                List.of("b", "c"),
                List.of("c")
            ),
            result
        );
    }

    @Test
    void allConsecutiveSublists_from_four_element_list() {
        Set<List<String>> result = Sublist.allConsecutiveSublists(List.of("d", "a", "b", "c"));

        assertEquals(
            Set.of(
                List.of("d"),
                List.of("d", "a"),
                List.of("d", "a", "b"),
                List.of("d", "a", "b", "c"),
                List.of("a"),
                List.of("a", "b"),
                List.of("a", "b", "c"),
                List.of("b"),
                List.of("b", "c"),
                List.of("c")
            ),
            result
        );
    }

    @Test
    void allSublists_from_empty_list_gives_empty_set() {
        Set<List<String>> result = Sublist.allSublists(emptyList());

        assertEquals(emptySet(), result);
    }

    @Test
    void allSublists_from_one_element_list_returns_only_the_one_element_list() {
        Set<List<String>> result = Sublist.allSublists(List.of("a"));

        assertEquals(Set.of(List.of("a")), result);
    }

    @Test
    void allSublists_from_two_element_list() {
        Set<List<String>> result = Sublist.allSublists(List.of("a", "b"));

        assertEquals(Set.of(List.of("a"), List.of("a", "b"), List.of("b")), result);
    }

    @Test
    void allSublists_from_three_element_list() {
        Set<List<String>> result = Sublist.allSublists(List.of("a", "b", "c"));

        assertEquals(
            Set.of(
                List.of("a"),
                List.of("a", "b"),
                List.of("a", "b", "c"),
                List.of("b"),
                List.of("b", "c"),
                List.of("c"),
                List.of("a", "c")
            ),
            result
        );
    }

    @Test
    void allSublists_from_four_element_list() {
        Set<List<String>> result = Sublist.allSublists(List.of("d", "a", "b", "c"));

        assertEquals(
            Set.of(
                List.of("d"),
                List.of("d", "a"),
                List.of("d", "a", "b"),
                List.of("d", "a", "b", "c"),
                List.of("a"),
                List.of("a", "b"),
                List.of("a", "b", "c"),
                List.of("b"),
                List.of("b", "c"),
                List.of("c"),
                List.of("d", "a", "c"),
                List.of("d", "b"),
                List.of("d", "b", "c"),
                List.of("d", "c"),
                List.of("a", "c")
            ),
            result
        );
    }
}