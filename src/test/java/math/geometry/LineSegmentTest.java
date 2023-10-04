package math.geometry;

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
    void lineEquation_for_simple_unit_diagonal() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(1, 1);
        LineSegment lineSegment = new LineSegment(a, b);

        assertEquals("y = 1.00 * x + 0.00", lineSegment.extrapolatedLineEquation());
    }

    @Test
    void lineEquation_for_longer_diagonal_is_equivalent() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(111, 111);
        LineSegment lineSegment = new LineSegment(a, b);

        assertEquals("y = 1.00 * x + 0.00", lineSegment.extrapolatedLineEquation());
    }

    @Test
    void lineEquation_for_inwards_diagonal_is_equivalent() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(-1, -1);
        LineSegment lineSegment = new LineSegment(a, b);

        assertEquals("y = 1.00 * x + 0.00", lineSegment.extrapolatedLineEquation());
    }

    @Test
    void lineEquation_for_non_trivial_line() {
        Vector2D a = new Vector2D(0, 2);
        Vector2D b = new Vector2D(1, 3);
        LineSegment lineSegment = new LineSegment(a, b);

        assertEquals("y = 1.00 * x + 2.00", lineSegment.extrapolatedLineEquation());
    }

    @Test
    void lineEquation_for_horizontal_line_on_x_axis() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(4, 0);
        LineSegment lineSegment = new LineSegment(a, b);

        assertEquals("y = 0.00 * x + 0.00", lineSegment.extrapolatedLineEquation());
    }

    @Test
    void lineEquation_for_horizontal_line_vertically_shifted() {
        Vector2D a = new Vector2D(0, -5);
        Vector2D b = new Vector2D(4, -5);
        LineSegment lineSegment = new LineSegment(a, b);

        assertEquals("y = 0.00 * x + -5.00", lineSegment.extrapolatedLineEquation());
    }
}