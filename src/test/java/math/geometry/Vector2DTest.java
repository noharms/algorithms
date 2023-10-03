package math.geometry;

import org.junit.jupiter.api.Test;

import static java.lang.Double.NaN;
import static math.geometry.Vector2D.*;
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
}