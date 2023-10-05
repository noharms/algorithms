package math.geometry2D;

import java.util.List;

/**
 * A parallelogram has two pairs of parallel sides like a rectangle but
 * by contrast to a rectangle the inner angles do not need to have 90°.
 *
 * <pre>
 *   y
 *   ^
 *   |         b  x-
 *   |           /    -
 *   |          /       -x d
 *   |         /        /
 *   |      a x-       /
 *   |           -    /
 *   |              -x c
 * -----------------------------------------------> x
 *   |
 *   |
 * </pre>
 *
 * Note how three corner points of the parallelogram automatically imply where the
 * fourth point is:
 * <pre>
 * d = a + ac + ab
 * </pre>
 * where ac is the connection vector from a to c and ab from a to b.
 *
 */
public record Parallelogram(Vector2D a, Vector2D ab, Vector2D ac) {

    public boolean isVertex(Vector2D point) {
        return vertices().stream().anyMatch(vertex -> vertex.equals(point));
    }

    public Vector2D b() {
        return a.add(ab);
    }

    public Vector2D c() {
        return a.add(ac);
    }

    public Vector2D d() {
        return a.add(ab).add(ac);
    }

    public List<Vector2D> vertices() {
        return List.of(a(), b(), c(), d());
    }

    public List<LineSegment> edges() {
        return List.of(
                new LineSegment(a(), b()),
                new LineSegment(b(), d()),
                new LineSegment(a(), c()),
                new LineSegment(c(), d())
        );
    }
}
