package math.geometry2D;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LineRelationTest {

    @Test
    void lineRelation_between_two_parallel_vertical_lines_is_parallel() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(0, 1));
        Line line2 = new Line(new Vector2D(4, 1), new Vector2D(0, 1));

        assertEquals(LineRelation.PARALELL, LineRelation.getLineRelation(line1, line2));
    }

    @Test
    void lineRelation_between_two_equivalent_vertical_lines_is_identical() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(0, 1));
        Line line2 = new Line(new Vector2D(0, 0), new Vector2D(0, 5.2));

        assertEquals(LineRelation.IDENTICAL, LineRelation.getLineRelation(line1, line2));
    }

    @Test
    void lineRelation_between_two_parallel_horizontal_lines_is_parallel() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(1, 0));
        Line line2 = new Line(new Vector2D(0, -3), new Vector2D(1, 0));

        assertEquals(LineRelation.PARALELL, LineRelation.getLineRelation(line1, line2));
    }

    @Test
    void lineRelation_between_two_equivalent_horizontal_lines_is_identical() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(1, 0));
        Line line2 = new Line(new Vector2D(0, 0), new Vector2D(2.222, 0));

        assertEquals(LineRelation.IDENTICAL, LineRelation.getLineRelation(line1, line2));
    }

    @Test
    void lineRelation_between_two_equivalent_horizontal_lines_of_opposite_direction_is_identical() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(1, 0));
        Line line2 = new Line(new Vector2D(0, 0), new Vector2D(-1, 0));

        assertEquals(LineRelation.IDENTICAL, LineRelation.getLineRelation(line1, line2));
    }

    @Test
    void lineRelation_between_two_parallel_tilted_lines_is_parallel() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(1, 1));
        Line line2 = new Line(new Vector2D(0, -2), new Vector2D(1, 1));

        assertEquals(LineRelation.PARALELL, LineRelation.getLineRelation(line1, line2));
    }

    @Test
    void lineRelation_between_two_equivalent_tilted_lines_is_identical() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(1, 1));
        Line line2 = new Line(new Vector2D(1, 1), new Vector2D(2, 2));

        assertEquals(LineRelation.IDENTICAL, LineRelation.getLineRelation(line1, line2));
    }

    @Test
    void lineRelation_between_two_crossing_tilted_lines_is_crossing() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(1, 1));
        Line line2 = new Line(new Vector2D(1, 0), new Vector2D(-1, 1));

        assertEquals(LineRelation.CROSSING, LineRelation.getLineRelation(line1, line2));
    }

    @Test
    void lineRelation_between_one_horizontal_and_one_tilted_line_is_crossing() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(1, 0));
        Line line2 = new Line(new Vector2D(1, 0), new Vector2D(1, 1));

        assertEquals(LineRelation.CROSSING, LineRelation.getLineRelation(line1, line2));
    }

    @Test
    void lineRelation_between_one_vertical_and_one_tilted_line_is_crossing() {
        Line line1 = new Line(new Vector2D(0, 0), new Vector2D(0, 1));
        Line line2 = new Line(new Vector2D(1, 0), new Vector2D(1, 1));

        assertEquals(LineRelation.CROSSING, LineRelation.getLineRelation(line1, line2));
    }
}