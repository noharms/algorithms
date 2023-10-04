package math.geometry;

import java.util.Locale;

import static math.geometry.Vector2D.DOUBLE_PRECISION;

public record LineSegment(Vector2D startPoint, Vector2D endPoint) {

    public String extrapolatedLineEquation() {
        if (Double.compare(Math.abs(startToEnd().x()), DOUBLE_PRECISION) < 0) {
            return String.format(Locale.ENGLISH, "Vertical Line at x=%.2f", startPoint.x());
        } else {
            Vector2D normalizedConnectionVector = startToEnd().normalize();
            double gradient = normalizedConnectionVector.y() / normalizedConnectionVector.x();
            // from  y = gradient * x + c, where c is still unknown we can follow from the knowledge of the start point
            // --> startPoint.y = gradient * startPoint.x + c
            // --> c = startPoint.y - gradient * startPoint.x
            double c = startPoint.y() - gradient * startPoint.x();
            return String.format(Locale.ENGLISH, "y = %.2f * x + %.2f", gradient, c);
        }
    }

    public Vector2D startToEnd() {
        return endPoint().subtract(startPoint());
    }
}
