package searching.rabinkarp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PolynomialRollingHashComputerTest {

    private static final RollingHashComputer HASH_COMPUTER = new PolynomialRollingHashComputer(1000, 17);

    @Test
    void rollingHash_one_char() {
        long oldHash = HASH_COMPUTER.computeFullHash("a");
        long rollingHash = HASH_COMPUTER.rollToRight(oldHash, "abc".toCharArray(), 0, 1);
        assertEquals(HASH_COMPUTER.computeFullHash("b"), rollingHash);
    }

    @Test
    void rollingHash_one_char_staying_the_same() {
        long oldHash = HASH_COMPUTER.computeFullHash("a");
        long rollingHash = HASH_COMPUTER.rollToRight(oldHash, "aa".toCharArray(), 0, 1);
        assertEquals(oldHash, rollingHash);
    }

    @Test
    void rollingHash_one_char_starting_not_at_beginning_of_text() {
        long oldHash = HASH_COMPUTER.computeFullHash("c");
        long rollingHash = HASH_COMPUTER.rollToRight(oldHash, "abcd".toCharArray(), 2, 1);
        assertEquals(HASH_COMPUTER.computeFullHash("d"), rollingHash);
    }

    @Test
    void rollingHash_two_chars() {
        long oldHash = HASH_COMPUTER.computeFullHash("ab");
        long rollingHash = HASH_COMPUTER.rollToRight(oldHash, "abc".toCharArray(), 0, 2);
        assertEquals(HASH_COMPUTER.computeFullHash("bc"), rollingHash);
    }

    @Test
    void rollingHash_three_chars() {
        long oldHash = HASH_COMPUTER.computeFullHash("abc");
        long rollingHash = HASH_COMPUTER.rollToRight(oldHash, "abcd".toCharArray(), 0, 3);
        assertEquals(HASH_COMPUTER.computeFullHash("bcd"), rollingHash);
    }

    @Test
    void rollingHash_special_ascii_chars() {
        long oldHash = HASH_COMPUTER.computeFullHash(".'?");
        long rollingHash = HASH_COMPUTER.rollToRight(oldHash, ".'?!".toCharArray(), 0, 3);
        assertEquals(HASH_COMPUTER.computeFullHash("'?!"), rollingHash);
    }

    @Test
    void rollingHash_move_twice() {
        long oldHash = HASH_COMPUTER.computeFullHash("a");
        long afterOneRoll = HASH_COMPUTER.rollToRight(oldHash, "abc".toCharArray(), 0, 1);
        long afterTwoRoll = HASH_COMPUTER.rollToRight(afterOneRoll, "abc".toCharArray(), 1, 1);
        assertEquals(HASH_COMPUTER.computeFullHash("c"), afterTwoRoll);
    }

    @Test
    void rollingHash_move_twice_two_char_window() {
        long oldHash = HASH_COMPUTER.computeFullHash("ab");
        long afterOneRoll = HASH_COMPUTER.rollToRight(oldHash, "abcd".toCharArray(), 0, 2);
        long afterTwoRoll = HASH_COMPUTER.rollToRight(afterOneRoll, "abcd".toCharArray(), 1, 2);
        assertEquals(HASH_COMPUTER.computeFullHash("cd"), afterTwoRoll);
    }

    @Test
    void rollingHash_move_thrice() {
        long oldHash = HASH_COMPUTER.computeFullHash("a");
        long afterOneRoll = HASH_COMPUTER.rollToRight(oldHash, "abcd".toCharArray(), 0, 1);
        long afterTwoRoll = HASH_COMPUTER.rollToRight(afterOneRoll, "abcd".toCharArray(), 1, 1);
        long afterThreeRoll = HASH_COMPUTER.rollToRight(afterTwoRoll, "abcd".toCharArray(), 2, 1);
        assertEquals(HASH_COMPUTER.computeFullHash("d"), afterThreeRoll);
    }

    @Test
    void rollingHash_with_overflow_factors() {
        assertThrows(IllegalArgumentException.class, () -> HASH_COMPUTER.computeFullHash("abcdefghi"));
    }

    @Test
    void rollingHash_bad_indices_returns_minus_1() {
        long afterOneRoll = HASH_COMPUTER.rollToRight(0, "abc".toCharArray(), 2, 2);
        assertEquals(-1, afterOneRoll);
    }
}