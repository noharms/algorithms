package math.geometry2D;

/**
 * To check if two polygons overlap, we can simply go through each vertex of polygon1 and check if it is inside
 * polygon2: if at least one vertex is found that is inside polygon2 there is an overlap; if none is found, we also need
 * to check vice versa if the vertices of polygon2 are inside polygon1. If also there no vertex is found to be inside,
 * then we are sure that there is no overlap.
 * <p>
 * TODO: generalize to polygons from parallelograms
 */
public class PolygonOverlapChecker {

    private PolygonOverlapChecker() {
        // static class, so far
    }

    /**
     * A method to check whether two paralellograms overlap.
     */
    public static boolean haveOverlap(Parallelogram p1, Parallelogram p2) {
        return p1.vertices().stream().anyMatch(vertex -> RayCastingAlgorithm.isInside(vertex, p2))
                || p2.vertices().stream().anyMatch(vertex -> RayCastingAlgorithm.isInside(vertex, p1));
    }
}
