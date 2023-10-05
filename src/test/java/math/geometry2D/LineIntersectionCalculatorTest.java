package math.geometry2D;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LineIntersectionCalculatorTest {

    @Test
    void interSectionPoint_two_parallel_vertical_lines_gives_special_return_value() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(0, 1));
        Line line2 = new Line(new Vector2D(1, 0), new Vector2D(0, 1));

        assertEquals(Vector2D.NEGATIVE_INFINITY_VECTOR, LineIntersectionCalculator.intersectionPoint(line1, line2));
    }

    @Test
    void interSectionPoint_two_parallel_horizontal_lines_gives_special_return_value() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(1, 0));
        Line line2 = new Line(new Vector2D(0, 1), new Vector2D(1, 0));

        assertEquals(Vector2D.NEGATIVE_INFINITY_VECTOR, LineIntersectionCalculator.intersectionPoint(line1, line2));
    }

    @Test
    void interSectionPoint_two_parallel_tilted_lines_gives_special_return_value() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(1, 1));
        Line line2 = new Line(new Vector2D(0, -1), new Vector2D(1, 1));

        assertEquals(Vector2D.NEGATIVE_INFINITY_VECTOR, LineIntersectionCalculator.intersectionPoint(line1, line2));
    }

    @Test
    void interSectionPoint_two_equivalent_vertical_lines_gives_special_return_value() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(0, 1));
        Line line2 = new Line(new Vector2D(0, 0), new Vector2D(0, 2));

        assertEquals(Vector2D.POSITIVE_INFINITY_VECTOR, LineIntersectionCalculator.intersectionPoint(line1, line2));
    }

    @Test
    void interSectionPoint_two_equivalent_horizontal_lines_gives_special_return_value() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(1, 0));
        Line line2 = new Line(new Vector2D(0, 0), new Vector2D(2, 0));

        assertEquals(Vector2D.POSITIVE_INFINITY_VECTOR, LineIntersectionCalculator.intersectionPoint(line1, line2));
    }

    @Test
    void interSectionPoint_two_equivalent_tilted_lines_gives_special_return_value() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(1, 1));
        Line line2 = new Line(new Vector2D(0, 0), new Vector2D(-2, -2));

        assertEquals(Vector2D.POSITIVE_INFINITY_VECTOR, LineIntersectionCalculator.intersectionPoint(line1, line2));
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