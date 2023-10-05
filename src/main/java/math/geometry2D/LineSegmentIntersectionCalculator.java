package math.geometry2D;

import static math.geometry2D.Vector2D.NEGATIVE_INFINITY_VECTOR;
import static math.geometry2D.Vector2D.POSITIVE_INFINITY_VECTOR;

public class LineSegmentIntersectionCalculator {

    private LineSegmentIntersectionCalculator() {
        // static class, so far
    }

    /**
     * The idea is: extrapolate the segments to lines, find the intersection point of the lines, and see if that point
     * is also contained in both the segments.
     * <br><br>
     * If there is 0 or infinitely many intersection points, {@link Vector2D#POSITIVE_INFINITY_VECTOR} is returned.
     */
    public static Vector2D intersectionPoint(LineSegment lineSegment1, LineSegment lineSegment2) {
        Line line1 = lineSegment1.extrapolateLine();
        Line line2 = lineSegment2.extrapolateLine();
        Vector2D intersectionPoint = LineIntersectionCalculator.intersectionPoint(line1, line2);
        if (intersectionPoint.equals(NEGATIVE_INFINITY_VECTOR)) {
            // extrapolated lines parallel
            return NEGATIVE_INFINITY_VECTOR;
        } else if (intersectionPoint.equals(POSITIVE_INFINITY_VECTOR)) {
            // extrapolated lines identical
            return haveSegmentsOfSameLineOverlap(lineSegment1, lineSegment2) ?
                    getTouchPointIfPossible(lineSegment1, lineSegment2) :
                    NEGATIVE_INFINITY_VECTOR;
        } else {
            // extrapolated lines crossing
            if (lineSegment1.contains(intersectionPoint) && lineSegment2.contains(intersectionPoint)) {
                return intersectionPoint;
            } else {
                return NEGATIVE_INFINITY_VECTOR;
            }
        }
    }

    private static Vector2D getTouchPointIfPossible(LineSegment lineSegment1, LineSegment lineSegment2) {
        return isTouchingOnlyAtEndpoint(lineSegment1, lineSegment2) ?
                getTouchPoint(lineSegment1, lineSegment2) :
                POSITIVE_INFINITY_VECTOR;
    }

    private static Vector2D getTouchPoint(LineSegment lineSegment1, LineSegment lineSegment2) {
        return lineSegment1.endPoint().equals(lineSegment2.startPoint()) ?
                lineSegment1.endPoint() :
                lineSegment1.startPoint();
    }

    private static boolean isTouchingOnlyAtEndpoint(LineSegment lineSegment1, LineSegment lineSegment2) {
        return lineSegment1.endPoint().equals(lineSegment2.startPoint()) ||
                lineSegment2.endPoint().equals(lineSegment1.startPoint());
    }

    private static boolean haveSegmentsOfSameLineOverlap(LineSegment lineSegment1, LineSegment lineSegment2) {
        return lineSegment2.contains(lineSegment1.startPoint()) || lineSegment1.contains(lineSegment2.startPoint());
    }
}
