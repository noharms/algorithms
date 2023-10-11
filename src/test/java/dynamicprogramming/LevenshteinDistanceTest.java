package dynamicprogramming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LevenshteinDistanceTest {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    @Test
    void empty_words_have_0_distance() {
        assertEquals(0, new LevenshteinDistance("", "").compute());
    }

    @Test
    void a_word_of_n_letters_has_a_distance_n_to_the_empty_string() {
        assertEquals(1, new LevenshteinDistance("a", "").compute());
        assertEquals(2, new LevenshteinDistance("ab", "").compute());
        assertEquals(3, new LevenshteinDistance("abc", "").compute());
        assertEquals(4, new LevenshteinDistance("abcd", "").compute());
        assertEquals(5, new LevenshteinDistance("abcde", "").compute());
        assertEquals(6, new LevenshteinDistance("abcdef", "").compute());
    }

    @Test
    void special_characters_are_allowed() {
        assertEquals(5, new LevenshteinDistance("abcd", "!(%&)").compute());
    }

    @Test
    void the_same_words_have_0_distance() {
        assertEquals(0, new LevenshteinDistance("a", "a").compute());
        assertEquals(0, new LevenshteinDistance("abc", "abc").compute());
        assertEquals(0, new LevenshteinDistance("cba", "cba").compute());
        assertEquals(0, new LevenshteinDistance("AaA", "AaA").compute());
        assertEquals(0, new LevenshteinDistance(".AaBbCcXx.", ".AaBbCcXx.").compute());
    }

    @Test
    void words_of_same_length_but_different_letters_have_distance_equal_to_the_length() {
        assertEquals(1, new LevenshteinDistance("a", "x").compute());
        assertEquals(3, new LevenshteinDistance("abc", "xyz").compute());
        assertEquals(4, new LevenshteinDistance("abcd", "!?=/").compute());
    }

    @Test
    void same_letters_but_in_wrong_order_so_that_no_consecutive_substring_is_contained_larger_than_1() {
        assertEquals("abcdefg".length() - 1, new LevenshteinDistance("abcdefg", "gfedcba").compute());
    }

    @Test
    void word2_is_substring_of_word1_so_the_distance_is_given_by_the_additional_letters_in_word1() {
        assertEquals(2, new LevenshteinDistance("abcdefg", "bcdef").compute());
        assertEquals(ALPHABET.length() - "abc".length(), new LevenshteinDistance(ALPHABET, "abc").compute());
        assertEquals(ALPHABET.length() - "ef".length(), new LevenshteinDistance(ALPHABET, "ef").compute());
        assertEquals(ALPHABET.length() - "mnopqrstu".length(), new LevenshteinDistance(ALPHABET, "mnopqrstu").compute());
        assertEquals(ALPHABET.length() - "x".length(), new LevenshteinDistance(ALPHABET, "x").compute());
        assertEquals(ALPHABET.length() - "yz".length(), new LevenshteinDistance(ALPHABET, "yz").compute());
    }

    @Test
    void chars_of_word2_appear_in_the_same_order_in_word1_so_distance_is_given_by_additional_chars() {
        assertEquals(4, new LevenshteinDistance("abcdefg", "bdf").compute());
        assertEquals(4, new LevenshteinDistance("abcdefg", "abg").compute());
        assertEquals(4, new LevenshteinDistance("abcdefg", "abg").compute());
    }

    @Test
    void distance_is_symmetric() {
        assertEquals(new LevenshteinDistance("a", "").compute(), new LevenshteinDistance("", "a").compute());
        assertEquals(new LevenshteinDistance("ab", "").compute(), new LevenshteinDistance("", "ab").compute());
        assertEquals(new LevenshteinDistance("abc", "").compute(), new LevenshteinDistance("", "abc").compute());
        assertEquals(new LevenshteinDistance("abcd", "").compute(), new LevenshteinDistance("", "abcd").compute());
        assertEquals(new LevenshteinDistance("abcde", "").compute(), new LevenshteinDistance("", "abcde").compute());
        assertEquals(new LevenshteinDistance("abcdef", "").compute(), new LevenshteinDistance("", "abcdef").compute());
        assertEquals(
                new LevenshteinDistance("bcdef", "abcdefg").compute(),
                new LevenshteinDistance("abcdefg", "bcdef").compute()
        );
    }
}