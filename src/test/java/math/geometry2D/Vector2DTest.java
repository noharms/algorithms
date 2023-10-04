package math.geometry2D;

import org.junit.jupiter.api.Test;

import static java.lang.Double.NaN;
import static math.geometry2D.Vector2D.*;
import static org.junit.jupiter.api.Assertions.*;

class Vector2DTest {

    private static final Vector2D VECTOR_2_1 = new Vector2D(2., 1.);
    private static final Vector2D VECTOR_m2_1 = new Vector2D(-2., 1.);
    private static final Vector2D VECTOR_2_m1 = new Vector2D(2., -1.);
    private static final Vector2D VECTOR_m2_m1 = new Vector2D(-2., -1.);

    @Test
    void hashCode_in_double_class_returns_different_hashes_for_positive_zero_and_negative_zero() {
        assertNotEquals(Double.hashCode(POSITIVE_ZERO), Double.hashCode(NEGATIVE_ZERO));
    }

    @Test
    void negative_0_is_considered_equal_to_positive_0() {
        assertEquals(new Vector2D(-0.0, 0.0), ZERO_VECTOR);
        assertEquals(new Vector2D(0.0, -0.0), ZERO_VECTOR);
        assertEquals(new Vector2D(-0.0, -0.0), ZERO_VECTOR);
    }

    @Test
    void nan_for_x_value_throws() {
        assertThrows(IllegalArgumentException.class, () -> new Vector2D(NaN, 1.));
    }

    @Test
    void nan_for_y_value_throws() {
        assertThrows(IllegalArgumentException.class, () -> new Vector2D(1., NaN));
    }

    @Test
    void nan_for_x_and_y_value_throws() {
        assertThrows(IllegalArgumentException.class, () -> new Vector2D(NaN, NaN));
    }

    @Test
    void hash_of_negative_0_is_considered_equal_to_positive_0() {
        assertEquals(new Vector2D(-0.0, 0.0).hashCode(), ZERO_VECTOR.hashCode());
        assertEquals(new Vector2D(0.0, -0.0).hashCode(), ZERO_VECTOR.hashCode());
        assertEquals(new Vector2D(-0.0, -0.0).hashCode(), ZERO_VECTOR.hashCode());
    }

    @Test
    void add_zero_vector_gives_back_original_vector() {
        assertEquals(VECTOR_2_1, VECTOR_2_1.add(ZERO_VECTOR));
        assertEquals(VECTOR_m2_1, VECTOR_m2_1.add(ZERO_VECTOR));
        assertEquals(VECTOR_2_m1, VECTOR_2_m1.add(ZERO_VECTOR));
        assertEquals(VECTOR_m2_m1, VECTOR_m2_m1.add(ZERO_VECTOR));
    }

    @Test
    void add_non_zero_vector() {
        assertEquals(new Vector2D(4, 3), new Vector2D(1, 2).add(new Vector2D(3, 1)));
        assertEquals(new Vector2D(-2, 3), new Vector2D(1, 2).add(new Vector2D(-3, 1)));
        assertEquals(new Vector2D(-2, 1), new Vector2D(1, 2).add(new Vector2D(-3, -1)));
        assertEquals(new Vector2D(4, 1), new Vector2D(1, 2).add(new Vector2D(3, -1)));
    }

    @Test
    void negative_on_zero_vector_gives_back_zero_vector() {
        assertEquals(ZERO_VECTOR, ZERO_VECTOR.negative());
    }

    @Test
    void subtract_same_vector_gives_zero_vector() {
        Vector2D positiveValues = new Vector2D(42.1, 2.3);
        Vector2D negativeValues = new Vector2D(-42.1, -2.3);
        Vector2D mixedValues = new Vector2D(42.1, -2.3);
        assertEquals(ZERO_VECTOR, positiveValues.subtract(positiveValues));
        assertEquals(ZERO_VECTOR, negativeValues.subtract(negativeValues));
        assertEquals(ZERO_VECTOR, mixedValues.subtract(mixedValues));
    }

    @Test
    void length_simple_unit_lengths() {
        assertEquals(1., new Vector2D(1, 0).length(), DOUBLE_PRECISION);
        assertEquals(1., new Vector2D(0, 1).length(), DOUBLE_PRECISION);
        assertEquals(1., new Vector2D(-1, 0).length(), DOUBLE_PRECISION);
        assertEquals(1., new Vector2D(0, -1).length(), DOUBLE_PRECISION);
    }

    @Test
    void length_more_difficult_examples() {
        assertEquals(42.3, new Vector2D(42.3, 0).length(), DOUBLE_PRECISION);
        assertEquals(42.3, new Vector2D(0, 42.3).length(), DOUBLE_PRECISION);
        assertEquals(Math.sqrt(2), new Vector2D(1, 1).length(), DOUBLE_PRECISION);
        assertEquals(Math.sqrt(2), new Vector2D(1, -1).length(), DOUBLE_PRECISION);
        assertEquals(5, new Vector2D(3, 4).length(), DOUBLE_PRECISION);
    }

    @Test
    void normalize_gives_unit_vector() {
        assertEquals(1., new Vector2D(1, 2).normalize().length(), DOUBLE_PRECISION);
        assertEquals(1., new Vector2D(-1, 2).normalize().length(), DOUBLE_PRECISION);
        assertEquals(1., new Vector2D(42.3, 2).normalize().length(), DOUBLE_PRECISION);
        assertEquals(1., new Vector2D(42.3, 42.4).normalize().length(), DOUBLE_PRECISION);
    }
}