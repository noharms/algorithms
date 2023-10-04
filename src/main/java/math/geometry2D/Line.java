package math.geometry2D;

import java.util.Objects;

/**
 * A representation of a line by
 * <ol>
 *     <li>a sample point</li>
 *     <li>a direction vector</li>
 * </ol>
 * <p>
 * Note that an alternative representation as a function "f(x) = c * x + d" is also provided as
 * {@link LineFunction}. Its disadvantage is that it does not allow to represent vertical lines.
 * <br><br>
 * The advantage of the {@link LineFunction} is that it is easier to implement its equals method because in the vector
 * representation the same line can be represented by infinitely many (samplePoint, direction) pairs.
 */
public final class Line {
    private final Vector2D samplePoint;
    private final Vector2D direction;

    public Line(Vector2D samplePoint, Vector2D direction) {
        this.samplePoint = samplePoint;
        this.direction = direction;
    }

    public boolean isVerticalLine() {
        return direction.x() == 0;
    }

    public LineFunction toLineFunction() {
        if (isVerticalLine()) {
            throw new UnsupportedOperationException("For vertical lines we cannot convert to a line function.");
        }
        Vector2D directionNormalized = direction.normalize();
        double gradient = directionNormalized.y() / directionNormalized.x();
        // from  y = gradient * x + c, where c is still unknown we can follow from the knowledge of the sample point
        // --> startPoint.y = gradient * startPoint.x + c
        // --> c = startPoint.y - gradient * startPoint.x
        double yIntersection = samplePoint.y() - gradient * samplePoint.x();
        return new LineFunction(gradient, yIntersection);
    }

    public Vector2D getSamplePoint() {
        return samplePoint;
    }

    public Vector2D getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line otherLine = (Line) o;
        if (isVerticalLine()) {
            return samplePoint.x() == otherLine.samplePoint.x();
        } else {
            return toLineFunction().equals(otherLine.toLineFunction());
        }
    }

    @Override
    public int hashCode() {
        if (isVerticalLine()) {
            return Objects.hash(samplePoint.x());
        } else {
            return Objects.hash(toLineFunction());
        }
    }

    @Override
    public String toString() {
        if (isVerticalLine()) {
            return "Vertical line at: x=%.2f".formatted(samplePoint.x());
        } else {
            return toLineFunction().toString();
        }
    }

    record LineFunction(double gradient, double yAxisIntersection) {

        public double valueAt(double x) {
            return gradient * x + yAxisIntersection;
        }

    }
}
