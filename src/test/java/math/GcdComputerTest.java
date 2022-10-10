package math;

import org.junit.jupiter.api.Test;

import static math.GcdComputer.gcd;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GcdComputerTest {

    @Test
    void gcd_11_1() {
        assertEquals(1, gcd(1, 1));
    }

    @Test
    void gcd_10_1() {
        assertEquals(1, gcd(1, 0));
    }

    @Test
    void gcd_minus10_throws() {
        assertThrows(IllegalArgumentException.class, () -> gcd(-1, 0));
    }

    @Test
    void gcd_00_throws() {
        assertThrows(IllegalArgumentException.class, () -> gcd(-1, 0));
    }

    @Test
    void gcd_anyPositiveNumberWith1_returns1() {
        for (int i = 0; i < 100; i++) {
            assertEquals(1, gcd(i, 1));
        }
    }

    @Test
    void gcd_anyNumberWith0_returnsTheOtherNumber() {
        for (int i = 1; i < 100; i++) {
            assertEquals(i, gcd(i, 0));
        }
    }

    @Test
    void gcd_anyNumberWithItself_returnsTheNumber() {
        for (int i = 1; i < 100; i++) {
            assertEquals(i, gcd(i, i));
        }
    }

    @Test
    void gcd_16_24_8() {
        assertEquals(8, gcd(16, 24));
    }

    @Test
    void gcd_16_25_8() {
        assertEquals(1, gcd(16, 25));
    }

    @Test
    void gcd_15_25_8() {
        assertEquals(5, gcd(15, 25));
    }

}