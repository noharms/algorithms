package math.geometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParallelogramTest {

    private static final Vector2D POINT_1_1 = new Vector2D(1., 1.);
    private static final Vector2D VECTOR_4_0 = new Vector2D(4., 0.);
    private static final Vector2D VECTOR_0_4 = new Vector2D(0., 4.);

    private static final Parallelogram SQUARE_FULLY_QUADRANT_1 = new Parallelogram(POINT_1_1, VECTOR_4_0, VECTOR_0_4);
    private static final Parallelogram SQUARE_OVERLAP_ALL_QUADRANTS = new Parallelogram(
            POINT_1_1.negative(), VECTOR_4_0, VECTOR_0_4
    );

    @Test
    void corner_points_of_a_square_are_computed_as_expected() {
        assertEquals(new Vector2D(1., 1.), SQUARE_FULLY_QUADRANT_1.a());
        assertEquals(new Vector2D(5., 1.), SQUARE_FULLY_QUADRANT_1.b());
        assertEquals(new Vector2D(1., 5.), SQUARE_FULLY_QUADRANT_1.c());
        assertEquals(new Vector2D(5., 5.), SQUARE_FULLY_QUADRANT_1.d());
    }

    @Test
    void corner_points_of_a_square_which_covers_negative_numbers_are_computed_as_expected() {
        assertEquals(new Vector2D(-1., -1.), SQUARE_OVERLAP_ALL_QUADRANTS.a());
        assertEquals(new Vector2D(3., -1.), SQUARE_OVERLAP_ALL_QUADRANTS.b());
        assertEquals(new Vector2D(-1., 3.), SQUARE_OVERLAP_ALL_QUADRANTS.c());
        assertEquals(new Vector2D(3., 3.), SQUARE_OVERLAP_ALL_QUADRANTS.d());
    }

}