package math.geometry2D;

import static math.geometry2D.Vector2D.DOUBLE_PRECISION;

public record LineSegment(Vector2D startPoint, Vector2D endPoint) {

    public Line extrapolateLine() {
        if (isVirtuallyVertical()) {
            return new Line(startPoint, new Vector2D(0, 1));
        } else {
            return new Line(startPoint, startToEnd());
        }
    }

    /**
     * We consider also those lines with too high a gradient as "virtually" vertical because we cannot handle too large
     * numbers properly, and therefore it is easier to consider them straight vertical lines.
     */
    public boolean isVirtuallyVertical() {
        return Double.compare(Math.abs(startToEnd().x() / startToEnd().y()), DOUBLE_PRECISION) < 0;
    }

    /**
     * Note that this is also a "direction" vector of the line segment.
     */
    public Vector2D startToEnd() {
        return endPoint().subtract(startPoint());
    }

    /**
     * A point (e, f) is contained in a line, if the line representation equals the point for some value of the line
     * parameter t:
     * <pre>
     *            (a, b) + t * (c, d) = (e, f)
     *
     *     <->     t = (e - a) / c
     *          && t = (f - b) / d
     * </pre>
     * This is an overdetermined system, which means if not both equations can be met, there is no solution.
     * <br><br>
     * This idea can be used to check if a point is on a line segment by taking (a,b) the starting point and (c, d) the
     * connection vector to the end-point, and then t must not only exist but also be between [0, 1] if (e,f) is part
     * of the segment.
     */
    public boolean contains(Vector2D point) {
        double a = startPoint.x();
        double b = startPoint.y();
        double c = startToEnd().x();
        double d = startToEnd().y();
        double e = point.x();
        double f = point.y();

        double t1 = c != 0.0 ? (e - a) / c : Double.NaN;
        double t2 = d != 0.0 ? (f - b) / d : Double.NaN;
        return !Double.isNaN(t1) && !Double.isNaN(t2) && t1 == t2 && t1 >= 0.0 && t1 <= 1.0;
    }
}
