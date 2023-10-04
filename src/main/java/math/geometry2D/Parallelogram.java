package math.geometry2D;

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

    public Vector2D b() {
        return a.add(ab);
    }

    public Vector2D c() {
        return a.add(ac);
    }

    public Vector2D d() {
        return a.add(ab).add(ac);
    }
}
