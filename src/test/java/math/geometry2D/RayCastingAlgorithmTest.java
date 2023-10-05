package math.geometry2D;

import org.junit.jupiter.api.Test;

import static math.geometry2D.RayCastingAlgorithm.isInside;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RayCastingAlgorithmTest {

    public static final Parallelogram UNIT_SQUARE_AT_1_0 = new Parallelogram(
            new Vector2D(1, 0),
            new Vector2D(0, 1),
            new Vector2D(1, 0)
    );

    @Test
    void isInside_point_which_is_left_of_parallelogram_gives_false() {
        Vector2D point = new Vector2D(0, 0);

        assertFalse(isInside(point, UNIT_SQUARE_AT_1_0));
    }

    @Test
    void isInside_point_which_is_right_of_parallelogram_gives_false() {
        Vector2D point = new Vector2D(3, 0);

        assertFalse(isInside(point, UNIT_SQUARE_AT_1_0));
    }

    @Test
    void isInside_point_which_is_top_of_parallelogram_gives_false() {
        Vector2D point = new Vector2D(1.5, 2);

        assertFalse(isInside(point, UNIT_SQUARE_AT_1_0));
    }

    @Test
    void isInside_point_which_is_below_parallelogram_gives_false() {
        Vector2D point = new Vector2D(1.5, -2);

        assertFalse(isInside(point, UNIT_SQUARE_AT_1_0));
    }

    @Test
    void isInside_vertexes_are_considered_inside_parallelogram() {
        Vector2D bottomLeft = new Vector2D(1, 0);
        Vector2D topLeft = new Vector2D(1, 1);
        Vector2D bottomRight = new Vector2D(2, 0);
        Vector2D topRight = new Vector2D(2, 1);

        assertTrue(isInside(bottomLeft, UNIT_SQUARE_AT_1_0));
        assertTrue(isInside(topLeft, UNIT_SQUARE_AT_1_0));
        assertTrue(isInside(bottomRight, UNIT_SQUARE_AT_1_0));
        assertTrue(isInside(topRight, UNIT_SQUARE_AT_1_0));
    }

    @Test
    void isInside_points_on_edges_are_considered_inside_parallelogram() {
        Vector2D bottom = new Vector2D(1.4, 0);
        Vector2D left = new Vector2D(1, 0.5555555);
        Vector2D right = new Vector2D(2, 0.000000001);
        Vector2D top = new Vector2D(1.999, 1);

        assertTrue(isInside(bottom, UNIT_SQUARE_AT_1_0));
        assertTrue(isInside(left, UNIT_SQUARE_AT_1_0));
        assertTrue(isInside(right, UNIT_SQUARE_AT_1_0));
        assertTrue(isInside(top, UNIT_SQUARE_AT_1_0));
    }

    @Test
    void isInside_points_well_inside_are_considered_inside_parallelogram() {
        Vector2D point1 = new Vector2D(1.5, 0.5);
        Vector2D point2 = new Vector2D(1.1, 0.9);
        Vector2D point3 = new Vector2D(1.99999, 0.99999);
        Vector2D point4 = new Vector2D(1.00001, 0.5);

        assertTrue(isInside(point1, UNIT_SQUARE_AT_1_0));
        assertTrue(isInside(point2, UNIT_SQUARE_AT_1_0));
        assertTrue(isInside(point3, UNIT_SQUARE_AT_1_0));
        assertTrue(isInside(point4, UNIT_SQUARE_AT_1_0));
    }

    @Test
    void isInside_point_outside_but_under_the_roof_of_tilted_parallelogram_is_found_outside() {
        Parallelogram tiltedParallelogram = new Parallelogram(
                new Vector2D(0, 0),
                new Vector2D(2, 2),
                new Vector2D(1, 0)
        );
        Vector2D underTheRightRoof = new Vector2D(1.5, 0.1);
        Vector2D aboveTheLeftRoof = new Vector2D(0.5, 1);

        assertFalse(isInside(underTheRightRoof, tiltedParallelogram));
        assertFalse(isInside(aboveTheLeftRoof, tiltedParallelogram));
    }

    @Test
    void isInside_origin_in_diamond_around_origin() {
        Parallelogram diamond = new Parallelogram(
                new Vector2D(-1, 0),
                new Vector2D(1, 1),
                new Vector2D(1, -1)
        );
        Vector2D origin = new Vector2D(0.0, 0.0);

        assertTrue(isInside(origin, diamond));
    }


}