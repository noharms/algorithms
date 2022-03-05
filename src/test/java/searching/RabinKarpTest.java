package searching;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RabinKarpTest {

    @Test
    void rollingHash_one_char() {
        int oldHash = RabinKarp.initialHash("a");
        int rollingHash = RabinKarp.rollingHash(oldHash, 1, 'a', 'b');
        assertEquals(RabinKarp.initialHash("b"), rollingHash);
    }

    @Test
    void rollingHash_two_chars() {
        int oldHash = RabinKarp.initialHash("ab");
        int rollingHash = RabinKarp.rollingHash(oldHash, 2, 'a', 'c');
        assertEquals(RabinKarp.initialHash("bc"), rollingHash);
    }

    @Test
    void rollingHash_three_chars() {
        int oldHash = RabinKarp.initialHash("abc");
        int rollingHash = RabinKarp.rollingHash(oldHash, 3, 'a', 'd');
        assertEquals(RabinKarp.initialHash("bcd"), rollingHash);
    }

    @Test
    void rollingHash_special_ascii_chars() {
        int oldHash = RabinKarp.initialHash(".-?");
        int rollingHash = RabinKarp.rollingHash(oldHash, 3, '.', '+');
        assertEquals(RabinKarp.initialHash("-?+"), rollingHash);
    }

    @Test
    void rollingHash_move_twice() {
        int oldHash = RabinKarp.initialHash("abc");
        int rollingHash = RabinKarp.rollingHash(oldHash, 3, 'a', 'd');
        rollingHash = RabinKarp.rollingHash(rollingHash, 3, 'b', 'e');
        assertEquals(RabinKarp.initialHash("cde"), rollingHash);
    }

    @Test
    void rollingHash_move_thrice() {
        int oldHash = RabinKarp.initialHash("abc");
        int rollingHash = RabinKarp.rollingHash(oldHash, 3, 'a', 'd');
        rollingHash = RabinKarp.rollingHash(rollingHash, 3, 'b', 'e');
        rollingHash = RabinKarp.rollingHash(rollingHash, 3, 'c', 'x');
        assertEquals(RabinKarp.initialHash("dex"), rollingHash);
    }

    @Test
    void rollingHash_with_overflow_factors() {
        assertThrows(IllegalArgumentException.class, () -> RabinKarp.initialHash("abcdefghi"));
    }

}