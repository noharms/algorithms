package searching.rabinkarp;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RabinKarpTest {

    @Test
    void empty_text_empty_word_returns_empty_list() {
        assertEquals(emptyList(), RabinKarp.findOccurences("", ""));
    }

    @Test
    void empty_text_returns_empty_list() {
        assertEquals(emptyList(), RabinKarp.findOccurences("abc", ""));
    }

    @Test
    void empty_word_returns_empty_list() {
        assertEquals(emptyList(), RabinKarp.findOccurences("", "abc"));
    }

    @Test
    void word_longer_than_text_returns_empty_list() {
        assertEquals(emptyList(), RabinKarp.findOccurences("abcd", "abc"));
    }

    @Test
    void one_letter_word_one_letter_text_match() {
        assertEquals(List.of(0), RabinKarp.findOccurences("a", "a"));
    }

    @Test
    void one_letter_word_one_letter_text_not_match() {
        assertEquals(emptyList(), RabinKarp.findOccurences("a", "b"));
    }

    @Test
    void two_letter_word_two_letter_text_match() {
        assertEquals(List.of(0), RabinKarp.findOccurences("aa", "aa"));
        assertEquals(List.of(0), RabinKarp.findOccurences("ab", "ab"));
    }

    @Test
    void two_letter_word_two_letter_text_not_match() {
        assertEquals(emptyList(), RabinKarp.findOccurences("aa", "ab"));
    }

    @Test
    void one_char_word_six_char_text_all_match() {
        assertEquals(List.of(0, 1, 2, 3, 4, 5), RabinKarp.findOccurences("a", "aaaaaa"));
    }

    @Test
    void one_char_word_six_char_text_some_but_not_all_match() {
        assertEquals(List.of(0, 2, 4), RabinKarp.findOccurences("a", "axaxax"));
    }

    @Test
    void three_char_word_repetition() {
        assertEquals(List.of(0, 3, 6), RabinKarp.findOccurences("abc", "abcabcabc"));
    }

    @Test
    void three_char_word_longer_text() {
        assertEquals(List.of(5, 16), RabinKarp.findOccurences("abc", "ab c abc ababac abc"));
    }

}