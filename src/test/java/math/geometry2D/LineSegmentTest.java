package math.geometry2D;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineSegmentTest {

    @Test
    void startToEnd_for_upwards_vertical_line() {
        Vector2D a = new Vector2D(4, 5);
        Vector2D b = new Vector2D(4, 8);
        LineSegment lineSegment = new LineSegment(a, b);

        assertEquals(new Vector2D(0, 3), lineSegment.startToEnd());
    }

    @Test
    void startToEnd_for_downwards_vertical_line() {
        Vector2D a = new Vector2D(4, 8);
        Vector2D b = new Vector2D(4, 5);
        LineSegment lineSegment = new LineSegment(a, b);

        assertEquals(new Vector2D(0, -3), lineSegment.startToEnd());
    }

    @Test
    void startToEnd_for_outwards_horizontal_line() {
        Vector2D a = new Vector2D(3, 5);
        Vector2D b = new Vector2D(4, 5);
        LineSegment lineSegment = new LineSegment(a, b);

        assertEquals(new Vector2D(1, 0), lineSegment.startToEnd());
    }

    @Test
    void startToEnd_for_inwards_horizontal_line() {
        Vector2D a = new Vector2D(4, 5);
        Vector2D b = new Vector2D(3, 5);
        LineSegment lineSegment = new LineSegment(a, b);

        assertEquals(new Vector2D(-1, 0), lineSegment.startToEnd());
    }

    @Test
    void startToEnd_for_diagonal_line() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(3, 3);
        LineSegment lineSegment = new LineSegment(a, b);

        assertEquals(new Vector2D(3, 3), lineSegment.startToEnd());
    }

    @Test
    void extrapolateLine_for_simple_unit_diagonal() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(1, 1);
        LineSegment lineSegment = new LineSegment(a, b);

        assertEquals(new Line(a, b.subtract(a)), lineSegment.extrapolateLine());
    }

    @Test
    void extrapolateLine_for_longer_diagonal_is_equivalent() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(111, 111);
        LineSegment lineSegment = new LineSegment(a, b);

        assertEquals(new Line(a, b.subtract(a)), lineSegment.extrapolateLine());
    }

    @Test
    void extrapolateLine_for_inwards_diagonal_is_equivalent() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(-1, -1);
        LineSegment lineSegment = new LineSegment(a, b);

        assertEquals(new Line(a, b.subtract(a)), lineSegment.extrapolateLine());
    }

    @Test
    void extrapolateLine_for_non_trivial_line() {
        Vector2D a = new Vector2D(0, 2);
        Vector2D b = new Vector2D(1, 3);
        LineSegment lineSegment = new LineSegment(a, b);

        assertEquals(new Line(a, b.subtract(a)), lineSegment.extrapolateLine());
    }

    @Test
    void extrapolateLine_for_horizontal_line_on_x_axis() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(4, 0);
        LineSegment lineSegment = new LineSegment(a, b);

        assertEquals(new Line(a, b.subtract(a)), lineSegment.extrapolateLine());
    }

    @Test
    void extrapolateLine_for_horizontal_line_vertically_shifted() {
        Vector2D a = new Vector2D(0, -5);
        Vector2D b = new Vector2D(4, -5);
        LineSegment lineSegment = new LineSegment(a, b);

        assertEquals(new Line(a, b.subtract(a)), lineSegment.extrapolateLine());
    }

    @Test
    void extrapolateLine_for_vertical_exception_is_thrown() {
        Vector2D a = new Vector2D(0, -5);
        Vector2D b = new Vector2D(0, 5);
        LineSegment lineSegment = new LineSegment(a, b);

        assertEquals(new Line(a, b.subtract(a)), lineSegment.extrapolateLine());
    }

    @Test
    void isVirtuallyVertical_for_vertical_line_gives_true() {
        LineSegment lineSegment = new LineSegment(new Vector2D(0, 0), new Vector2D(0, 1));

        assertTrue(lineSegment.isVirtuallyVertical());
    }

    @Test
    void isVirtuallyVertical_for_horizontal_line_gives_false() {
        LineSegment lineSegment = new LineSegment(new Vector2D(0, 0), new Vector2D(10, 0));

        assertFalse(lineSegment.isVirtuallyVertical());
    }

    @Test
    void isVirtuallyVertical_for_inclined_line_gives_false() {
        LineSegment lineSegment = new LineSegment(new Vector2D(0, 0), new Vector2D(10, 10));

        assertFalse(lineSegment.isVirtuallyVertical());
    }

    @Test
    void isVirtuallyVertical_for_gradient_greater_than_10_to_12_is_true() {
        LineSegment lineSegment = new LineSegment(new Vector2D(0, 0), new Vector2D(10e-7, 10e6));

        assertTrue(lineSegment.isVirtuallyVertical());
    }

    @Test
    void isVirtuallyVertical_for_gradient_equal_10_to_12_is_true() {
        LineSegment lineSegment = new LineSegment(new Vector2D(0, 0), new Vector2D(10e-7, 10e5));

        assertTrue(lineSegment.isVirtuallyVertical());
    }

    @Test
    void isVirtuallyVertical_for_gradient_less_than_10_to_12_is_true() {
        LineSegment lineSegment = new LineSegment(new Vector2D(0, 0), new Vector2D(10e-7, 10e4));

        assertFalse(lineSegment.isVirtuallyVertical());
    }

    @Test
    void contains_point_which_is_the_start_point_gives_true() {
        Vector2D startPoint = new Vector2D(0, 0);
        Vector2D endPoint = new Vector2D(1, 1);
        LineSegment lineSegment = new LineSegment(startPoint, endPoint);

        assertTrue(lineSegment.contains(startPoint));
    }

    @Test
    void contains_point_which_is_the_end_point_gives_true() {
        Vector2D startPoint = new Vector2D(0, 0);
        Vector2D endPoint = new Vector2D(1, 1);
        LineSegment lineSegment = new LineSegment(startPoint, endPoint);

        assertTrue(lineSegment.contains(endPoint));
    }

    @Test
    void contains_points_which_are_between_start_and_end_point_gives_true() {
        Vector2D startPoint = new Vector2D(0, 0);
        Vector2D endPoint = new Vector2D(1, 1);
        LineSegment lineSegment = new LineSegment(startPoint, endPoint);

        assertTrue(lineSegment.contains(new Vector2D(0.1, 0.1)));
        assertTrue(lineSegment.contains(new Vector2D(0.2, 0.2)));
        assertTrue(lineSegment.contains(new Vector2D(0.3, 0.3)));
        assertTrue(lineSegment.contains(new Vector2D(0.4, 0.4)));
        assertTrue(lineSegment.contains(new Vector2D(0.5, 0.5)));
        assertTrue(lineSegment.contains(new Vector2D(0.6, 0.6)));
        assertTrue(lineSegment.contains(new Vector2D(0.7, 0.7)));
        assertTrue(lineSegment.contains(new Vector2D(0.8, 0.8)));
        assertTrue(lineSegment.contains(new Vector2D(0.9, 0.9)));
    }

    @Test
    void contains_points_which_are_between_start_and_end_point_using_multiple_digits_gives_true() {
        Vector2D startPoint = new Vector2D(0, 0);
        Vector2D endPoint = new Vector2D(1, 1);
        LineSegment lineSegment = new LineSegment(startPoint, endPoint);

        assertTrue(lineSegment.contains(new Vector2D(0.333333, 0.333333)));
        assertTrue(lineSegment.contains(new Vector2D(0.9999, 0.9999)));
    }

    @Test
    void contains_points_which_are_obviously_off_gives_false() {
        Vector2D startPoint = new Vector2D(0, 0);
        Vector2D endPoint = new Vector2D(1, 1);
        LineSegment lineSegment = new LineSegment(startPoint, endPoint);

        assertFalse(lineSegment.contains(new Vector2D(-1, 0)));
        assertFalse(lineSegment.contains(new Vector2D(100, 200)));
    }

    @Test
    void contains_points_which_are_on_the_extrapolated_line_but_not_on_the_segment_gives_false() {
        Vector2D startPoint = new Vector2D(0, 0);
        Vector2D endPoint = new Vector2D(1, 1);
        LineSegment lineSegment = new LineSegment(startPoint, endPoint);

        assertFalse(lineSegment.contains(new Vector2D(-1, -1)));
        assertFalse(lineSegment.contains(new Vector2D(1.1, 1.1)));
    }

    @Test
    void contains_points_which_are_slightly_off_gives_false() {
        Vector2D startPoint = new Vector2D(0, 0);
        Vector2D endPoint = new Vector2D(1, 1);
        LineSegment lineSegment = new LineSegment(startPoint, endPoint);

        assertFalse(lineSegment.contains(new Vector2D(0.333333, 0.333334)));
        assertFalse(lineSegment.contains(new Vector2D(0.9999, 0.9998)));
    }
}