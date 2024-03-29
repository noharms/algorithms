package math.geometry2D;

import org.junit.jupiter.api.Test;

import static math.geometry2D.LineSegmentIntersectionCalculator.intersectionPoint;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LineSegmentIntersectionCalculatorTest {

    @Test
    void intersectionPoint_two_diagonals_through_origin_both_ascending() {
        LineSegment lineSegment1 = new LineSegment(new Vector2D(-1, -1), new Vector2D(1, 1));
        LineSegment lineSegment2 = new LineSegment(new Vector2D(1, -1), new Vector2D(-1, 1));

        assertEquals(new Vector2D(0, 0), intersectionPoint(lineSegment1, lineSegment2));
    }

    @Test
    void intersectionPoint_two_diagonals_through_origin_both_descending() {
        LineSegment lineSegment1 = new LineSegment(new Vector2D(1, 1), new Vector2D(-1, -1));
        LineSegment lineSegment2 = new LineSegment(new Vector2D(-1, 1), new Vector2D(1, -1));

        assertEquals(new Vector2D(0, 0), intersectionPoint(lineSegment1, lineSegment2));
    }

    @Test
    void intersectionPoint_two_diagonals_through_origin_one_ascending_one_descending() {
        LineSegment lineSegment1 = new LineSegment(new Vector2D(-1, -1), new Vector2D(1, 1));
        LineSegment lineSegment2 = new LineSegment(new Vector2D(-1, 1), new Vector2D(1, -1));

        assertEquals(new Vector2D(0, 0), intersectionPoint(lineSegment1, lineSegment2));
    }

    @Test
    void intersectionPoint_two_diagonals_through_x_1_y_0() {
        LineSegment lineSegment1 = new LineSegment(new Vector2D(0, -1), new Vector2D(2, 1));
        LineSegment lineSegment2 = new LineSegment(new Vector2D(0, 1), new Vector2D(2, -1));

        assertEquals(new Vector2D(1, 0), intersectionPoint(lineSegment1, lineSegment2));
    }

    @Test
    void intersectionPoint_one_steep_ascending_line_below_one_shallow_ascending_line() {
        LineSegment lineSegment1 = new LineSegment(new Vector2D(0, 0), new Vector2D(2, 2));
        LineSegment lineSegment2 = new LineSegment(new Vector2D(0, -3), new Vector2D(2, 5));

        assertEquals(new Vector2D(1, 1), intersectionPoint(lineSegment1, lineSegment2));
    }

    @Test
    void intersectionPoint_of_the_same_line_with_itself() {
        LineSegment lineSegment1 = new LineSegment(new Vector2D(0, 0), new Vector2D(1, 1));

        assertEquals(Vector2D.POSITIVE_INFINITY_VECTOR, intersectionPoint(lineSegment1, lineSegment1));
    }

    @Test
    void intersectionPoint_two_overlapping_segments_of_the_same_line() {
        LineSegment lineSegment1 = new LineSegment(new Vector2D(0, 0), new Vector2D(1, 1));
        LineSegment lineSegment2 = new LineSegment(new Vector2D(0.5, 0.5), new Vector2D(2, 2));

        assertEquals(Vector2D.POSITIVE_INFINITY_VECTOR, intersectionPoint(lineSegment1, lineSegment2));
    }

    @Test
    void intersectionPoint_two_touching_segments_of_the_same_line() {
        LineSegment lineSegment1 = new LineSegment(new Vector2D(0, 0), new Vector2D(1, 1));
        LineSegment lineSegment2 = new LineSegment(new Vector2D(1, 1), new Vector2D(2, 2));

        assertEquals(new Vector2D(1, 1), intersectionPoint(lineSegment1, lineSegment2));
    }

    @Test
    void intersectionPoint_two_disjunct_segments_of_the_same_line() {
        LineSegment lineSegment1 = new LineSegment(new Vector2D(0, 0), new Vector2D(1, 1));
        LineSegment lineSegment2 = new LineSegment(new Vector2D(1.5, 1.5), new Vector2D(2, 2));

        assertEquals(Vector2D.NEGATIVE_INFINITY_VECTOR, intersectionPoint(lineSegment1, lineSegment2));
    }

    @Test
    void intersectionPoint_two_completely_disjunct_segments() {
        LineSegment lineSegment1 = new LineSegment(new Vector2D(0, 0), new Vector2D(1, 1));
        LineSegment lineSegment2 = new LineSegment(new Vector2D(-4, -4), new Vector2D(-2, -5));

        assertEquals(Vector2D.NEGATIVE_INFINITY_VECTOR, intersectionPoint(lineSegment1, lineSegment2));
    }

    @Test
    void intersectionPoint_two_completely_disjunct_but_parallel_tilted_segments() {
        LineSegment lineSegment1 = new LineSegment(new Vector2D(0, 0), new Vector2D(1, 1));
        LineSegment lineSegment2 = new LineSegment(new Vector2D(-4, -4), new Vector2D(-5, -5));

        assertEquals(Vector2D.NEGATIVE_INFINITY_VECTOR, intersectionPoint(lineSegment1, lineSegment2));
    }

    @Test
    void intersectionPoint_two_completely_disjunct_but_parallel_horizontal_segments() {
        LineSegment lineSegment1 = new LineSegment(new Vector2D(0, 0), new Vector2D(1, 0));
        LineSegment lineSegment2 = new LineSegment(new Vector2D(-4, 2), new Vector2D(5, 2));

        assertEquals(Vector2D.NEGATIVE_INFINITY_VECTOR, intersectionPoint(lineSegment1, lineSegment2));
    }

    @Test
    void intersectionPoint_two_parallel_vertical_segments() {
        LineSegment lineSegment1 = new LineSegment(new Vector2D(0, 0), new Vector2D(0, 1));
        LineSegment lineSegment2 = new LineSegment(new Vector2D(-4, -2), new Vector2D(-4, 2));

        assertEquals(Vector2D.NEGATIVE_INFINITY_VECTOR, intersectionPoint(lineSegment1, lineSegment2));
    }

    @Test
    void intersectionPoint_one_vertical_one_horizontal_segment_which_intersect() {
        LineSegment lineSegment1 = new LineSegment(new Vector2D(0, 0), new Vector2D(0, 2));
        LineSegment lineSegment2 = new LineSegment(new Vector2D(-4, 1), new Vector2D(4, 1));

        assertEquals(new Vector2D(0, 1), intersectionPoint(lineSegment1, lineSegment2));
    }

    @Test
    void intersectionPoint_one_horizontal_one_vertical_segment_which_intersect() {
        LineSegment lineSegment1 = new LineSegment(new Vector2D(-4, 1), new Vector2D(4, 1));
        LineSegment lineSegment2 = new LineSegment(new Vector2D(0, 0), new Vector2D(0, 2));

        assertEquals(new Vector2D(0, 1), intersectionPoint(lineSegment1, lineSegment2));
    }

    @Test
    void intersectionPoint_one_horizontal_one_tilted_segment_which_intersect() {
        LineSegment lineSegment1 = new LineSegment(new Vector2D(-4, 1), new Vector2D(4, 1));
        LineSegment lineSegment2 = new LineSegment(new Vector2D(0, 0), new Vector2D(4, 2));

        assertEquals(new Vector2D(2, 1), intersectionPoint(lineSegment1, lineSegment2));
    }
}