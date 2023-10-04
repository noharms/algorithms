package math.geometry2D;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LineIntersectionCalculatorTest {

    @Test
    void interSectionPoint_two_parallel_vertical_lines_throws() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(0, 1));
        Line line2 = new Line(new Vector2D(1, 0), new Vector2D(0, 1));

        assertThrows(IllegalArgumentException.class, () -> LineIntersectionCalculator.intersectionPoint(line1, line2));
    }

    @Test
    void interSectionPoint_two_parallel_horizontal_lines_throws() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(1, 0));
        Line line2 = new Line(new Vector2D(0, 1), new Vector2D(1, 0));

        assertThrows(IllegalArgumentException.class, () -> LineIntersectionCalculator.intersectionPoint(line1, line2));
    }

    @Test
    void interSectionPoint_two_parallel_tilted_lines_throws() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(1, 1));
        Line line2 = new Line(new Vector2D(0, -1), new Vector2D(1, 1));

        assertThrows(IllegalArgumentException.class, () -> LineIntersectionCalculator.intersectionPoint(line1, line2));
    }

    @Test
    void interSectionPoint_two_equivalent_vertical_lines_throws() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(0, 1));
        Line line2 = new Line(new Vector2D(0, 0), new Vector2D(0, 2));

        assertThrows(IllegalArgumentException.class, () -> LineIntersectionCalculator.intersectionPoint(line1, line2));
    }

    @Test
    void interSectionPoint_two_equivalent_horizontal_lines_throws() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(1, 0));
        Line line2 = new Line(new Vector2D(0, 0), new Vector2D(2, 0));

        assertThrows(IllegalArgumentException.class, () -> LineIntersectionCalculator.intersectionPoint(line1, line2));
    }

    @Test
    void interSectionPoint_two_equivalent_tilted_lines_throws() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(1, 1));
        Line line2 = new Line(new Vector2D(0, 0), new Vector2D(-2, -2));

        assertThrows(IllegalArgumentException.class, () -> LineIntersectionCalculator.intersectionPoint(line1, line2));
    }

    @Test
    void interSectionPoint_two_crossing_lines_is_correct() {
        Line line1 = new Line(new Vector2D(-1, 0), new Vector2D(1, 1));
        Line line2 = new Line(new Vector2D(1, 0), new Vector2D(1, -1));

        assertEquals(new Vector2D(0, 1), LineIntersectionCalculator.intersectionPoint(line1, line2));
    }

    @Test
    void interSectionPoint_two_lines_through_origin_is_correct() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(1, 1));
        Line line2 = new Line(new Vector2D(0, 0), new Vector2D(1, -1));

        assertEquals(new Vector2D(0, 0), LineIntersectionCalculator.intersectionPoint(line1, line2));
    }

    @Test
    void interSectionPoint_one_vertical_one_tilted_line_is_correct() {
        Line line1 = new Line(new Vector2D(-1, 0), new Vector2D(0, 1));
        Line line2 = new Line(new Vector2D(2, 4), new Vector2D(1, 2));

        assertEquals(new Vector2D(-1, -2), LineIntersectionCalculator.intersectionPoint(line1, line2));
    }

    @Test
    void interSectionPoint_one_horizontal_one_tilted_line_is_correct() {
        Line line1 = new Line(new Vector2D(0, 2), new Vector2D(1, 0));
        Line line2 = new Line(new Vector2D(1, 0), new Vector2D(1, 1));

        assertEquals(new Vector2D(3, 2), LineIntersectionCalculator.intersectionPoint(line1, line2));
    }
}