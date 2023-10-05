package math.geometry2D;

import static math.geometry2D.LineSegmentIntersectionCalculator.intersectionPoint;

/**
 * The ray casting algorithm is a simple way to check if a point is contained inside another polygon.
 * <br><br>
 * The algorithm works as follows. Draw a line horizontally from the point to the right (or left) to infinity and count
 * how many intersections with an edge of the polygon are made: if the number of intersections is even, the point must
 * be outside (one intersection for entering, one for leaving the polygon interior); if the number of intersections is
 * odd, the point must be inside the polygon. Note that 0 intersections are also fine (0 is an even number).
 * <br><br>
 * Caveat: the situation can become delicate to interpret when the ray intersects with a vertex or runs
 * directly on top of an edge. Therefore, if a vertex was intersected or a, we should simply use another ray until we
 * do not hit a vertex.
 */
public class RayCastingAlgorithm {

    private static final int DELICATE_INTERSECTIONS_COUNT = -1;

    // CAVEAT: this is a main limitation - if we need to handle polygons close to that distance this will need to be
    // raised; but handling very large numbers might entail precision problems, so it is unclear how good it works then
    private static final double LARGE_DOUBLE = 10_000.0;

    private RayCastingAlgorithm() {
        // static class, so far
    }

    /**
     * If the point is on the edge of the polygon we consider it inside.
     */
    public static boolean isInside(Vector2D point, Parallelogram parallelogram) {
        return pointIsOnEdge(point, parallelogram) || pointIsNotOnEdgeButInside(point, parallelogram);
    }

    private static boolean pointIsOnEdge(Vector2D point, Parallelogram parallelogram) {
        return parallelogram.edges().stream().anyMatch(edge -> edge.contains(point));
    }

    private static boolean pointIsNotOnEdgeButInside(Vector2D point, Parallelogram parallelogram) {
        double rayVerticalShift = 0.0;
        int countIntersections = DELICATE_INTERSECTIONS_COUNT;
        while (countIntersections == DELICATE_INTERSECTIONS_COUNT) {
            LineSegment ray = getRay(point, rayVerticalShift);
            countIntersections = countIntersections(parallelogram, ray);
            rayVerticalShift += 1;
        }
        return countIntersections % 2 == 1;
    }

    private static LineSegment getRay(Vector2D point, double rayVerticalShift) {
        return new LineSegment(point, new Vector2D(LARGE_DOUBLE, point.y() + rayVerticalShift));
    }

    private static int countIntersections(Parallelogram parallelogram, LineSegment ray) {
        int intersectionCount = 0;
        for (LineSegment edge : parallelogram.edges()) {
            Vector2D intersectionPoint = intersectionPoint(ray, edge);
            if (parallelogram.isVertex(intersectionPoint)) {
                return DELICATE_INTERSECTIONS_COUNT;
            } else if (intersectionPoint.equals(Vector2D.POSITIVE_INFINITY_VECTOR)) {
                return DELICATE_INTERSECTIONS_COUNT;
            } else if (!intersectionPoint.equals(Vector2D.NEGATIVE_INFINITY_VECTOR)) {
                ++intersectionCount;
            }
        }
        return intersectionCount;
    }

}
