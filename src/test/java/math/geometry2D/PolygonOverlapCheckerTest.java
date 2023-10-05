package math.geometry2D;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PolygonOverlapCheckerTest {

    public static final Parallelogram SQUARE_AT_1_0 = new Parallelogram(
            new Vector2D(1, 0),
            new Vector2D(0, 2),
            new Vector2D(2, 0)
    );

    public static final Parallelogram SQUARE_AT_2_0 = new Parallelogram(
            new Vector2D(2, 0),
            new Vector2D(0, 2),
            new Vector2D(2, 0)
    );

    public static final Parallelogram SQUARE_AT_5_0 = new Parallelogram(
            new Vector2D(5, 0),
            new Vector2D(0, 2),
            new Vector2D(2, 0)
    );

    @Test
    void hasOverlap_two_disjunct_squares_have_no_overlap() {
        assertFalse(PolygonOverlapChecker.haveOverlap(SQUARE_AT_1_0, SQUARE_AT_5_0));
    }

    @Test
    void hasOverlap_two_almost_touching_squares_have_no_overlap() {
        Parallelogram squareSlightlyBesideOther = new Parallelogram(
                new Vector2D(3.00000000001, 0),
                new Vector2D(0, 2),
                new Vector2D(2, 0)
        );
        assertFalse(PolygonOverlapChecker.haveOverlap(SQUARE_AT_1_0, squareSlightlyBesideOther));
    }

    @Test
    void hasOverlap_two_overlapping_squares_at_same_vertical_position() {
        assertTrue(PolygonOverlapChecker.haveOverlap(SQUARE_AT_1_0, SQUARE_AT_2_0));
    }

    @Test
    void hasOverlap_two_overlapping_squares_at_different_vertical_positions() {
        Parallelogram shiftedDownAndRight = new Parallelogram(
                new Vector2D(2, -1),
                new Vector2D(0, 2),
                new Vector2D(2, 0)
        );
        assertTrue(PolygonOverlapChecker.haveOverlap(SQUARE_AT_1_0, shiftedDownAndRight));
    }

    @Test
    void hasOverlap_two_squares_touching_at_edge() {
        Parallelogram touchingSquare = new Parallelogram(
                new Vector2D(0, -1),
                new Vector2D(0, 1),
                new Vector2D(1, 0)
        );
        assertTrue(PolygonOverlapChecker.haveOverlap(SQUARE_AT_1_0, touchingSquare));
    }

    @Test
    void hasOverlap_diamond_around_origin_and_rectangle_that_overlaps_one_corner() {
        Parallelogram diamond = new Parallelogram(
                new Vector2D(-1, 0),
                new Vector2D(1, 1),
                new Vector2D(1, -1)
        );
        Parallelogram rectangle = new Parallelogram(
                new Vector2D(0.5, 0),
                new Vector2D(0, 1),
                new Vector2D(1, 0)
        );

        assertTrue(PolygonOverlapChecker.haveOverlap(diamond, rectangle));
    }
}