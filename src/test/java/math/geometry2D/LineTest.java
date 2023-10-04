package math.geometry2D;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {

    @Test
    void isVertical_for_vertical_line_gives_true() {
        Line line = new Line(new Vector2D(0, 0), new Vector2D(0, 1));

        assertTrue(line.isVerticalLine());
    }

    @Test
    void isVertical_for_horizontal_line_gives_false() {
        Line line = new Line(new Vector2D(0, 0), new Vector2D(1, 0));

        assertFalse(line.isVerticalLine());
    }


    @Test
    void isVertical_for_inclined_line_gives_false() {
        Line line = new Line(new Vector2D(0, 0), new Vector2D(1, 4));

        assertFalse(line.isVerticalLine());
    }


    @Test
    void toLineFunction_for_simple_unit_diagonal() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(1, 1);
        LineSegment lineSegment = new LineSegment(a, b);
        Line line = lineSegment.extrapolateLine();

        assertEquals(new Line.LineFunction(1, 0), line.toLineFunction());
    }

    @Test
    void toLineFunction_for_longer_diagonal_is_equivalent() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(111, 111);
        LineSegment lineSegment = new LineSegment(a, b);
        Line line = lineSegment.extrapolateLine();

        assertEquals(new Line.LineFunction(1, 0), line.toLineFunction());
    }

    @Test
    void toLineFunction_for_inwards_diagonal_is_equivalent() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(-1, -1);
        LineSegment lineSegment = new LineSegment(a, b);
        Line line = lineSegment.extrapolateLine();

        assertEquals(new Line.LineFunction(1, 0), line.toLineFunction());
    }

    @Test
    void toLineFunction_for_non_trivial_line() {
        Vector2D a = new Vector2D(0, 2);
        Vector2D b = new Vector2D(1, 3);
        LineSegment lineSegment = new LineSegment(a, b);
        Line line = lineSegment.extrapolateLine();

        assertEquals(new Line.LineFunction(1, 2), line.toLineFunction());
    }

    @Test
    void toLineFunction_for_horizontal_line_on_x_axis() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(4, 0);
        LineSegment lineSegment = new LineSegment(a, b);
        Line line = lineSegment.extrapolateLine();

        assertEquals(new Line.LineFunction(0, 0), line.toLineFunction());
    }

    @Test
    void toLineFunction_for_horizontal_line_vertically_shifted() {
        Vector2D a = new Vector2D(0, -5);
        Vector2D b = new Vector2D(4, -5);
        LineSegment lineSegment = new LineSegment(a, b);
        Line line = lineSegment.extrapolateLine();

        assertEquals(new Line.LineFunction(0, -5), line.toLineFunction());
    }

    @Test
    void toLineFunction_for_vertical_exception_is_thrown() {
        Vector2D a = new Vector2D(0, -5);
        Vector2D b = new Vector2D(0, 5);
        LineSegment lineSegment = new LineSegment(a, b);
        Line line = lineSegment.extrapolateLine();

        assertThrows(UnsupportedOperationException.class, line::toLineFunction);
    }

    @Test
    void equals_on_the_same_line_gives_true() {
        Line line = new Line(new Vector2D(1, 1), new Vector2D(42.1, 42.2));

        assertEquals(line, line);
    }

    @Test
    void equals_on_another_start_point_but_same_direction_gives_false() {
        Line line = new Line(new Vector2D(1, 1), new Vector2D(1, 1));
        Line other = new Line(new Vector2D(0, 1), new Vector2D(1, 1));

        assertNotEquals(line, other);
    }

    @Test
    void equals_on_another_start_point_but_same_direction_gives_also_for_floating_point_numbers_false() {
        Line line = new Line(new Vector2D(1, 1), new Vector2D(42.1, 42.2));
        Line other = new Line(new Vector2D(0, 1), new Vector2D(42.1, 42.2));

        assertNotEquals(line, other);
    }

    @Test
    void equals_on_another_but_equivalent_start_point_gives_true() {
        Line line = new Line(new Vector2D(1, 1), new Vector2D(1, 1));
        Line other = new Line(new Vector2D(-1, -1), new Vector2D(1, 1));

        assertEquals(line, other);
    }

    @Test
    void equals_on_another_but_equivalent_start_point_gives_also_for_floating_point_numbers_true() {
        Line line = new Line(new Vector2D(1, 1), new Vector2D(42.3, 42.3));
        Line other = new Line(new Vector2D(-1, -1), new Vector2D(42.3, 42.3));

        assertEquals(line, other);
    }

    @Test
    void hashCode_two_lines_with_another_start_point_but_same_direction_is_different() {
        Line line = new Line(new Vector2D(1, 1), new Vector2D(1, 1));
        Line other = new Line(new Vector2D(0, 1), new Vector2D(1, 1));

        assertNotEquals(line.hashCode(), other.hashCode());
    }

    @Test
    void hashCode_two_lines_with_another_start_point_but_same_direction_is_also_for_floating_point_numbers_different() {
        Line line = new Line(new Vector2D(1, 1), new Vector2D(42.1, 42.2));
        Line other = new Line(new Vector2D(0, 1), new Vector2D(42.1, 42.2));

        assertNotEquals(line.hashCode(), other.hashCode());
    }

    @Test
    void hashCode_two_lines_with_another_but_equivalent_start_point_is_the_same() {
        Line line = new Line(new Vector2D(1, 1), new Vector2D(1, 1));
        Line other = new Line(new Vector2D(-1, -1), new Vector2D(1, 1));

        assertEquals(line.hashCode(), other.hashCode());
    }

    @Test
    void hashCode_two_lines_with_another_but_equivalent_start_point_is_also_for_floating_point_numbers_same() {
        Line line = new Line(new Vector2D(1, 1), new Vector2D(42.3, 42.3));
        Line other = new Line(new Vector2D(-1, -1), new Vector2D(42.3, 42.3));

        assertEquals(line.hashCode(), other.hashCode());
    }
}